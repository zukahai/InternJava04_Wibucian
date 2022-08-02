package com.java04.wibucian.controllers;

import com.java04.wibucian.models.Category;
import com.java04.wibucian.repositories.CategoryRepository;
import com.java04.wibucian.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;

@Controller
public class HomeController {
    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        ArrayList<Category> categories = new ArrayList<>();
        categories = (ArrayList<Category>) categoryService.findAll();
        for (Category category : categories) {
            System.out.println(category);
        }
        System.out.println("HomeController.index()");
        return "index";
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home() {
        ArrayList<Category> categories = new ArrayList<>();
        categories = (ArrayList<Category>) categoryService.findAll();
        for (Category category : categories) {
            System.out.println(category);
        }
        System.out.println("HomeController.index()");
        return "index";
    }





}
