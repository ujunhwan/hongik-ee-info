package info.hongik.ee.domain.taken;

import info.hongik.ee.domain.course.Course;
import info.hongik.ee.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter @Setter
@AllArgsConstructor
public class Taken {

    @Id @GeneratedValue
    @Column(name = "user_class_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "class_id")
    private Course course;

    private int grade;

    public Taken() {
    }
}
