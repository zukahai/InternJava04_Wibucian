//package com.java04.wibucian.controllers;
//
//import com.java04.wibucian.commons.Role;
//import com.java04.wibucian.models.Employee;
//import com.java04.wibucian.security.CustomUserDetail;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import java.util.Collection;
//import java.util.Set;
//
//@Controller
//@RequestMapping(value = {"", "/"})
//public class MainController {
//
//    @GetMapping
//    public String homePage(Authentication authentication) {
//        if (authentication == null || !authentication.isAuthenticated()) {
//            // unauth home page
//            return "redirect:/";
//        }
//        Employee employee = ((CustomUserDetail) authentication.getPrincipal()).getEmployee();
//        if (employee == null) {
//            return "redirect:/admin";
//        }
//        return "redirect:/staff";
//    }
//
//    @GetMapping(value = "login")
//    public String loginPage() {
//        return "admin/employee/login";
//    }
//}
