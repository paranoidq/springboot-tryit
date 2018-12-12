package me.test.springboottryit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author paranoidq
 * @since 1.0.0
 */
@Controller
public class TestSecurityController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }

    @RequestMapping("/loging")
    public String login() {
        return "login";
    }
}
