package com.java04.wibucian.controllers;

import com.java04.wibucian.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin/category")

public class AdminCategoryController {
    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value ="/" ,method = RequestMethod.GET)
    public String index(ModelMap modelMap ) {
        modelMap.addAttribute("categories", categoryService.findAll());
        return "admin/category/index";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create() {
        return "admin/category/create";
    }
    @RequestMapping(value = "/store", method = RequestMethod.GET)
    public String store() {
        return "admin/category/edit";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable int id, ModelMap modelMap) {
        return "admin/category/edit";
    }
     @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(int id) {
        return "admin/category/delete";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(int id) {
        return "admin/category/delete";
    }


}
