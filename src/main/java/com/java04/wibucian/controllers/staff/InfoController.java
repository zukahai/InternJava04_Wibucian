package com.java04.wibucian.controllers.staff;

import com.java04.wibucian.dtos.ShiftDTO;
import com.java04.wibucian.models.Employee;
import com.java04.wibucian.security.CustomUserDetail;
import com.java04.wibucian.services.ShiftService;
import com.java04.wibucian.vos.EmployeeUpdateVO;
import com.java04.wibucian.vos.StaffShiftVO;
import org.springframework.beans.factory.annotation.Autowired;
import com.java04.wibucian.services.EmployeeService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

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

    //    @RequestMapping(method = RequestMethod.POST, value = "/shift")
    //    @ResponseBody
    //    public ResponseEntity<ShiftDTO> staffRequestShift(@Valid StaffShiftVO shiftVO) {
    //        // TODO: Fetch employeeID
    //        shiftVO.setIdEmployee("Employee00002");
    //        return ResponseEntity.ok()
    //                             .body(this.shiftService.createShift(shiftVO,
    //                                                                 shiftVO
    //                                                                 .getIdEmployee()));
    //    }

    @PostMapping(value = "/edit", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public void updateEmployee(HttpServletResponse response,
                               @Valid EmployeeUpdateVO employeeUpdateVO,
                               @ModelAttribute("employee") Employee employee) throws
            IOException {
        employeeService.update(employee, employeeUpdateVO);
        response.sendRedirect("/staff/info");
    }
}
