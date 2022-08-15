package com.java04.wibucian.controllers.admin;


import com.java04.wibucian.models.Employee;
import com.java04.wibucian.security.CustomUserDetail;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminMainController {

    @GetMapping(value = {"", "/"})
    public String updatePage(Authentication authentication, Model model) {
        return "redirect:/admin/shift/all";
    }
}
