package info.hongik.ee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NoticeController {
    @GetMapping("api/notice")
    public String notice() {
        return "notice";
    }
}
