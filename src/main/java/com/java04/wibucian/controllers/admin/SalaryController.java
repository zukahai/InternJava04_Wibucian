package com.java04.wibucian.controllers.admin;

import com.java04.wibucian.commons.Constant;
import com.java04.wibucian.commons.Utils;
import com.java04.wibucian.models.Employee;
import com.java04.wibucian.models.RollCall;
import com.java04.wibucian.services.EmployeeService;
import com.java04.wibucian.services.RollCallService;
import com.java04.wibucian.services.SalaryService;
import com.java04.wibucian.validations.annotation.ValidDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@Controller
@Validated
@RequestMapping("/admin/salary")
public class SalaryController {
    @Autowired
    private SalaryService salaryService;
    @Autowired
    private RollCallService rollCallService;
    @Autowired
    private EmployeeService employeeService;

    @ModelAttribute("selectSubmenu")
    public String setSelectedSubmenu() {
        return "salary";
    }

    @ModelAttribute("current")
    public Calendar getCurrentDate() {
        return Utils.getCurrentDate();
    }

    @GetMapping
    public String getSalaryMainPage(Model model,
                                    @ModelAttribute("current") Calendar current) {
        current.add(Calendar.MONTH, -1);
        model.addAttribute("currentMonth", Utils.getDateFormat(current.getTime(),
                                                               Constant.YYYY_MM_FORMAT));
        return "admin/salary/index";
    }

    @GetMapping(value = "calculate")
    public String getSalaryCalculatePage(Model model,
                                         @ModelAttribute("current") Calendar current) {
        model.addAttribute("currentMonth", Utils.getDateFormat(current.getTime(),
                                                               Constant.MM_YYYY_FORMAT));
        model.addAttribute("employeeList", this.employeeService.findAll());
        return "admin/salary/calculate";
    }

    @GetMapping(value = "roll-call")
    public String getRollCallPage(Model model,
                                  @ModelAttribute("current") Calendar current) {
        model.addAttribute("currentMonth", Utils.getDateFormat(current.getTime(),
                                                               Constant.YYYY_MM_DD_FORMAT));
        return "admin/salary/roll-call";
    }

    @GetMapping(value = "roll-call/summarize")
    @ResponseBody
    public ResponseEntity<Map<?, ?>> getRollCallForADay(
            @Valid @DateTimeFormat(pattern = "yyyy-MM-dd") @RequestParam(
                    "date") String dateString) {
        Calendar calendar = Utils.getCalendarInstanceFromFormat(dateString,
                                                                Constant.YYYY_MM_DD_FORMAT);
        return ResponseEntity.ok()
                             .body(Map.of("data",
                                          this.rollCallService.getRollCallDetailForADate(
                                                  calendar.getTime())));
    }

    @GetMapping(value = "summarize")
    @ResponseBody
    public ResponseEntity<Map<?, ?>> getSalarySummary(
            @Valid @RequestParam("month") int month,
            @Valid @RequestParam("year") int year) {
        return ResponseEntity.ok()
                             .body(Map.of("data",
                                          this.salaryService.getSalarySummarizeForAllEmployeeForYearAndMonth(
                                                  year, month - 1)));
    }

    @GetMapping(value = "/calculate/api")
    @ResponseBody
    public ResponseEntity<Map<?, ?>> getSalaryForEmployee(
            @RequestParam("employeeIdList") String employeeIdList) {
        List<String> employeeIds = Arrays.stream(employeeIdList.split(","))
                                         .toList();
        Calendar current = Utils.currentDateWithoutTime();
        return ResponseEntity.ok()
                             .body(Map.of("data",
                                          this.salaryService.getSalarySummarizeForEmployeesUpToDate(
                                                  employeeIds, current)));
    }

    @GetMapping(value = "employee")
    @ResponseBody
    public ResponseEntity<Map<?, ?>> getEmployeeList() {
        return ResponseEntity.ok()
                             .body(Map.of("data", this.employeeService.findAll()
                                                                      .stream()
                                                                      .map(employee -> {
                                                                          Map<String,
                                                                                  Object>
                                                                                  map =
                                                                                  new HashMap<>();
                                                                          map.put("employeeId",
                                                                                  employee.getId());
                                                                          map.put("employeeName",
                                                                                  employee.getName());
                                                                          return map;
                                                                      })
                                                                      .toList()));
    }
}
