package com.neweducation.enrollment.services.group_enrollment;

import com.neweducation.enrollment.dtos.MessageDTO;
import com.neweducation.enrollment.dtos.group_enrollment.CourseListDTO;
import com.neweducation.enrollment.dtos.group_enrollment.CourseWithGroupItemDTO;
import com.neweducation.enrollment.dtos.group_enrollment.GroupItemDTO;
import com.neweducation.enrollment.dtos.group_enrollment.SubscriptionDTO;
import com.neweducation.enrollment.exceptions.enrollment.CourseAlreadySubscribedException;
import com.neweducation.enrollment.exceptions.enrollment.GroupAlreadySubscribedException;
import com.neweducation.enrollment.exceptions.enrollment.GroupFullException;
import com.neweducation.enrollment.exceptions.enrollment.OutOfEnrollmentBlockException;
import com.neweducation.enrollment.exceptions.not_found.*;
import com.neweducation.enrollment.models.*;
import com.neweducation.enrollment.repositories.*;
import com.neweducation.enrollment.services.EnrollmentMapper;
import com.neweducation.enrollment.services.EnrollmentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupEnrollmentService extends EnrollmentService {

    private final EnrollmentBlockRepository enrollmentBlockRepository;
    private final CourseRepository courseRepository;
    private final GroupRepository groupRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final StudentScheduleRepository studentScheduleRepository;
    private final ModelMapper mapper;
    private final EnrollmentMapper enrollmentMapper;

    public GroupEnrollmentService(StudyingDetailsRepository studyingDetailsRepository,
                                  StudentRepository studentRepository,
                                  FieldOfStudyRepository fieldOfStudyRepository,
                                  EnrollmentBlockRepository enrollmentBlockRepository,
                                  CourseRepository courseRepository,
                                  GroupRepository groupRepository,
                                  EnrollmentRepository enrollmentRepository,
                                  StudentScheduleRepository studentScheduleRepository) {
        super(studyingDetailsRepository, studentRepository, fieldOfStudyRepository);
        this.enrollmentBlockRepository = enrollmentBlockRepository;
        this.courseRepository = courseRepository;
        this.groupRepository = groupRepository;
        this.enrollmentRepository = enrollmentRepository;
        this.studentScheduleRepository = studentScheduleRepository;
        this.mapper = new ModelMapper();
        this.enrollmentMapper = new EnrollmentMapper();
    }

    public CourseListDTO getCourseList(Long studentIndex, String fieldOfStudyCode, Long enrollmentBlockId) {
        Student student = studentRepository.findByIndex(studentIndex)
                .orElseThrow(() -> new StudentNotFoundException(String.valueOf(studentIndex)));
        List<Course> courses = new ArrayList<>();
        courses.addAll(getCourses(studentIndex, fieldOfStudyCode, CourseStatus.TO_PASS));
        courses.addAll(getCourses(studentIndex, fieldOfStudyCode, CourseStatus.FAILED));

        FieldOfStudy fieldOfStudy = fieldOfStudyRepository.findByCode(fieldOfStudyCode)
                .orElseThrow(() -> new FieldOfStudyNotFoundException(fieldOfStudyCode));
        EnrollmentBlock enrollmentBlock = enrollmentBlockRepository.findById(enrollmentBlockId)
                .orElseThrow(() -> new EnrollmentBlockNotFoundException(enrollmentBlockId));

        return enrollmentMapper.mapToCourseListDTO(fieldOfStudy, enrollmentBlock, courses);
    }

    public List<GroupItemDTO> getGroupList(Long studentIndex, String courseCode, Long enrollmentBlockId) {
        Student student = studentRepository.findByIndex(studentIndex)
                .orElseThrow(() -> new StudentNotFoundException(String.valueOf(studentIndex)));
        Course course = courseRepository.findByCode(courseCode)
                .orElseThrow(() -> new CourseNotFoundException(courseCode));
        EnrollmentBlock enrollmentBlock = enrollmentBlockRepository.findById(enrollmentBlockId)
                .orElseThrow(() -> new EnrollmentBlockNotFoundException(enrollmentBlockId));

        return course.getGroups().stream()
                .filter(group -> group.getRealization().equals(enrollmentBlock.getRealization()))
                .map(enrollmentMapper::mapToGroupItemDTO)
                .collect(Collectors.toList());
    }

    public List<CourseWithGroupItemDTO> getCurrentCourses(Long studentIndex, String fieldOfStudyCode) {
        Student student = studentRepository.findByIndex(studentIndex)
                .orElseThrow(() -> new StudentNotFoundException(String.valueOf(studentIndex)));

        List<Course> courses = getCourses(studentIndex, fieldOfStudyCode, CourseStatus.TO_PASS);
        return assignGroupsToCourses(studentIndex, courses);
    }

    public List<CourseWithGroupItemDTO> getOverdueCourses(Long studentIndex, String fieldOfStudyCode) {
        Student student = studentRepository.findByIndex(studentIndex)
                .orElseThrow(() -> new StudentNotFoundException(String.valueOf(studentIndex)));

        List<Course> courses = getCourses(studentIndex, fieldOfStudyCode, CourseStatus.FAILED);
        return assignGroupsToCourses(studentIndex, courses);
    }

    public MessageDTO subscribeToTheGroup(SubscriptionDTO subscriptionDTO) {
        Student student = studentRepository.findByIndex(subscriptionDTO.getStudentIndex())
                .orElseThrow(() -> new StudentNotFoundException(String.valueOf(subscriptionDTO.getStudentIndex())));

        Group group = groupRepository.findByCode(subscriptionDTO.getGroupCode())
                .orElseThrow(() -> new GroupNotFoundException(subscriptionDTO.getGroupCode()));

        EnrollmentBlock enrollmentBlock = enrollmentBlockRepository.findById(subscriptionDTO.getEnrollmentBlockId())
                .orElseThrow(() -> new EnrollmentBlockNotFoundException(subscriptionDTO.getEnrollmentBlockId()));

        checkEnrollmentRules(student, group, enrollmentBlock);

        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setGroup(group);
        enrollment.setEnrollmentDate(new Date());
        enrollment.setEnrollmentMode(enrollmentBlock.isCorrection() ? EnrollmentMode.CORRECTION : EnrollmentMode.NORMAL);

        enrollmentRepository.save(enrollment);

        return new MessageDTO("Pomyślnie zapisano do grupy " + subscriptionDTO.getGroupCode() + "!");
    }

    public MessageDTO unsubscribeFromTheGroup(Long studentIndex, String groupCode) {
        Student student = studentRepository.findByIndex(studentIndex)
                .orElseThrow(() -> new StudentNotFoundException(String.valueOf(studentIndex)));

        Group group = groupRepository.findByCode(groupCode)
                .orElseThrow(() -> new GroupNotFoundException(groupCode));

        Enrollment enrollment = enrollmentRepository.findByStudentAndGroup(student, group)
                .orElseThrow(() -> new EnrollmentNotFoundException(studentIndex, groupCode));

        enrollmentRepository.delete(enrollment);

        return new MessageDTO("Pomyślnie wypisano z grupy " + groupCode + "!");
    }

    private void checkEnrollmentRules(Student student, Group group, EnrollmentBlock enrollmentBlock) {
        if(enrollmentRepository.findByStudentAndGroup(student, group).isPresent())
            throw new GroupAlreadySubscribedException(group.getCode());

        if(checkCourseSubscription(student, group))
            throw new CourseAlreadySubscribedException(
                    group.getCourse().getCode(), group.getCourse().getName(), group.getCourse().getFormOfClasses().getValue());

        if(checkIfEnrollmentBlockIsClosed(student, enrollmentBlock))
            throw new OutOfEnrollmentBlockException(enrollmentBlock.getName());

        if(checkIfGroupIsFull(group))
            throw new GroupFullException(group.getCode(), group.getCourse().getCode(), group.getCourse().getName());
    }

    private boolean checkCourseSubscription(Student student, Group group) {
        for(Enrollment enrollment : student.getEnrollments()) {
            if(enrollment.getGroup().getCourse().getCode().equals(group.getCourse().getCode())) {
                return true;
            }
        }
        return false;
    }

    private boolean checkIfEnrollmentBlockIsClosed(Student student, EnrollmentBlock enrollmentBlock) {
        StudentSchedule studentSchedule = studentScheduleRepository
                .findByStudentIndexAndEnrollmentBlockId(student.getIndex(), enrollmentBlock.getId())
                .orElseThrow(() -> new StudentScheduleNotFoundException(student.getIndex(), enrollmentBlock.getId()));

        Date currentDate = new Date();
        return currentDate.after(studentSchedule.getEndDate()) || currentDate.before(studentSchedule.getStartDate());
    }

    private boolean checkIfGroupIsFull(Group group) {
        return group.getEnrollments().size() == group.getLimitOfPlaces();
    }

    private List<CourseWithGroupItemDTO> assignGroupsToCourses(Long studentIndex, List<Course> courses) {
        List<CourseWithGroupItemDTO> courseWithGroupItemDTOS = new ArrayList<>();
        for(Course course : courses) {
            Group assignedGroup = course.getGroups().stream()
                    .filter(group -> group.getEnrollments().stream()
                            .map(enrollment -> enrollment.getStudent().getIndex())
                            .collect(Collectors.toList()).contains(studentIndex))
                    .findFirst().orElse(null);
            courseWithGroupItemDTOS.add(enrollmentMapper.mapToCourseWithGroupItemDTO(course, assignedGroup));
        }
        return courseWithGroupItemDTOS;
    }

}
