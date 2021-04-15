package info.hongik.ee.domain;

import javax.persistence.*;

@Entity
public class UserClass {

    @Id @GeneratedValue
    @Column(name = "user_class_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id")
    private Class aClass;

    private int grade;

    public UserClass() {
    }

    public UserClass(Long id, User user, Class aClass, int grade) {
        this.id = id;
        this.user = user;
        this.aClass = aClass;
        this.grade = grade;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Class getaClass() {
        return aClass;
    }

    public int getGrade() {
        return grade;
    }
}
