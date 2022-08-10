package com.java04.wibucian.controllers.staff;

import com.java04.wibucian.dtos.EmployeeDTO;
import com.java04.wibucian.models.Account;
import com.java04.wibucian.models.Employee;
import com.java04.wibucian.repositories.AccountRepository;
import com.java04.wibucian.services.AccountService;
import com.java04.wibucian.services.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/staff/ui")
public class StaffUIController {
    private AccountRepository repository;
    private EmployeeService employeeService;

    public StaffUIController(AccountRepository repository, EmployeeService employeeService) {
        this.repository = repository;
        this.employeeService = employeeService;
    }

    @GetMapping("/update")
    public String updatePage(Principal principal, Model model){
        String id=principal.getName();

        Optional<Account> accountOptional=repository.findById(id);
        Employee employee=accountOptional.get().getEmployee();
        EmployeeDTO employeeDTO = employeeService.getById(employee.getId());
        employeeDTO.setIdEmployee(employee.getId());
        model.addAttribute("employee",employeeDTO);
        return "admin/employee/update";
    }
}