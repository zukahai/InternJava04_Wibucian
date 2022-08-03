package com.java04.wibucian.repositories;

import com.java04.wibucian.models.TypeProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TypeProductRepository extends JpaRepository<TypeProduct, String>, JpaSpecificationExecutor<TypeProduct> {

}