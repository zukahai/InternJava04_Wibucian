package com.java04.wibucian.repositories;

import com.java04.wibucian.models.DetailImportGoods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DetailImportGoodsRepository extends JpaRepository<DetailImportGoods, String>, JpaSpecificationExecutor<DetailImportGoods> {

}