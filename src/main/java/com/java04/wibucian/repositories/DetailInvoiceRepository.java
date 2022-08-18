package com.java04.wibucian.repositories;

import com.java04.wibucian.models.DetailInvoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.time.Instant;
import java.util.List;

public interface DetailInvoiceRepository extends JpaRepository<DetailInvoice, String>, JpaSpecificationExecutor<DetailInvoice> {
    //find detail invoice by idProduct and idInvoice
    @Query(value = "select * from DetailInvoice where idProduct = ?1 and idInvoice = ?2", nativeQuery = true)
    public DetailInvoice findByIdProduct(String idProduct, String idInvoice);

    //get all detail invoice between two time
    @Query(value = "select * from DetailInvoice where dateTime between ?1 and ?2", nativeQuery = true)
    public List<DetailInvoice> findByDateTime(Instant startTime, Instant endTime);
    //get sum quantity and sum total money of detailinvoice by idProduct
    @Query(value = "select sum(quantity) from DetailInvoice where idProduct = ?1", nativeQuery = true)
    public Integer getSumQuantity(String idProduct);
    @Query(value = "select sum(totalMoney) from DetailInvoice where idProduct = ?1", nativeQuery = true)
    public Double getSumTotalMoney(String idProduct);


}