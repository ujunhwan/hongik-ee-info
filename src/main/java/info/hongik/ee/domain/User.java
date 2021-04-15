package info.hongik.ee.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class User {
    @Id
    @Column(name = "user_id")
    private String id;

    private int year;
    private LocalDateTime updateDate;

    public User() {
    }

    public User(String id, int year, LocalDateTime updateDate) {
        this.id = id;
        this.year = year;
        this.updateDate = updateDate;
    }

    public String getId() {
        return id;
    }

    public int getYear() {
        return year;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }
}

