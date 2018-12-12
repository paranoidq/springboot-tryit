package me.test.springboottryit.controller.exception;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author paranoidq
 * @since 1.0.0
 */
@Controller
public class TestController {

    @RequestMapping("/errortest")
    public void test() throws Exception {
        throw new Exception("message");
    }
}
