package info.hongik.ee.domain.course;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CourseDto {
    private final String name;
    private final String number;
    private final String credit;
    private final String grade;
    private final String type;
}
