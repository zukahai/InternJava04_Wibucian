package com.java04.wibucian.controllers.staff;

import com.java04.wibucian.commons.Constant;
import com.java04.wibucian.commons.DayOfWeek;
import com.java04.wibucian.commons.ShiftOfDay;
import com.java04.wibucian.commons.Utils;
import com.java04.wibucian.models.Employee;
import com.java04.wibucian.models.Shift;
import com.java04.wibucian.models.ShiftRotate;
import com.java04.wibucian.repositories.ShiftRotateRepository;
import com.java04.wibucian.security.CustomUserDetail;
import com.java04.wibucian.services.ShiftRotateService;
import com.java04.wibucian.services.ShiftService;
import com.java04.wibucian.vos.ShiftRotateUpdateVO;
import com.java04.wibucian.vos.ShiftRotateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/staff/shiftRotate")
public class StaffShiftRotateController {

    @Autowired
    private ShiftRotateService shiftRotateService;

    @Autowired
    private ShiftService shiftService;

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

    @GetMapping("/my/api")
    @ResponseBody
    public ResponseEntity<Map<?, ?>> getOwnShiftRotates(
            @ModelAttribute("employee") Employee employee) {
        return ResponseEntity.ok()
                             .body(Map.of("data",
                                          this.shiftRotateService.getOwnShiftRotates(
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

    @GetMapping(value = "/cancel/{shiftRotateId}")
    public ResponseEntity<Boolean> cancelShiftRotate(
            @ModelAttribute("employee") Employee employee,
            @PathVariable("shiftRotateId") String shiftRotateId) {
        return ResponseEntity.ok()
                             .body(this.shiftRotateService.cancelShiftRotateRequest(
                                     employee, shiftRotateId));
    }

    @GetMapping(value = "/create")
    public String createShiftRotate(@RequestParam("shiftId") String shiftId,
                                    @ModelAttribute("employee") Employee employee,
                                    Model model) {
        Shift shift = this.shiftService.getById(shiftId);
        this.shiftRotateService.checkCanCreateShiftRotate(employee, shift);
        Calendar weekStartDay = Utils.getWeekStartFromDate(shift.getShiftDate());
        Calendar weekEndDay = Utils.getWeekEndFromDate(shift.getShiftDate());
        model.addAttribute("daysOfWeek", DayOfWeek.values());
        model.addAttribute("shiftsOfDay", ShiftOfDay.values());
        model.addAttribute("candidateShiftsToRotate",
                           this.shiftRotateService.getCandidateShiftToRotate(employee,
                                                                             shift,
                                                                             weekStartDay,
                                                                             weekEndDay));
        model.addAttribute("shift", shift);
        model.addAttribute("weekDayMapping",
                           Utils.getWeekDayMapping(weekStartDay, weekEndDay));

        return "staff/shiftRotate/create";
    }


    @PostMapping(value = "/create",
                 consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String createShiftRotate(@ModelAttribute("employee") Employee employee,
                                    @Valid ShiftRotateVO shiftRotateVO) {
        this.shiftRotateService.createShiftRotate(employee, shiftRotateVO);
        return "redirect:/staff/shiftRotate/my";
    }
}
