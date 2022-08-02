package com.java04.wibucian.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(path = "/")
public class HomeController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        System.out.println("HomeController.index()");
        return "index";
    }
    //home route
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(ModelMap model) {
        System.out.println("HomeController.home()");
        model.addAttribute("message", "Hello World!");
        return "home";
    }


}
