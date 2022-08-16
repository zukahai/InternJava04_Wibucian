package com.java04.wibucian.controllers.admin;

import com.java04.wibucian.commons.Constant;
import com.java04.wibucian.commons.DayOfWeek;
import com.java04.wibucian.commons.ShiftOfDay;
import com.java04.wibucian.commons.Utils;
import com.java04.wibucian.dtos.ShiftDTO;
import com.java04.wibucian.models.Employee;
import com.java04.wibucian.services.EmployeeService;
import com.java04.wibucian.services.ShiftService;
import com.java04.wibucian.services.WorkPlanService;
import com.java04.wibucian.validations.annotation.ValidISO8061Date;
import com.java04.wibucian.vos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.DatatypeConverter;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Validated
@Controller
@RequestMapping("admin/shift")
public class ShiftController {
    @Autowired
    private ShiftService shiftService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private WorkPlanService workPlanService;

    @ModelAttribute("contextPath")
    public String getContextPath(HttpServletRequest request) {
        return request.getContextPath();
    }

    // tạo mới ca làm việc của admin
    @RequestMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
                    method = RequestMethod.POST, value = {"", "/"})
    @ResponseBody
    public ResponseEntity<ShiftDTO> save(@Valid AdminShiftVO shiftVO) {
        return ResponseEntity.ok()
                             .body(this.shiftService.createShift(shiftVO,
                                                                 shiftVO.getIdEmployee()));
    }

    // xóa ca làm việc của admin
    @DeleteMapping("/{shiftId}")
    @ResponseBody
    public ResponseEntity<Boolean> delete(
            @Valid @NotNull @PathVariable("shiftId") String shiftId) {
        return ResponseEntity.ok()
                             .body(shiftService.delete(shiftId, null));
    }

    // sửa ca làm việc của admin
    @RequestMapping(value = {"/{shiftId}"},
                    consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
                    method = RequestMethod.PATCH)
    @ResponseBody
    public ResponseEntity<Boolean> update(
            @Valid @NotNull @PathVariable("shiftId") String shiftId,
            @Valid ShiftUpdateVO shiftUpdateVO) {
        return ResponseEntity.ok()
                             .body(shiftService.update(shiftId, shiftUpdateVO));
    }

    //    @GetMapping("/{id}")
    //    public ShiftDTO getById(@Valid @NotNull @PathVariable("id") String id) {
    //        return shiftService.getById(id);
    //    }

    /**
     * Xem tất cả các đăng ký ca làm việc cho tuần tiếp theo
     *
     * @param model
     *
     * @return
     */
    @GetMapping("/request")
    public String getShiftRequestPage(Model model) {
        boolean isInShiftRequestTime = this.shiftService.isInShiftRequestTime();
        if (isInShiftRequestTime) {
            model.addAttribute("daysOfWeek", DayOfWeek.values());
            model.addAttribute("shiftsOfDay", ShiftOfDay.values());
            model.addAttribute("shiftRequestsForNextWeek",
                               this.shiftService.getAllShiftRequestsForNextWeak());
            model.addAttribute("workPlansForNextWeek",
                               this.workPlanService.getWorkPlanForNextWeek());
            Calendar firstDayOfNextWeek = Utils.getFirstDayOfNextWeek();
            Calendar lastDayOfNextWeek = Utils.getLastDayOfNextWeek();
            model.addAttribute("weekStart",
                               Utils.getDateFormat(firstDayOfNextWeek.getTime(),
                                                   Constant.DD_MM_YYYY_FORMAT));
            model.addAttribute("weekEnd", Utils.getDateFormat(lastDayOfNextWeek.getTime(),
                                                              Constant.DD_MM_YYYY_FORMAT));

        }
        model.addAttribute("isInShiftRequestTime", isInShiftRequestTime);
        return "admin/shift/request";
    }

    /**
     * Preview và chỉnh sửa các ca đăng ký làm việc trong tuần tiếp theo
     *
     * @param model
     *
     * @return
     */
    @GetMapping("/request/preview")
    public String getShiftApprovePage(Model model) {
        boolean isInShiftApproveTime = this.shiftService.isInShiftApproveTime();
        boolean isAlreadyApprovedForNextWeek =
                this.shiftService.isAlreadyApprovedForNextWeek();
        if (isInShiftApproveTime) {
            List<String> allEmployeesID = this.employeeService.findAll()
                                                              .stream()
                                                              .map(Employee::getId)
                                                              .toList();
            model.addAttribute("allEmployeesID", allEmployeesID);
            model.addAttribute("daysOfWeek", DayOfWeek.values());
            model.addAttribute("shiftsOfDay", ShiftOfDay.values());
            model.addAttribute("shiftRequestsForNextWeek",
                               this.shiftService.getAllShiftRequestsForNextWeak());
            model.addAttribute("workPlansForNextWeek",
                               this.workPlanService.getWorkPlanForNextWeek());
            Calendar firstDayOfNextWeek = Utils.getFirstDayOfNextWeek();
            Calendar lastDayOfNextWeek = Utils.getLastDayOfNextWeek();
            model.addAttribute("weekStart",
                               Utils.getDateFormat(firstDayOfNextWeek.getTime(),
                                                   Constant.DD_MM_YYYY_FORMAT));
            model.addAttribute("weekEnd", Utils.getDateFormat(lastDayOfNextWeek.getTime(),
                                                              Constant.DD_MM_YYYY_FORMAT));
            model.addAttribute("weekDayMapping",
                               Utils.getWeekDayMapping(firstDayOfNextWeek,
                                                       lastDayOfNextWeek));
        }
        model.addAttribute("isInShiftApproveTime", isInShiftApproveTime);
        model.addAttribute("isAlreadyApprovedForNextWeek", isAlreadyApprovedForNextWeek);
        return "admin/shift/preview";
    }

    /**
     * Xem lịch làm việc
     *
     * @return
     */
    @GetMapping("/all")
    public String getShiftHistory() {
        return "/admin/shift/index";
    }

    /**
     * Chốt lịch làm việc cho tuần tiếp theo
     *
     * @param contextPath
     *
     * @return
     */
    @GetMapping("/request/approve")
    public String approveShiftRequest(@ModelAttribute("contextPath") String contextPath) {
        this.shiftService.approveShiftRequest();
        return "redirect:" + contextPath + "/admin/shift/request/preview";
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
            @Valid @ValidISO8061Date @RequestParam("start") String startTime,
            @Valid @ValidISO8061Date @RequestParam("end") String endTime) {
        Calendar start = DatatypeConverter.parseDateTime(startTime);
        Calendar end = DatatypeConverter.parseDateTime(endTime);
        return ResponseEntity.ok()
                             .body(this.shiftService.findAllShiftsBetween(start, end));
    }
}
