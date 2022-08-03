package com.java04.wibucian.repositories;

import com.java04.wibucian.models.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SaleRepository extends JpaRepository<Sale, String>, JpaSpecificationExecutor<Sale> {

}