package com.java04.wibucian.repositories;

import com.java04.wibucian.models.DetailIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DetailIngredientRepository
        extends JpaRepository<DetailIngredient, String>,
        JpaSpecificationExecutor<DetailIngredient> {
}