package com.java04.wibucian.repositories;

import com.java04.wibucian.models.Employee;
import com.java04.wibucian.models.Shift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ShiftRepository
        extends JpaRepository<Shift, String>, JpaSpecificationExecutor<Shift> {
    // query records theo nhân viên, ngày làm việc, ca làm việc và trạng thái
    public Optional<Shift> findByEmployeeAndShiftDateAndShiftCodeAndRequestShift(
            Employee employee, Date shiftDate, Integer shiftCode, boolean requestShift);

    // query record theo ngày làm và ca làm, sắp xếp theo thời gian đăng ký
    public List<Shift> findAllByShiftDateAndShiftCodeOrderByRequestTimeDesc(
            Date shiftDate, int shiftCode);

    // query toàn bộ records ca làm trong mốc thời gian cụ thể
    public List<Shift> findAllByRequestShiftAndShiftDateBetween(boolean requestShift,
                                                                Date firstDate,
                                                                Date secondDate);

    // query toàn bộ records ca làm trong mốc thời gian cụ thể của một nhân viên
    @Query("SELECT t from Shift t WHERE t.requestShift = FALSE AND ((t.employee = "
            + ":employee AND t.employeeChange IS NULL) OR (t.employeeChange = "
            + ":employee)) AND t.shiftDate BETWEEN :firstDay AND :secondDay")
    public List<Shift> findAllShiftsBelongToEmployeeBetween(Employee employee,
                                                            Date firstDay,
                                                            Date secondDay);

    public int countByEmployeeAndShiftDateBetween(Employee employee, Date firstDay,
                                                  Date lastDay);

    public int countByEmployeeAndOvertimeRequestAndShiftDateBetween(Employee employee,
                                                                    boolean overtimeRequest,
                                                                    Date firstDay,
                                                                    Date lastDay);

    @Query("SELECT s FROM Shift s WHERE ((s.employee = :employee AND s.employeeChange IS"
            + " NULL) OR s.employeeChange = :employee) AND s.shiftDate = :shiftDate AND "
            + "s.shiftCode = :shiftCode")
    public Optional<Shift> getCurrentShiftOfEmployee(Employee employee, Date shiftDate,
                                                     int shiftCode);
};