package com.java04.wibucian.repositories;

import com.java04.wibucian.models.Ordercf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OrdercfRepository extends JpaRepository<Ordercf, String>, JpaSpecificationExecutor<Ordercf> {

}