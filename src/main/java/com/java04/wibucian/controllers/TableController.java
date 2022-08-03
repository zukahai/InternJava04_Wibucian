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
@RequestMapping("/admin/table")
public class TableController {
    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        System.out.println("TableController.index() called");
        return "admin/table/index";
    }
}
