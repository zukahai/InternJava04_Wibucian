package com.java04.wibucian.services;

import com.java04.wibucian.commons.ShiftOfDay;
import com.java04.wibucian.commons.ShiftOfDayConversion;
import com.java04.wibucian.commons.Utils;
import com.java04.wibucian.models.Employee;
import com.java04.wibucian.models.RollCall;
import com.java04.wibucian.models.Shift;
import com.java04.wibucian.repositories.EmployeeRepository;
import com.java04.wibucian.repositories.ShiftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.BinaryOperator;

@Service
public class SalaryService {
    @Autowired
    private ShiftRepository shiftRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Map<String, Object>> getSalarySummarizeForEmployeesUpToDate(
            List<String> employeeIdList, Calendar date) {
        List<Employee> employeeList = this.employeeRepository.findAllById(employeeIdList);
        Calendar firstDateOfMonth = Utils.currentDateWithoutTime();
        firstDateOfMonth.set(Calendar.YEAR, date.get(Calendar.YEAR));
        firstDateOfMonth.set(Calendar.MONTH, date.get(Calendar.MONTH));
        firstDateOfMonth.set(Calendar.DAY_OF_MONTH, 1);
        return summarizeEmployeeWorkAndSalaryBetween(employeeList, firstDateOfMonth,
                                                     date);
    }

    public List<Map<String, Object>> getSalarySummarizeForAllEmployeeForYearAndMonth(
            int year, int month) {
        Calendar firstDateOfMonth = Utils.currentDateWithoutTime();
        firstDateOfMonth.set(Calendar.YEAR, year);
        firstDateOfMonth.set(Calendar.MONTH, month);
        firstDateOfMonth.set(Calendar.DAY_OF_MONTH, 1);
        Calendar lastDateOfMonth = Utils.currentDateWithoutTime();
        lastDateOfMonth.set(Calendar.YEAR, year);
        lastDateOfMonth.set(Calendar.MONTH, month);
        lastDateOfMonth.set(Calendar.DAY_OF_MONTH, 1);
        lastDateOfMonth.add(Calendar.MONTH, 1);
        lastDateOfMonth.add(Calendar.DATE, -1);
        List<Employee> employeeList = this.employeeRepository.findAll();
        return summarizeEmployeeWorkAndSalaryBetween(employeeList, firstDateOfMonth,
                                                     lastDateOfMonth);
    }

    private List<Map<String, Object>> summarizeEmployeeWorkAndSalaryBetween(
            List<Employee> employeeList, Calendar firstDate, Calendar secondDate) {
        List<Map<String, Object>> result = employeeList.stream()
                                                       .map(employee -> {
                                                           List<Shift> shifts =
                                                                   this.shiftRepository.findAllShiftsBelongToEmployeeBetween(
                                                                           employee,
                                                                           firstDate.getTime(),
                                                                           secondDate.getTime());
                                                           return this.summarizeEmployeeWork(
                                                                   employee, shifts);
                                                       })
                                                       .toList();
        return result;
    }

    private Map<String, Object> summarizeEmployeeWork(Employee employee,
                                                      List<Shift> shifts) {
        Map<String, Object> employeeSummary = new HashMap<>();
        float employeeSalaryCoef = employee.getCoefficientsSalary();
        employeeSummary.put("employeeId", employee.getId());
        employeeSummary.put("employeeName", employee.getName());
        employeeSummary.put("employeeSalaryCoef", employeeSalaryCoef);
        employeeSummary.put("numOfShiftRequest", shifts.size());
        int numOfShiftWorked = 0;
        float hoursWorked = 0;
        for (Shift shift : shifts) {
            if (shift.getRollCall() != null) {
                numOfShiftWorked++;
            }
            hoursWorked += calculateHourWorkedFromShift(shift);
        }
        employeeSummary.put("numOfShiftWorked", numOfShiftWorked);
        employeeSummary.put("hoursWorked", hoursWorked);
        employeeSummary.put("totalSalary", (float) hoursWorked * employeeSalaryCoef);
        return employeeSummary;
    }

    private float calculateHourWorkedFromShift(Shift shift) {
        RollCall rollCall = shift.getRollCall();
        if (rollCall == null) {
            return 0;
        }
        Date shiftDate = shift.getShiftDate();
        int shiftCode = shift.getShiftCode();
        String endTimeString = ShiftOfDayConversion.getShiftOfDayFromValue(shiftCode)
                                                   .getEndTime();
        Instant endTime = Utils.getCalendarInstanceFromDateAndHHMMSSTimeString(shiftDate,
                                                                               endTimeString)
                               .toInstant();
        Instant rollCallTime = rollCall.getRollCallTime();
        return Math.abs((float) ChronoUnit.MINUTES.between(rollCallTime, endTime) / 60);
    }
}
