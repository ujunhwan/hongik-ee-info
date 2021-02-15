package info.hongik.ee.controller;

import info.hongik.ee.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api")
public class FieldApiController {
    private final NoticeService noticeService;

    @Autowired
    public FieldApiController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @GetMapping("field")
    public String field() {
        System.out.println("get field");
        return "field";
    }

}
