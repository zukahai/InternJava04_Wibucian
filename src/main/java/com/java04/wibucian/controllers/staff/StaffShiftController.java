package com.java04.wibucian.controllers.staff;

import com.java04.wibucian.commons.Constant;
import com.java04.wibucian.commons.DayOfWeek;
import com.java04.wibucian.commons.ShiftOfDay;
import com.java04.wibucian.commons.Utils;
import com.java04.wibucian.dtos.ShiftDTO;
import com.java04.wibucian.models.Employee;
import com.java04.wibucian.security.CustomUserDetail;
import com.java04.wibucian.services.ShiftService;
import com.java04.wibucian.services.WorkPlanService;
import com.java04.wibucian.validations.annotation.ValidISO8061Date;
import com.java04.wibucian.vos.StaffShiftVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.bind.DatatypeConverter;
import java.util.*;

@Controller
@RequestMapping("/staff/shift")
public class StaffShiftController {
    @Autowired
    private ShiftService shiftService;
    @Autowired
    private WorkPlanService workPlanService;

    @ModelAttribute("employee")
    public Employee getEmployee(Authentication authentication) {
        return ((CustomUserDetail) authentication.getPrincipal()).getEmployee();
    }

    @ModelAttribute("selectSubmenu")
    public String setSelectSubmenu() {
        return "shift";
    }

    // toàn bộ của staff

    /**
     * Xem lịch làm việc
     *
     * @return
     */
    @GetMapping("/all")
    public String getShiftHistory() {
        return "staff/shift/index";
    }

    /**
     * Trang đăng ký ca làm của staff
     *
     * @param model
     *
     * @return
     */
    @GetMapping("/request")
    public String getOwnShiftRequestForNextWeek(
            @ModelAttribute("employee") Employee employee, Model model) {
        boolean isInShiftRequestTime = this.shiftService.isInShiftRequestTime();
        boolean isInShiftApproveTime = this.shiftService.isInShiftApproveTime();
        boolean isAlreadyApproved = this.shiftService.isAlreadyApprovedForNextWeek();
        if (isInShiftRequestTime || isInShiftApproveTime) {
            Calendar firstDayOfNextWeek = Utils.getFirstDayOfNextWeek();
            Calendar lastDayOfNextWeek = Utils.getLastDayOfNextWeek();
            model.addAttribute("totalNormalRequest",
                               this.shiftService.getNormalRequestOfEmployeeForDateBetween(
                                       employee, firstDayOfNextWeek, lastDayOfNextWeek));
            model.addAttribute("daysOfWeek", DayOfWeek.values());
            model.addAttribute("shiftsOfDay", ShiftOfDay.values());
            model.addAttribute("shiftRequestsForNextWeek",
                               this.shiftService.getAllShiftRequestsForNextWeak());
            model.addAttribute("workPlansForNextWeek",
                               this.workPlanService.getWorkPlanForNextWeek());

            model.addAttribute("weekStart",
                               Utils.getDateFormat(firstDayOfNextWeek.getTime(),
                                                   Constant.DD_MM_YYYY_FORMAT));
            model.addAttribute("weekEnd", Utils.getDateFormat(lastDayOfNextWeek.getTime(),
                                                              Constant.DD_MM_YYYY_FORMAT));
            model.addAttribute("employeeId", employee.getId());
            model.addAttribute("weekDayMapping",
                               Utils.getWeekDayMapping(firstDayOfNextWeek,
                                                       lastDayOfNextWeek));
        }
        model.addAttribute("isInShiftRequestTime", isInShiftRequestTime);
        model.addAttribute("isInShiftApproveTime", isInShiftApproveTime);
        model.addAttribute("isAlreadyApproved", isAlreadyApproved);
        return "staff/shift/request";
    }

    /**
     * Tạo mới ca làm việc của staff
     *
     * @param staffShiftVO
     *
     * @return
     */
    @PostMapping("/request")
    @ResponseBody
    public ResponseEntity<ShiftDTO> staffCreateShift(
            @ModelAttribute("employee") Employee employee,
            @Valid StaffShiftVO staffShiftVO) {
        staffShiftVO.setIdEmployee(employee.getId());
        return ResponseEntity.ok()
                             .body(this.shiftService.createShift(staffShiftVO,
                                                                 staffShiftVO.getIdEmployee()));
    }

    /**
     * Xóa ca làm việc của staff
     *
     * @param shiftId
     *
     * @return
     */
    @DeleteMapping("/request/{shiftId}")
    @ResponseBody
    public ResponseEntity<Boolean> staffDeleteShift(
            @ModelAttribute("employee") Employee employee,
            @Valid @PathVariable("shiftId") String shiftId) {
        return ResponseEntity.ok()
                             .body(this.shiftService.delete(shiftId, employee));
    }

    /**
     * Lấy danh sách các ca làm việc trong giới hạn ngày
     *
     * @param startTime Ngày đầu tiên
     * @param endTime   Ngày cuối cùng
     *
     * @return
     */
    @GetMapping()
    @ResponseBody
    public ResponseEntity<List<ShiftDTO>> getAllShiftBetween(
            @ModelAttribute("employee") Employee employee,
            @Valid @ValidISO8061Date @RequestParam("start") String startTime,
            @Valid @ValidISO8061Date @RequestParam("end") String endTime) {
        Calendar start = DatatypeConverter.parseDateTime(startTime);
        Calendar end = DatatypeConverter.parseDateTime(endTime);
        Date a = start.getTime();
        Date b = end.getTime();
        return ResponseEntity.ok()
                             .body(this.shiftService.findAllShiftsOfEmployeeBetween(
                                     employee, start, end));
    }
}
