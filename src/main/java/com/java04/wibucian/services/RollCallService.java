package com.java04.wibucian.services;

import com.java04.wibucian.commons.Utils;
import com.java04.wibucian.models.Employee;
import com.java04.wibucian.models.RollCall;
import com.java04.wibucian.models.Shift;
import com.java04.wibucian.repositories.RollCallRepository;
import com.java04.wibucian.repositories.ShiftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RollCallService {
    @Autowired
    private RollCallRepository rollCallRepository;

    @Autowired
    private ShiftRepository shiftRepository;

    public void createRollCall(Shift shift) {
        RollCall rollCall = new RollCall();
        rollCall.setShift(shift);
        rollCall.setRollCallTime(Utils.getCurrentDate()
                                      .toInstant());
        this.rollCallRepository.save(rollCall);
    }

    public Optional<RollCall> getByShift(Shift shift) {
        return this.rollCallRepository.findByShift(shift);
    }

    public List<Map<String, Object>> getRollCallDetailForADate(Date date) {
        List<Shift> shifts =
                this.shiftRepository.findAllByRequestShiftAndShiftDateBetween(false, date,
                                                                              date);
        return shifts.stream()
                     .map(shift -> {
                         Employee employee = shift.getEmployeeChange() == null
                                 ? shift.getEmployee()
                                 : shift.getEmployeeChange();
                         RollCall rollCall = shift.getRollCall();

                         Map<String, Object> dto = new HashMap<>();
                         dto.put("employeeId", employee.getId());
                         dto.put("employeeName", employee.getName());
                         dto.put("shiftId", shift.getId());
                         dto.put("shiftCode", shift.getShiftCode());
                         dto.put("rollCallAt", rollCall != null
                                 ? rollCall.getRollCallTime()
                                 : null);
                         return dto;
                     })
                     .toList();
    }
}
