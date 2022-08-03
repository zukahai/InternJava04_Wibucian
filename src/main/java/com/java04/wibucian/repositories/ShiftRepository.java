package com.java04.wibucian.repositories;

import com.java04.wibucian.models.Shift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ShiftRepository extends JpaRepository<Shift, String>, JpaSpecificationExecutor<Shift> {

}