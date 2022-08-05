package com.java04.wibucian.controllers.admin;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("")
public class AdminController {
    @GetMapping("/admin/")
    public String info(Principal principal){
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        return principal.getName();
    }

    @GetMapping("/staff/")
    public String staff(){
        return "staff";
    }
}
