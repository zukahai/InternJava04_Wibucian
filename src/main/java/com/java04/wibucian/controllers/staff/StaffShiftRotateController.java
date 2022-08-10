package com.java04.wibucian.controllers.staff;

import com.java04.wibucian.models.Employee;
import com.java04.wibucian.security.CustomUserDetail;
import com.java04.wibucian.services.ShiftRotateService;
import com.java04.wibucian.vos.ShiftRotateUpdateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/staff/shiftRotate")
public class StaffShiftRotateController {

    @Autowired
    private ShiftRotateService shiftRotateService;

    @ModelAttribute("employee")
    public Employee getEmployee(Authentication authentication) {
        return ((CustomUserDetail) authentication.getPrincipal()).getEmployee();
    }

    @ModelAttribute("selectSubmenu")
    public String setSelectSubmenu() {
        return "shiftRotate";
    }

    @GetMapping("/my")
    public String getMyShiftRotate() {
        return "staff/shiftRotate/my";
    }

    @GetMapping("/request")
    public String getMyShiftRotateRequest() {
        return "staff/shiftRotate/request";
    }

    @GetMapping("/request/api")
    @ResponseBody
    public ResponseEntity<Map<?, ?>> getShiftRotatesToBeAccepted(
            @ModelAttribute("employee") Employee employee) {
        return ResponseEntity.ok()
                             .body(Map.of("data",
                                          this.shiftRotateService.getAllShiftRotatesToBeAccepted(
                                                  employee)));
    }

    @PatchMapping(value = "/accept/{shiftRotateId}",
                 consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<Boolean> acceptShiftRotate(
            @ModelAttribute("employee") Employee employee,
            @Valid ShiftRotateUpdateVO shiftRotateUpdateVO) {
        return ResponseEntity.ok()
                             .body(this.shiftRotateService.acceptShiftRotateRequest(
                                     employee, shiftRotateUpdateVO));
    }

    @PostMapping(value = "/cancel/{shiftRotateId}",
                 consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<Boolean> cancelShiftRotate(
            @ModelAttribute("employee") Employee employee,
            @PathVariable("shiftRotateId") String shiftRotateId) {
        return ResponseEntity.ok()
                             .body(this.shiftRotateService.cancelShiftRotateRequest(
                                     employee, shiftRotateId));
    }
}
