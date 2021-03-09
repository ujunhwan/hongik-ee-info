package info.hongik.ee.controller;

import info.hongik.ee.domain.LoginInfo;
import info.hongik.ee.service.SecurityService;
import info.hongik.ee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserApiController {

    private final UserService userService;
    private final SecurityService securityService;

    @Autowired
    public UserApiController(UserService userService, SecurityService securityService) {
        this.userService = userService;
        this.securityService = securityService;
    }

    @PostMapping(value = "/login", produces = "application/json; charset=UTF-8")
    public Map<String, String> loginPost(@RequestBody LoginInfo loginInfo) {
        System.out.println(loginInfo.getId());
        if(userService.login(loginInfo)) {
            String token = securityService.createToken();
            System.out.println(token);
            Map<String, String> res = new HashMap<>();
            res.put("token", token);
            res.put("isLogin", "true");
            return res;
        }
        return null;
    }

    @GetMapping(value="/logout")
    public boolean logoutGet() {
        userService.logout();
        return true;
    }

    @GetMapping(value="/class")
    public boolean classGet() {
        userService.crawlUserInfo();
        return true;
    }

}
