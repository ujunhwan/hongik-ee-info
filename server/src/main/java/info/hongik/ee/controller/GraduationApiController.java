package info.hongik.ee.controller;


import info.hongik.ee.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class GraduationApiController {
    private final NoticeService noticeService;

    @Autowired
    public GraduationApiController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @GetMapping("grad")
    public String grad() {
        System.out.println("get grad");
        return "grad";
    }


}
