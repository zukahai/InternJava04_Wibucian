package com.java04.wibucian.repositories;

import com.java04.wibucian.models.DetailImportGoods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface DetailImportGoodsRepository extends JpaRepository<DetailImportGoods, String>, JpaSpecificationExecutor<DetailImportGoods> {

    //find by idImportGoods and idIngredient
    @Query(value = "SELECT * FROM DetailImportGoods WHERE idImportGoods = ?1 AND idIngredient = ?2", nativeQuery = true)
    public DetailImportGoods findByIdImportGoodsAndIdIngredient(String idImportGoods, String idIngredient);

    @Query(value = "SELECT * FROM DetailImportGoods WHERE id = ?1", nativeQuery = true)
    public DetailImportGoods findByIdCustom(String id);
}