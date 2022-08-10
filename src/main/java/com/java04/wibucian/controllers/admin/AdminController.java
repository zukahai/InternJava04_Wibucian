package com.java04.wibucian.controllers.admin;

import com.java04.wibucian.dtos.AccountDTO;
import com.java04.wibucian.models.Account;
import com.java04.wibucian.models.Employee;
import com.java04.wibucian.repositories.AccountRepository;
import com.java04.wibucian.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping("")
public class AdminController {

    @GetMapping("/admin/")
    public String info(Principal principal){
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        String id=principal.getName();
        return id;
    }

    @GetMapping("/staff/")
    public String staff(){
        return "staff";
    }
}
