import {CourseItem} from './course-item';

export interface CourseList {
  enrollmentBlockId: number;
  enrollmentBlockName: string;
  fieldOfStudyCode: string;
  fieldOfStudyName: string;
  courses: CourseItem[];
}
