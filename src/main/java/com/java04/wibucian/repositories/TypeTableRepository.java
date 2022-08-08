package com.java04.wibucian.repositories;

import com.java04.wibucian.interfaces.models.TypeTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TypeTableRepository extends JpaRepository<TypeTable, String>, JpaSpecificationExecutor<TypeTable> {

}