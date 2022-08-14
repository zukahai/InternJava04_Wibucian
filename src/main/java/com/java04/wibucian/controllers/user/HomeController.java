package com.java04.wibucian.controllers.user;

import com.java04.wibucian.dtos.EmployeeDTO;
import com.java04.wibucian.models.Employee;
import com.java04.wibucian.services.EmployeeService;
import com.java04.wibucian.services.HomeService;
import com.java04.wibucian.vos.EmployeeQueryVO;
import com.java04.wibucian.vos.EmployeeUpdateVO;
import com.java04.wibucian.vos.EmployeeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.util.HashMap;

@Validated
@Controller
@RequestMapping("")
public class HomeController {

    @Autowired
    private HomeService homeService;

    @GetMapping("/")
    public String Home(ModelMap modelMap) throws Exception {
        modelMap.addAttribute("typetables", homeService.findAllTypeProduct());
        modelMap.addAttribute("products", homeService.findNumberRandomProduct(4));
        return "user/index";
    }
    @GetMapping("/product")
    public String Product(ModelMap modelMap) throws Exception {
        modelMap.addAttribute("typetables", homeService.findAllTypeProduct());
        modelMap.addAttribute("products", homeService.findAllProduct());
        return "user/product";
    }

    @GetMapping("/about")
    public String About(ModelMap modelMap) throws Exception {
        modelMap.addAttribute("employees", homeService.findAllEmployee());
        return "user/about";
    }
}
