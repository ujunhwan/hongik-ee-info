package info.hongik.ee.domain.user;

import lombok.Getter;

@Getter
public class UserUpdateDto {
    private String studentId;
    private String year;
    private String major;

    public UserUpdateDto(String studentId, String year, String major) {
        this.studentId = studentId;
        this.year = year;
        this.major = major;
    }
}

