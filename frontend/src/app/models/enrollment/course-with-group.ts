import {CourseItem} from './course-item';
import {GroupItem} from './group-item';

export interface CourseWithGroup {
  course: CourseItem;
  group: GroupItem;
}
