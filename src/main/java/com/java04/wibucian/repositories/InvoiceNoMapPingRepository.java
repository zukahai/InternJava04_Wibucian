package com.java04.wibucian.repositories;

import com.java04.wibucian.models.InvoiceNoMapPing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InvoiceNoMapPingRepository extends JpaRepository<InvoiceNoMapPing, String>, JpaSpecificationExecutor<InvoiceNoMapPing> {
        @Query(value = "select * from invoice where id = ?1", nativeQuery = true)
        InvoiceNoMapPing findByIdCustom(String id);

        //find by idGroupTable and status
        @Query(value = "select * from invoice where idGroupTable = ?1 and status = ?2", nativeQuery = true)
        List<InvoiceNoMapPing> findByIdGroupTableAndStatus(String idGroupTable, String status);

}