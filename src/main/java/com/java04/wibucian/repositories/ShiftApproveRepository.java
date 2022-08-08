package com.java04.wibucian.repositories;

import com.java04.wibucian.models.ShiftApprove;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Date;
import java.util.Optional;

public interface ShiftApproveRepository extends JpaRepository<ShiftApprove, String>,
        JpaSpecificationExecutor<ShiftApprove> {
    public Optional<ShiftApprove> findByApproveFor(Date firstDayOfWeek);
}