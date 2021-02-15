package info.hongik.ee.controller;

import info.hongik.ee.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class LoginApiController {
    private final NoticeService noticeService;

    @Autowired
    public LoginApiController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @GetMapping("login")
    public String login() {
        System.out.println("get login");
        return "login";
    }

    @GetMapping("logout")
    public String logout() {
        System.out.println("get logout");
        return "redirect:/";
    }
}
