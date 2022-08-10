package com.java04.wibucian.controllers.staff;

import com.java04.wibucian.models.Employee;
import com.java04.wibucian.security.CustomUserDetail;
import com.java04.wibucian.services.AccountService;
import com.java04.wibucian.vos.AccountUpdateVO;
import org.springframework.beans.factory.annotation.Autowired;
import com.java04.wibucian.services.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/staff/account")
public class StaffAccountController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private AccountService accountService;

    @ModelAttribute("employee")
    public Employee getEmployee(Authentication authentication) {
        return ((CustomUserDetail) authentication.getPrincipal()).getEmployee();
    }

    @ModelAttribute("selectSubmenu")
    public String setSelectSubmenu() {
        return "account";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/change-password")
    public String getChangePasswordPage() {
        return "staff/account/change-password";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/change-password")
    @ResponseBody
    public ResponseEntity<Boolean> staffRequestShift(
            @ModelAttribute("employee") Employee employee,
            @Valid AccountUpdateVO accountUpdateVO) {
        // TODO: Fetch employeeID
//        this.accountService.changePassword(employee, accountUpdateVO);
        return ResponseEntity.ok()
                             .body(true);
    }
}
