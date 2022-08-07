package com.java04.wibucian.controllers.staff;

import com.java04.wibucian.dtos.ShiftDTO;
import com.java04.wibucian.services.ShiftService;
import com.java04.wibucian.vos.ShiftVO;
import com.java04.wibucian.vos.StaffShiftVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
@RequestMapping("/staff")
public class StaffController {

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
}
