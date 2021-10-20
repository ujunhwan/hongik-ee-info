package info.hongik.ee.domain.user;

import info.hongik.ee.domain.base.BaseEntity;
import info.hongik.ee.domain.base.BaseTimeEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity @Getter
public class User extends BaseEntity {
    private String studentId;
    private String year;
    private String major;

    public User() {
    }

    public User(String studentId, String year, String major) {
        this.studentId = studentId;
        this.year = year;
        this.major = major;
    }
}

