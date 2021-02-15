package info.hongik.ee.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GraduationApiController {

    // hongikee.info/api/grad
    @GetMapping("api/grad")
    public String grad() {
        return "grad";
    }


}
