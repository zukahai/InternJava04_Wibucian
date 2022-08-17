package com.java04.wibucian.controllers.user;

import com.java04.wibucian.services.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Validated
@Controller
@RequestMapping("")
public class HomeController {

    @Autowired
    private HomeService homeService;

    @GetMapping("/home")
    public String Home(ModelMap modelMap) throws Exception {
        modelMap.addAttribute("typetables", homeService.findAllTypeProductHaveProductNotNull());
        modelMap.addAttribute("products", homeService.findNumberRandomProduct(4));
        return "user/index";
    }
    @GetMapping("/product")
    public String Product(ModelMap modelMap) throws Exception {
        modelMap.addAttribute("typetables", homeService.findAllTypeProductHaveProductNotNull());
        modelMap.addAttribute("products", homeService.findAllProduct());
        return "user/product";
    }

    @GetMapping("/about")
    public String About(ModelMap modelMap) throws Exception {
        modelMap.addAttribute("employees", homeService.findAllEmployee());
        return "user/about";
    }
}
