package me.test.springboottryit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author paranoidq
 * @since 1.0.0
 */
@Controller
public class TestTemplateController {

    @RequestMapping("/testtemplate")
    public String index(ModelMap map) {
        map.addAttribute("host", "localhost");
        return "testtemplate";
    }
}
