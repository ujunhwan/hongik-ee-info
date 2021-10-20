package info.hongik.ee.payload;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import info.hongik.ee.domain.course.CourseEnum;
import lombok.Getter;

@Getter
public class CourseCreateRequest {

    private final String courseName;
    private final String courseNumber;
    private final String credit;
    private final CourseEnum type;

    @JsonCreator
    public CourseCreateRequest(
            @JsonProperty("course_name") String courseName,
            @JsonProperty("course_number") String courseNumber,
            @JsonProperty("credit") String credit,
            @JsonProperty("type") CourseEnum type
            ) {
        this.courseName = courseName;
        this.courseNumber = courseNumber;
        this.credit = credit;
        this.type = type;
    }
}
