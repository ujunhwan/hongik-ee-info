package info.hongik.ee.domain.user;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity @Getter
public class User {
    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    private String studentId;
    private String year;
    private String major;
    private LocalDateTime updateDate;

    public User() {
    }

    public User(String studentId, String year, String major, LocalDateTime updateDate) {
        this.studentId = studentId;
        this.year = year;
        this.major = major;
        this.updateDate = updateDate;
    }
}

