package com.java04.wibucian.repositories;

import com.java04.wibucian.models.WorkPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface WorkPlanRepository
        extends JpaRepository<WorkPlan, String>, JpaSpecificationExecutor<WorkPlan> {

    public List<WorkPlan> findAllByShiftDateBetween(Date firstDate, Date secondDate);

    public Optional<WorkPlan> findByShiftDateAndShiftCode(Date shiftDate, Integer shiftCode);
}