package com.java04.wibucian.repositories;

import com.java04.wibucian.interfaces.models.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IngredientRepository extends JpaRepository<Ingredient, String>, JpaSpecificationExecutor<Ingredient> {

}