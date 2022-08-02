package com.java04.wibucian.controllers;

import com.java04.wibucian.models.Category;
import com.java04.wibucian.services.CategoryService;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    @RequestMapping(value = "/store", method = RequestMethod.POST)
    public String store(@ModelAttribute("category") Category category) {
        categoryService.save(category);
        return "redirect:/admin/category/";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable int id, ModelMap modelMap) {
        modelMap.addAttribute("category", categoryService.findById(id));
        return "admin/category/edit";
    }
     @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@ModelAttribute("category") Category category) {
        System.out.println(category);
        categoryService.updateById(category);
        return "redirect:/admin/category/";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable int id) {
        categoryService.deleteById(id);
        return "redirect:/admin/category/";
    }


}
