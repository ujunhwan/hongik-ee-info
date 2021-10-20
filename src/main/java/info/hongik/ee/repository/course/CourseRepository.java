package info.hongik.ee.repository.course;

import info.hongik.ee.domain.course.Course;
import info.hongik.ee.domain.course.CourseEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    Course findCourseOneById(Long id);
    Course findCourseOneByCourseNumber(String courseNumber);
    List<Course> findCourseListByCourseName(String courseName);
    List<Course> findCourseListByCourseNumber(String courseNumber);
    List<Course> findCourseListByType(Enum<CourseEnum> type);
    List<Course> findAll();
}
