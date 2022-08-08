package com.java04.wibucian.repositories;

import com.java04.wibucian.interfaces.models.Tablecf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TablecfRepository extends JpaRepository<Tablecf, String>, JpaSpecificationExecutor<Tablecf> {

}