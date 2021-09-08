package info.hongik.ee.domain;

import info.hongik.ee.domain.course.Course;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Elective extends Course {

    private String type;
    private String detail;

    public Elective(String classNumber, String className, String credit, String grade, String type, String detail) {
        super(classNumber, className, credit, grade);
        this.type = type;
        this.detail = detail;
    }
}
