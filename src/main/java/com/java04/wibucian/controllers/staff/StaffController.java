package com.java04.wibucian.controllers.staff;

import com.java04.wibucian.dtos.ShiftDTO;
import com.java04.wibucian.services.ShiftService;
import com.java04.wibucian.vos.ShiftVO;
import com.java04.wibucian.vos.StaffShiftVO;
import org.springframework.beans.factory.annotation.Autowired;
import com.java04.wibucian.controllers.admin.EmployeeController;
import com.java04.wibucian.services.EmployeeService;
import com.java04.wibucian.vos.EmployeeVO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping("/staff")
public class StaffController {

    private EmployeeService employeeService;

    @Autowired
    private ShiftService shiftService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<String> getStaffMainPage() {
        return ResponseEntity.ok()
                             .body("staff main");
    }

    @RequestMapping(method = RequestMethod.POST, value = "/shift")
    @ResponseBody
    public ResponseEntity<ShiftDTO> staffRequestShift(@Valid StaffShiftVO shiftVO) {
        // TODO: Fetch employeeID
        shiftVO.setIdEmployee("Employee00002");
        return ResponseEntity.ok()
                             .body(this.shiftService.createShift(shiftVO,
                                                                 shiftVO.getIdEmployee()));
    }

    @PostMapping(
            value = "/update",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public void updateEmployee(HttpServletResponse response, EmployeeVO employeeVO) throws IOException {
        employeeService.save(employeeVO);
        response.sendRedirect("/staff");
    }
}
