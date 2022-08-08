package com.java04.wibucian.repositories;

import com.java04.wibucian.interfaces.models.Employee;
import com.java04.wibucian.interfaces.models.Shift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Date;
import java.util.List;

public interface ShiftRepository
        extends JpaRepository<Shift, String>, JpaSpecificationExecutor<Shift> {
    public Shift findByEmployeeAndShiftDateAndShiftCode(Employee employee, Date shiftDate,
                                                        int shiftCode);

    public List<Shift> findAllByShiftDateAndShiftCodeOrderByRequestTimeDesc(
            Date shiftDate, int shiftCode);

    public int countByEmployeeAndShiftDateBetween(Employee employee, Date firstDay,
                                                  Date lastDay);
};