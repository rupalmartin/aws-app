package com.aws;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Locale;

@Controller
public class AWSController {

    @RequestMapping(value = "index", method = {RequestMethod.GET,
            RequestMethod.POST})
    public String index(Locale locale, Model model) {
        return "index";
    }

    @RequestMapping(value = "read", method = {RequestMethod.GET,
            RequestMethod.POST})
    public String read(Locale locale, Model model) {
        return "read";
    }
    @RequestMapping(value = "update", method = {RequestMethod.GET,
            RequestMethod.POST})
    public String update(Locale locale, Model model) {
        return "update";
    }


}
