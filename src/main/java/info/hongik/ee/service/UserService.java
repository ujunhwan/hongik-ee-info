package info.hongik.ee.service;

import info.hongik.ee.domain.course.Course;
import info.hongik.ee.domain.LoginDto;
import info.hongik.ee.domain.user.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface UserService {
    public Long join(User user);
    public User findBysId(String studentId);
    public Map<String, String> login(Map<String, String> loginInfo);

    public Map<String, String> login(LoginDto loginDto);
    public boolean logout();

//    public Map<String, String> getCookies(HttpServletRequest request);

    public List<Course> getCourses(HttpServletRequest request);
    public String getGraduationRequirement(HttpServletRequest request);

    public List<Course> crawlUserInfo(Map<String, String> cookies);
    public List<Long> getClassifiedClasses(Long fieldId);
    public String getMajorFromId(String id);
    public String getYearFromId(String id);
}
