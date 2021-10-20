package info.hongik.ee.domain.course;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CourseDto {
    private String courseName;
    private String courseNumber;
    private String credit;
    private CourseEnum type;

    public CourseDto(Course course) {
        this.courseName = course.getCourseName();
        this.courseNumber = course.getCourseNumber();
        this.credit = course.getCredit();
        this.type = course.getType();
    }
}
