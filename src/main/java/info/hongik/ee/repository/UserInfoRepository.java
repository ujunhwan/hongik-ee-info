package info.hongik.ee.repository;

import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Repository;

import java.util.Map;

public interface UserInfoRepository {
    void saveCookie(Map<String, String> cookie);

    Map<String, String> getCookie();

    void saveStudentId(String studentId);

    String getStudentId();

    void saveAcquisition(String acquisition);

    void saveDriver(WebDriver driver);
    WebDriver getDriver();

    void saveUserId(String id);
    String getUserId();
}

