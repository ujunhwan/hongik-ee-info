package info.hongik.ee.domain.course;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Course {

    @Id @GeneratedValue
    @Column(name = "course_id")
    private Long id;
    private String courseNumber;
    private String courseName;
    private String credit;
    private String grade;
    private String type;

    public Course() {
    }

    public Course(String courseNumber, String courseName, String credit, String grade) {
        this.courseNumber = courseNumber;
        this.courseName = courseName;
        this.credit = credit;
        this.grade = grade;
    }

}

