package shop.mtcoding.orange.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class indexController {

    @GetMapping("/main")
    public String main() {
        return "main";
    }
}
