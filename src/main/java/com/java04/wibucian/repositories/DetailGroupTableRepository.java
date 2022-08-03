package com.java04.wibucian.repositories;

import com.java04.wibucian.models.DetailGroupTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DetailGroupTableRepository extends JpaRepository<DetailGroupTable, String>, JpaSpecificationExecutor<DetailGroupTable> {

}