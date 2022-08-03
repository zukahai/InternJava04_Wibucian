package com.java04.wibucian.services;

import com.java04.wibucian.dtos.InvoiceDTO;
import com.java04.wibucian.models.Invoice;
import com.java04.wibucian.repositories.InvoiceRepository;
import com.java04.wibucian.vos.InvoiceQueryVO;
import com.java04.wibucian.vos.InvoiceUpdateVO;
import com.java04.wibucian.vos.InvoiceVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    public String save(InvoiceVO vO) {
        Invoice bean = new Invoice();
        BeanUtils.copyProperties(vO, bean);
        bean = invoiceRepository.save(bean);
        return bean.getIdInvoice();
    }

    public void delete(String id) {
        invoiceRepository.deleteById(id);
    }

    public void update(String id, InvoiceUpdateVO vO) {
        Invoice bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        invoiceRepository.save(bean);
    }

    public InvoiceDTO getById(String id) {
        Invoice original = requireOne(id);
        return toDTO(original);
    }

    public Page<InvoiceDTO> query(InvoiceQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private InvoiceDTO toDTO(Invoice original) {
        InvoiceDTO bean = new InvoiceDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private Invoice requireOne(String id) {
        return invoiceRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
