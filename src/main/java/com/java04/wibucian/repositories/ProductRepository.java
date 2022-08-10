package com.java04.wibucian.repositories;

import com.java04.wibucian.models.Product;
import com.java04.wibucian.models.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String>, JpaSpecificationExecutor<Product> {
    public List<Product> findAllBySaleNotNull();

}