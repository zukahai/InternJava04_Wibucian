package com.java04.wibucian.repositories;

import com.java04.wibucian.interfaces.models.ImportGoods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ImportGoodsRepository extends JpaRepository<ImportGoods, String>, JpaSpecificationExecutor<ImportGoods> {

}