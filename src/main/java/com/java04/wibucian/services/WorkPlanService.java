package com.java04.wibucian.services;

import com.java04.wibucian.commons.*;
import com.java04.wibucian.exception.BadRequestException;
import com.java04.wibucian.exception.NotFoundException;
import com.java04.wibucian.models.Shift;
import com.java04.wibucian.models.WorkPlan;
import com.java04.wibucian.repositories.WorkPlanRepository;
import com.java04.wibucian.vos.WorkPlanVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class WorkPlanService {
    private final int WORK_PLAN_START_DAY = Constant.WORK_PLAN_START_DAY;
    private final int WORK_PLAN_END_DAY = Constant.WORK_PLAN_END_DAY;
    @Autowired
    private WorkPlanRepository workPlanRepository;

    public Map<DayOfWeek, Map<ShiftOfDay, List<WorkPlan>>> getWorkPlanForNextWeek() {
        Calendar firstDayOfNextWeek = Utils.getFirstDayOfNextWeek();
        Calendar lastDayOfNextWeek = Utils.getLastDayOfNextWeek();
        List<WorkPlan> workPlanList = this.workPlanRepository.findAllByShiftDateBetween(
                firstDayOfNextWeek.getTime(), lastDayOfNextWeek.getTime());
        return convertWorkPlanList(workPlanList);
    }

    public WorkPlan changeWorkPlan(String workPlanId, WorkPlanVO workPlanVO) {
        if (!isInWorkPlanTime()) {
            throw new BadRequestException("Không trong thời gian lập kế hoạch làm việc");
        }
        WorkPlan workPlan = this.requiredOne(workPlanId);
        if (!canChangeWorkPlan(workPlan.getShiftDate())) {
            throw new BadRequestException("Không thể thay đổi kế hoạch làm việc");
        }
        BeanUtils.copyProperties(workPlanVO, workPlan);
        workPlan = workPlanRepository.save(workPlan);
        return workPlan;
    }

    private Map<DayOfWeek, Map<ShiftOfDay, List<WorkPlan>>> convertWorkPlanList(
            List<WorkPlan> workPlanList) {
        Map<DayOfWeek, Map<ShiftOfDay, List<WorkPlan>>> result = new HashMap<>();
        Calendar utilCalendar = Calendar.getInstance();
        for (WorkPlan workPlan : workPlanList) {
            utilCalendar.setTime(workPlan.getShiftDate());
            DayOfWeek dayOfWeek = DayOfWeekConversion.getDayOfWeekFromValue(
                    utilCalendar.get(Calendar.DAY_OF_WEEK));
            ShiftOfDay shiftOfDay =
                    ShiftOfDayConversion.getShiftOfDayFromValue(workPlan.getShiftCode());

            // check xem dữ liệu của ngày làm việc có tồn tại không
            Map<ShiftOfDay, List<WorkPlan>> shiftOfDayToWorkPlanListMap =
                    result.get(dayOfWeek);
            if (shiftOfDayToWorkPlanListMap == null) {
                shiftOfDayToWorkPlanListMap = new HashMap<>();
            }

            // check xem dữ liệu của buổi làm việc có tồn tại không
            List<WorkPlan> workPlans = shiftOfDayToWorkPlanListMap.get(shiftOfDay);
            if (workPlans == null) {
                workPlans = new ArrayList<>();
            }

            // add dữ liệu
            workPlans.add(workPlan);
            shiftOfDayToWorkPlanListMap.put(shiftOfDay, workPlans);
            result.put(dayOfWeek, shiftOfDayToWorkPlanListMap);
        }
        return result;
    }

    public boolean isInWorkPlanTime() {
        Calendar currentDate = Utils.getCurrentDate();
        int dayOfWeek = currentDate.get(Calendar.DAY_OF_WEEK);
        return dayOfWeek >= this.WORK_PLAN_START_DAY
                && dayOfWeek <= this.WORK_PLAN_END_DAY;
    }

    private boolean canChangeWorkPlan(Date workPlanDate) {
        Calendar workPlanCalendar = Calendar.getInstance();
        workPlanCalendar.setTime(workPlanDate);
        Calendar firstDayOfNextWeek = Utils.getFirstDayOfNextWeek();
        Calendar lastDayOfNextWeek = Utils.getLastDayOfNextWeek();
        return workPlanCalendar.compareTo(firstDayOfNextWeek) >= 0
                && workPlanCalendar.compareTo(lastDayOfNextWeek) < 1;
    }


    /**
     * Mở lên kế hoạch làm việc cho tuần tiếp theo bằng một Scheduled job
     */
    @Scheduled(cron = Constant.WORK_PLAN_OPEN_CRON)
    public void scheduleCreateWorkPlanForNextWeek() {
        Calendar firstDayOfNextWeek = Utils.getFirstDayOfNextWeek();
        Calendar lastDayOfNextWeek = Utils.getLastDayOfNextWeek();

        List<WorkPlan> workPlansForNextWeek = new ArrayList<>();

        while (firstDayOfNextWeek.compareTo(lastDayOfNextWeek) <= 0) {
            Arrays.stream(ShiftOfDay.values())
                  .toList()
                  .forEach(shiftOfDay -> {
                      WorkPlan workPlan = new WorkPlan();
                      workPlan.setShiftDate(firstDayOfNextWeek.getTime());
                      workPlan.setShiftCode(shiftOfDay.getValue());
                      workPlan.setWork(true);
                      workPlansForNextWeek.add(workPlan);
                  });
            firstDayOfNextWeek.add(Calendar.DATE, 1);
        }
        workPlanRepository.saveAll(workPlansForNextWeek);
    }

    private WorkPlan requiredOne(String workPlanId) {
        return this.workPlanRepository.findById(workPlanId)
                                      .orElseThrow(() -> new NotFoundException(
                                              "Không tìm thấy kế hoạch làm việc"));
    }
}
