package info.hongik.ee.domain.course;

import com.sun.istack.NotNull;
import info.hongik.ee.domain.base.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@ToString
public class Course extends BaseEntity {
    @Column(nullable = false)
    private String courseNumber;

    @Column(nullable = false)
    private String courseName;

    @Column(nullable = false)
    private String credit;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CourseEnum type; // 교양? 전공? 드래곤볼?

    public Course(String courseNumber, String courseName, String credit, CourseEnum type) {
        this.courseNumber = courseNumber;
        this.courseName = courseName;
        this.credit = credit;
        this.type = type;
    }
}

