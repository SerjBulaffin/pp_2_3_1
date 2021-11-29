package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EnterController {

    @GetMapping("/")
    public String enteringWebSite() {
        return "index";
    }

}
