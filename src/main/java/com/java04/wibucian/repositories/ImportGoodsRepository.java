package com.java04.wibucian.repositories;

import com.java04.wibucian.models.ImportGoods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface ImportGoodsRepository extends JpaRepository<ImportGoods, String>, JpaSpecificationExecutor<ImportGoods> {

    //get by id
    @Query(value = "SELECT * FROM ImportGoods WHERE id = ?1", nativeQuery = true)
    public ImportGoods getByIdCustom(String id);


}