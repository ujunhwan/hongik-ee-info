package info.hongik.ee.domain;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
public class Class {

    @Id
    @Column(name = "class_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @NotNull
    private ClassType type;

    private Integer level;
    private int credit;

    public Class() {
    }

    public Class(Long id, ClassType type, Integer level, int credit) {
        this.id = id;
        this.type = type;
        this.level = level;
        this.credit = credit;
    }

    public Long getId() {
        return id;
    }

    public ClassType getType() {
        return type;
    }

    public Integer getLevel() {
        return level;
    }

    public int getCredit() {
        return credit;
    }
}

