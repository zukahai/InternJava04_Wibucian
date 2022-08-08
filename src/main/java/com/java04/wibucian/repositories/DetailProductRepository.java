package com.java04.wibucian.repositories;

import com.java04.wibucian.models.DetailProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DetailProductRepository extends JpaRepository<DetailProduct, String>, JpaSpecificationExecutor<DetailProduct> {

}