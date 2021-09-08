package info.hongik.ee.controller;

import info.hongik.ee.domain.course.Course;
import info.hongik.ee.domain.LoginDto;
import info.hongik.ee.domain.user.User;
import info.hongik.ee.service.SecurityService;
import info.hongik.ee.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:3000/", allowedHeaders = "*", allowCredentials = "true")
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;
    private final SecurityService securityService;

    @GetMapping(value = "/graduation", produces = "application/json; charset=UTF-8")
    public String graduationRequirement(HttpServletRequest request) {
        System.out.println("/api/user/graduation");
        return userService.getGraduationRequirement(request);
    }

    @GetMapping(value = "/courses", produces = "application/json; charset=UTF-8")
    public List<Course> courseList(HttpServletRequest request) {
        System.out.println("/api/user/courses");
        return userService.getCourses(request);
    }

    @PostMapping(value = "/login", produces = "application/json; charset=UTF-8")
    public Map<String, String> loginPost(@RequestBody LoginDto loginDto, HttpServletResponse response) {
        System.out.println("/api/user/login");
        Map<String, String> loginCookie = userService.login(loginDto);

        if(loginCookie != null) {
            loginCookie.forEach((key, value) -> {
                System.out.println("key = " + key);
                System.out.println("value = " + value);
                Cookie cookie = new Cookie(key, value);
                cookie.setMaxAge(60*60); // 1 hour
                cookie.setPath("/");
                response.addCookie(cookie);
            });
//            String token = securityService.createToken(loginInfo);

//            Cookie cookie = new Cookie("x_auth", token);
//            cookie.setMaxAge(-1);
//            response.addCookie(cookie);

//            Map<String, String> res = new HashMap<>();
//            res.put("token", token);
//            res.put("isLogin", "true");

//            // user 없으면
//            String id = loginInfo.get("USER_ID");
//            String year = userService.getYearFromId(id);
//            String major = userService.getMajorFromId(id);
//            User user = new User(id, year, major, LocalDateTime.now());
//
//            Long userId = userService.join(user);
//            System.out.println("userId = " + userId);

//            List<Course> classes = userService.crawlUserInfo(userId, loginCookie);

            System.out.println("Login Success!");

//            return res;
            for (String s : loginCookie.keySet()) {
                System.out.println(s + " : " + loginCookie.get(s));
            }
            return loginCookie;
        }


        System.out.println("Login False!");
        return null;
    }

    @GetMapping(value="/logout")
    public boolean logoutGet() {
        System.out.println("/api/user/logout");
        userService.logout();
        return true;
    }


}
