package com.java04.wibucian.repositories;

import com.java04.wibucian.models.DetailInvoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface DetailInvoiceRepository extends JpaRepository<DetailInvoice, String>, JpaSpecificationExecutor<DetailInvoice> {
    //find detail invoice by idProduct and idInvoice
    @Query(value = "select * from DetailInvoice where idProduct = ?1 and idInvoice = ?2", nativeQuery = true)
    public DetailInvoice findByIdProduct(String idProduct, String idInvoice);
}