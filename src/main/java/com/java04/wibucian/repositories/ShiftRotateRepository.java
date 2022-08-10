package com.java04.wibucian.repositories;

import com.java04.wibucian.models.Employee;
import com.java04.wibucian.models.Shift;
import com.java04.wibucian.models.ShiftRotate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
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
}