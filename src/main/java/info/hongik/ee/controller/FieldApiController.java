package info.hongik.ee.controller;

import info.hongik.ee.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


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
