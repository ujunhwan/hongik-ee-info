package info.hongik.ee.controller;

import info.hongik.ee.domain.LoginInfo;
import info.hongik.ee.service.SecurityService;
import info.hongik.ee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:3000/", allowedHeaders = "*", allowCredentials = "true")
public class UserApiController {

    private final UserService userService;
    private final SecurityService securityService;

    @Autowired
    public UserApiController(UserService userService, SecurityService securityService) {
        this.userService = userService;
        this.securityService = securityService;
    }

    @PostMapping(value = "/login", produces = "application/json; charset=UTF-8")
    public Map<String, String> loginPost(@RequestBody LoginInfo loginInfo, HttpServletResponse response) {
        if(userService.login(loginInfo)) {
            String token = securityService.createToken(loginInfo.getId());

            Cookie cookie = new Cookie("x_auth", token);
            cookie.setMaxAge(-1);
            response.addCookie(cookie);

            Map<String, String> res = new HashMap<>();
            res.put("token", token);
            res.put("isLogin", "true");

            // token만 db에 저장함

            System.out.println("Login Success!");
            return res;
        }
        System.out.println("Login False!");
        return null;
    }

    @GetMapping(value="/logout")
    public boolean logoutGet() {
        userService.logout();
        return true;
    }

}
