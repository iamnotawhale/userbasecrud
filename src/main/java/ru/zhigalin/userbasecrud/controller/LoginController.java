package ru.zhigalin.userbasecrud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/")
public class LoginController {

    @GetMapping(value = "/")
    public String index() {
        return "loginPage";
    }

    @GetMapping(value = "login")
    public String login() {
        return "loginPage";
    }

}
