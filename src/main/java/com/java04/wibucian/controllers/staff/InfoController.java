package com.java04.wibucian.controllers.staff;

import com.java04.wibucian.dtos.EmployeeDTO;
import com.java04.wibucian.dtos.ShiftDTO;
import com.java04.wibucian.models.Account;
import com.java04.wibucian.models.Employee;
import com.java04.wibucian.repositories.AccountRepository;
import com.java04.wibucian.security.CustomUserDetail;
import com.java04.wibucian.services.ShiftService;
import com.java04.wibucian.vos.StaffEmployeeUpdateVO;
import org.springframework.beans.factory.annotation.Autowired;
import com.java04.wibucian.services.EmployeeService;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/staff/info")
public class InfoController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ShiftService shiftService;

    @ModelAttribute("employee")
    public Employee getEmployee(Authentication authentication) {
        return ((CustomUserDetail) authentication.getPrincipal()).getEmployee();
    }

    @ModelAttribute("selectSubmenu")
    public String setSelectSubmenu() {
        return "account";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getStaffProfilePage() {
        return "staff/info/index";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/edit")
    public String editStaffProfilePage() {
        return "staff/info/edit";
    }

    @PostMapping(value = "/edit", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public void updateEmployee(
            @ModelAttribute(value = "employee", binding = false) Employee employee,
            HttpServletResponse response, @Valid StaffEmployeeUpdateVO employeeUpdateVO) throws
            IOException {
        employeeService.update(employee, employeeUpdateVO);
        response.sendRedirect("/staff/info");
    }
}
