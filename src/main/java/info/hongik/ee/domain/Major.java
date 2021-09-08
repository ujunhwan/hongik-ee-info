package info.hongik.ee.domain;

import info.hongik.ee.domain.course.Course;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Major extends Course {

    private String field;
    private int level;

    public Major(String classNumber, String className, String credit, String grade, String field, int level) {
        super(classNumber, className, credit, grade);
        this.field = field;
        this.level = level;
    }

}
