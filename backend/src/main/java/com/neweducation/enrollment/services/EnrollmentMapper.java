package com.neweducation.enrollment.services;

import com.neweducation.enrollment.dtos.enrollment_home.CourseItemDTO;
import com.neweducation.enrollment.dtos.group_enrollment.CourseListDTO;
import com.neweducation.enrollment.dtos.group_enrollment.CourseWithGroupItemDTO;
import com.neweducation.enrollment.dtos.group_enrollment.GroupItemDTO;
import com.neweducation.enrollment.models.Course;
import com.neweducation.enrollment.models.EnrollmentBlock;
import com.neweducation.enrollment.models.FieldOfStudy;
import com.neweducation.enrollment.models.Group;

import java.util.List;
import java.util.stream.Collectors;

public class EnrollmentMapper {

    private static final String[] DAYS_OF_WEEK = {"Poniedziałek", "Wtorek", "Środa", "Czwartek", "Piątek", "Sobota", "Niedziela"};

    public CourseItemDTO mapToCourseItem(Course course) {
        CourseItemDTO courseItemDTO = new CourseItemDTO();
        courseItemDTO.setCode(course.getCode());
        courseItemDTO.setName(course.getName());
        courseItemDTO.setNumberOfEcts(course.getNumberOfEcts());
        courseItemDTO.setSelectable(course.isSelectable());
        courseItemDTO.setFormOfClasses(course.getFormOfClasses().getValue());
        return courseItemDTO;
    }

    public CourseListDTO mapToCourseListDTO(FieldOfStudy fieldOfStudy,
                                            EnrollmentBlock enrollmentBlock,
                                            List<Course> courses) {

        CourseListDTO courseListDTO = new CourseListDTO();
        courseListDTO.setFieldOfStudyCode(fieldOfStudy.getCode());
        courseListDTO.setFieldOfStudyName(fieldOfStudy.getName());
        courseListDTO.setEnrollmentBlockId(enrollmentBlock.getId());
        courseListDTO.setEnrollmentBlockName(enrollmentBlock.getName());
        courseListDTO.setCourses(courses.stream().map(this::mapToCourseItem).collect(Collectors.toList()));

        return courseListDTO;
    }

    public GroupItemDTO mapToGroupItemDTO(Group group) {
        GroupItemDTO groupItemDTO = new GroupItemDTO();
        groupItemDTO.setDate(mapToDate(group));
        groupItemDTO.setCode(group.getCode());
        groupItemDTO.setPlace(group.getPlace());
        groupItemDTO.setLecturers(mapToLecturers(group));
        groupItemDTO.setOccupancy(mapToOccupancy(group));
        return groupItemDTO;
    }

    public CourseWithGroupItemDTO mapToCourseWithGroupItemDTO(Course course, Group group) {
        CourseWithGroupItemDTO courseWithGroupItemDTO = new CourseWithGroupItemDTO();
        courseWithGroupItemDTO.setCourse(mapToCourseItem(course));
        if(group != null)
            courseWithGroupItemDTO.setGroup(mapToGroupItemDTO(group));
        return courseWithGroupItemDTO;
    }

    private String mapToOccupancy(Group group) {
        int occupied = group.getEnrollments().size();
        return occupied + " / " + group.getLimitOfPlaces();
    }

    private String mapToLecturers(Group group) {
        return group.getLecturers().stream()
                .map(lecturer -> lecturer.getTitle() + " " + lecturer.getName() + " " + lecturer.getSurname())
                .collect(Collectors.joining(", "));
    }

    private String mapToDate(Group group) {
        if(group.getDayOfWeek() == -1)
            return "Bez terminu";
        String dayOfWeek = DAYS_OF_WEEK[group.getDayOfWeek()];
        int hourStart = group.getHour();
        int minuteStart = group.getMinute();
        return new StringBuilder()
                .append(dayOfWeek).append(" ")
                .append(hourStart).append(":")
                .append(minuteStart < 10 ? "0" + minuteStart : minuteStart).append(" (")
                .append(group.getDuration()).append(" min.)")
                .toString();
    }

}
