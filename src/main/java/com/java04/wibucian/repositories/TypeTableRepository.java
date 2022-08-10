package com.java04.wibucian.repositories;

import com.java04.wibucian.models.TypeTable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TypeTableRepository extends JpaRepository<TypeTable, String>, JpaSpecificationExecutor<TypeTable> {
//    public Page<TypeTable> findAll(Pageable pageable);
}