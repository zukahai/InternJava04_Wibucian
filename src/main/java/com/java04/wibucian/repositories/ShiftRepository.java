package com.java04.wibucian.repositories;

import com.java04.wibucian.models.Employee;
import com.java04.wibucian.models.Shift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ShiftRepository extends JpaRepository<Shift, String>, JpaSpecificationExecutor<Shift> {
    // query records theo nhân viên, ngày làm việc và ca làm việc
    public Optional<Shift> findByEmployeeAndShiftDateAndShiftCode(Employee employee, Date shiftDate,
                                                                  int shiftCode);

    // query record theo ngày làm và ca làm, sắp xếp theo thời gian đăng ký
    public List<Shift> findAllByShiftDateAndShiftCodeOrderByRequestTimeDesc(Date shiftDate, int shiftCode);

    // query toàn bộ records đăng ký trong mốc thời gian cụ thể
    public List<Shift> findAllByRequestShiftAndShiftDateBetween(boolean requestShift, Date firstDate,
                                                                Date secondDate);

    public int countByEmployeeAndShiftDateBetween(Employee employee, Date firstDay, Date lastDay);

    public int countByEmployeeAndOvertimeRequestAndShiftDateBetween(Employee employee,
                                                                    boolean overtimeRequest, Date firstDay,
                                                                    Date lastDay);

//    public List<Shift> findAllByRequestShiftAndShiftDateBetween(boolean requestShift,
//                                                                Date firstDate,
//                                                                Date shiftDate2);
};