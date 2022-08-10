package com.java04.wibucian.controllers.admin;

import com.java04.wibucian.commons.Constant;
import com.java04.wibucian.commons.DayOfWeek;
import com.java04.wibucian.commons.ShiftOfDay;
import com.java04.wibucian.commons.Utils;
import com.java04.wibucian.dtos.TypeTableDTO;
import com.java04.wibucian.models.TypeTable;
import com.java04.wibucian.models.WorkPlan;
import com.java04.wibucian.services.TypeTableService;
import com.java04.wibucian.services.WorkPlanService;
import com.java04.wibucian.vos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Validated
@Controller
@RequestMapping("admin/workPlan")
public class WorkPlanController {

    @Autowired
    private WorkPlanService workPlanService;

    /**
     * Request mapping GET admin/workPlan/, trả về trang lên kế hoạch làm việc cho tuần
     * tiếp theo của admin
     *
     * @param model Model object
     *
     * @return
     */
    @GetMapping(value = {"", "/"})
    public String getWorkPlanPage(Model model) {
        boolean isInWorkPlanTime = this.workPlanService.isInWorkPlanTime();
        if (isInWorkPlanTime) {
            Calendar firstDayOfNextWeek = Utils.getFirstDayOfNextWeek();
            Calendar lastDayOfNextWeek = Utils.getLastDayOfNextWeek();
            model.addAttribute("daysOfWeek", DayOfWeek.values());
            model.addAttribute("shiftsOfDay", ShiftOfDay.values());
            model.addAttribute("weekStart",
                               Utils.getDateFormat(firstDayOfNextWeek.getTime(),
                                                   Constant.DD_MM_YYYY_FORMAT));
            model.addAttribute("weekEnd", Utils.getDateFormat(lastDayOfNextWeek.getTime(),
                                                              Constant.DD_MM_YYYY_FORMAT));
            Map<Integer, String> weekDayMapping = new HashMap<>();
            while (firstDayOfNextWeek.compareTo(lastDayOfNextWeek) <= 0) {
                weekDayMapping.put(firstDayOfNextWeek.get(Calendar.DAY_OF_WEEK),
                                   Utils.getDateFormat(firstDayOfNextWeek.getTime(),
                                                       Constant.DD_MM_YYYY_FORMAT));
                firstDayOfNextWeek.add(Calendar.DATE, 1);
            }
            Map<DayOfWeek, Map<ShiftOfDay, List<WorkPlan>>> workPlansForNextWeek =
                    this.workPlanService.getWorkPlanForNextWeek();
            model.addAttribute("workPlansForNextWeek", workPlansForNextWeek);
            model.addAttribute("weekDayMapping", weekDayMapping);

        }
        model.addAttribute("isInWorkPlanTime", isInWorkPlanTime);
        return "admin/workPlan/index";
    }

    @GetMapping("/schedule")
    public String schedule() {
        this.workPlanService.scheduleCreateWorkPlanForNextWeek();
        return "abc";
    }

    /**
     * Request mapping PATCH admin/workPlan/{workPlanId}, thay đổi kế hoạch làm việc cho
     * ca nhất định
     *
     * @param workPlanId Mã kế hoạch làm việc
     * @param workPlanVO Đối tượng thay đổi
     *
     * @return
     */
    @PatchMapping("/{workPlanId}")
    public ResponseEntity<WorkPlan> changeWorkPlan(
            @NotNull @PathVariable("workPlanId") String workPlanId,
            @Valid WorkPlanVO workPlanVO) {
        return ResponseEntity.ok()
                             .body(this.workPlanService.changeWorkPlan(workPlanId,
                                                                       workPlanVO));
    }
}
