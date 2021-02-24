package info.hongik.ee.controller;

import info.hongik.ee.domain.LoginInfo;
import info.hongik.ee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LoginApiController {

    private final UserService userService;

    @Autowired
    public LoginApiController(UserService userService) {
        this.userService = userService;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping(value = "/login", produces = "application/json; charset=UTF-8")
    public String loginPost(@RequestBody LoginInfo loginInfo) {
        System.out.println(loginInfo.getId());

        /* todo: loginInfo 로 클래스넷 login 후 Crawling */
        userService.scrapeUserInfo(loginInfo);

        /* todo: Crawling한 정보들을 가공해서 리턴 */
        return "post response";
    }
}
