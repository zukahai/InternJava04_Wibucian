package com.java04.wibucian.repositories;

import com.java04.wibucian.models.Employee;
import com.java04.wibucian.models.Shift;
import com.java04.wibucian.models.ShiftRotate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ShiftRotateRepository extends JpaRepository<ShiftRotate, String>,
        JpaSpecificationExecutor<ShiftRotate> {
    List<ShiftRotate> getAllByStatusOrderByCreateTimeDesc(int status);

    List<ShiftRotate> getAllByStatusAndEmployeeChangeOrderByCreateTimeDesc(Integer status,
                                                                           Employee employeeChange);

    List<ShiftRotate> getAllByShiftOrShiftExchangeAndStatusNot(Shift shift,
                                                               Shift shiftExchange,
                                                               Integer status);

    @Query("SELECT t from ShiftRotate t WHERE t.shift = :shift and t.shiftExchange "
            + "=:shiftExchange and t.status in (:status1, :status2)")
    Optional<ShiftRotate> findExistPendingShiftRotate(Shift shift, Shift shiftExchange,
                                                      Integer status1, Integer status2);

    @Query("SELECT t from ShiftRotate t WHERE t.shift = :shift OR t.shiftExchange = "
            + ":shift AND t.status in (:status1, :status2)")
    List<ShiftRotate> getPendingShiftRotateByShift(Shift shift, int status1, int status2);

    @Query("SELECT t from ShiftRotate t WHERE t.employeeCreate = :employee AND t.status"
            + " in (:status1, :status2)")
    List<ShiftRotate> getPendingShiftRotatesOfEmployee(Employee employee, int status1,
                                                       int status2);

    @Query(value = "SELECT s.idShift\n" + "FROM Shift s\n"
            + "WHERE s.shiftDate >= cast(getdate() as date) AND (s.shiftDate BETWEEN "
            + ":weekStart AND :weekEnd)\n"
            + "AND s.isRequestShift = 'false'\n" + "AND s.idEmployeeChange IS NULL\n"
            + "AND NOT s.idEmployee = :idEmployee\n" + "AND NOT EXISTS\n" + "(SELECT *\n"
            + "FROM Shift s1\n" + "WHERE s1.shiftDate = s.shiftDate\n"
            + "AND s1.shiftCode = s.shiftCode\n" + "AND (s1.idEmployee = :idEmployee\n"
            + "OR s1.idEmployeeChange = :idEmployee)\n" + "UNION\n" + "SELECT *\n"
            + "FROM Shift s2\n" + "WHERE s2.shiftDate = :shiftDate\n"
            + "AND s2.shiftCode = :shiftCode\n" + "AND (s2.idEmployee = s.idEmployee\n"
            + "OR s2.idEmployeeChange = s.idEmployee))\n" + "AND NOT EXISTS\n"
            + "(SELECT *\n" + "FROM shiftRotate sr\n" + "WHERE sr.[status] in (0, 1)\n"
            + "AND (sr.idShift = :shiftId AND sr.idShiftExchange = s.idShift)\n"
            + "OR (sr.idShiftExchange = s.idShift AND sr.idShift = :shiftId))",
           nativeQuery = true)
    List<String> getCandidateShiftsToRotate(@Param("idEmployee") String idEmployee,
                                            @Param("shiftDate") Date shiftDate,
                                            @Param("shiftCode") int shiftCode,
                                            @Param("shiftId") String shiftId,
                                            @Param("weekStart") Date weekStart,
                                            @Param("weekEnd") Date weekEnd);
}