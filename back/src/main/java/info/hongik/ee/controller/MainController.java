package info.hongik.ee.controller;

import info.hongik.ee.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    private final NoticeService noticeService;

    @Autowired
    public MainController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @GetMapping("/")
    public String blank() {
        return "home";
    }

    @GetMapping("home")
    public String index() {
        return "home";
    }

    @GetMapping("grad")
    public String grad() {
        return "grad";
    }

    @GetMapping("field")
    public String field() {
        return "field";
    }

    @GetMapping("about")
    public String about() {
        return "about";
    }

}
