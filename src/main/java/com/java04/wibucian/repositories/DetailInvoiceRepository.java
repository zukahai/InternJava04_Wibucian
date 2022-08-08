package com.java04.wibucian.repositories;

import com.java04.wibucian.interfaces.models.DetailInvoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DetailInvoiceRepository extends JpaRepository<DetailInvoice, String>, JpaSpecificationExecutor<DetailInvoice> {

}