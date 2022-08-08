package com.java04.wibucian.repositories;

import com.java04.wibucian.models.DetailProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface DetailProductRepository extends JpaRepository<DetailProduct, String>, JpaSpecificationExecutor<DetailProduct> {
    List<DetailProduct> findAllByProductId(String productId);
}