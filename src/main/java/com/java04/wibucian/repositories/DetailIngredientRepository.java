package com.java04.wibucian.repositories;

import com.java04.wibucian.models.DetailIngredient;
import com.java04.wibucian.models.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DetailIngredientRepository extends JpaRepository<DetailIngredient, String>, JpaSpecificationExecutor<DetailIngredient> {
    public List<DetailIngredient> findAllByIdIngredient(Ingredient idIngredient);
}