package info.hongik.ee.service;

import info.hongik.ee.domain.course.Course;
import info.hongik.ee.domain.LoginDto;
import info.hongik.ee.domain.user.User;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface UserService {
    public Map<String, String> login(LoginDto loginDto) throws IOException;
    public boolean logout();

    public List<Course> getCourses(HttpServletRequest request) throws IOException;
    public String getGraduationRequirement(HttpServletRequest request) throws IOException;

    public List<Course> crawlUserInfo(Map<String, String> cookies);
    public String getMajorFromId(String id);
    public String getYearFromId(String id);
}
