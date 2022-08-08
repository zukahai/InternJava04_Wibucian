package com.java04.wibucian.repositories;

import com.java04.wibucian.models.Tablecf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface TablecfRepository extends JpaRepository<Tablecf, String>, JpaSpecificationExecutor<Tablecf> {

}