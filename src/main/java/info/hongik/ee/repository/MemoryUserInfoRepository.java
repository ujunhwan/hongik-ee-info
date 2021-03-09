package info.hongik.ee.repository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Map;

public class MemoryUserInfoRepository implements UserInfoRepository {
    private String id;
    private String studentId;
    private String acquisition;
    private Map<String, String> cookie;
    private WebDriver driver;

    @Override
    public void saveUserId(String id) {
        this.id = id;
    }

    @Override
    public String getUserId() {
        return id;
    }

    @Override
    public void saveCookie(Map<String, String> cookie) {
        this.cookie = cookie;
    }

    @Override
    public Map<String, String> getCookie() {
        return cookie;
    }

    @Override
    public void saveStudentId(String studentId) {
        this.studentId = studentId;
    }

    @Override
    public String getStudentId() {
        return studentId;
    }

    @Override
    public void saveAcquisition(String acquisition) {
        this.acquisition = acquisition;
    }

    @Override
    public void saveDriver(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public WebDriver getDriver() {
        return driver;
    }
}
