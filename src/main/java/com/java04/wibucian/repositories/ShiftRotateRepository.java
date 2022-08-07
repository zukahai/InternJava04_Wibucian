package com.java04.wibucian.repositories;

import com.java04.wibucian.models.ShiftRotate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ShiftRotateRepository extends JpaRepository<ShiftRotate, String>, JpaSpecificationExecutor<ShiftRotate> {

}