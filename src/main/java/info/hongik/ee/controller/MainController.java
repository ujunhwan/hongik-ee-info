package info.hongik.ee.controller;

import info.hongik.ee.domain.Notice;
import info.hongik.ee.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    private final NoticeService noticeService;

    @Autowired
    public MainController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @GetMapping("/")
    public String blank(Model model) {
        model.addAttribute("data", "index");
        return "index";
    }

    @GetMapping("index")
    public String index() {
        return "index";
    }

    @GetMapping("graduation")
    public String graduation() {
        return "graduation";
    }

    @GetMapping("subjects")
    public String subjects() {
        return "subjects";
    }

    @GetMapping("about")
    public String about() {
        return "about";
    }

    @GetMapping("api")
    @ResponseBody
    public Notice noticeApi() {
        Notice notice = new Notice();
        notice.setTitle("Title");
        notice.setLink("Link");
        return notice;
    }
}
