package com.java04.wibucian.repositories;

import com.java04.wibucian.models.ProductNoMapPing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductNoMapPingRepository extends JpaRepository<ProductNoMapPing, String>, JpaSpecificationExecutor<ProductNoMapPing> {

}