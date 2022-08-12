package com.java04.wibucian.repositories;

import com.java04.wibucian.models.RollCall;
import com.java04.wibucian.models.Shift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface RollCallRepository
        extends JpaRepository<RollCall, String>, JpaSpecificationExecutor<RollCall> {
    public Optional<RollCall> findByShift(Shift shift);
}