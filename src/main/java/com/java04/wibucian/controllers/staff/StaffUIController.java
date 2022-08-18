package com.java04.wibucian.controllers.staff;

import com.java04.wibucian.dtos.EmployeeDTO;
import com.java04.wibucian.models.Account;
import com.java04.wibucian.models.Employee;
import com.java04.wibucian.repositories.AccountRepository;
import com.java04.wibucian.security.CustomUserDetail;
import com.java04.wibucian.services.AccountService;
import com.java04.wibucian.services.EmployeeService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("staff")
public class StaffUIController {

    @GetMapping(value = {"", "/"})
    public String infoPage(Authentication authentication, Model model) {
        return "redirect:/staff/info";
    }
}
