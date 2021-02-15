package info.hongik.ee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FieldApiController {

    @GetMapping("api/field")
    public String field() {
        return "field";
    }

}
