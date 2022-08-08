package com.java04.wibucian.services;

import com.java04.wibucian.dtos.DetailInvoiceDTO;
import com.java04.wibucian.models.DetailInvoice;
import com.java04.wibucian.models.Invoice;
import com.java04.wibucian.models.Product;

import com.java04.wibucian.repositories.DetailInvoiceRepository;
import com.java04.wibucian.repositories.InvoiceRepository;
import com.java04.wibucian.repositories.ProductRepository;
import com.java04.wibucian.vos.DetailInvoiceQueryVO;
import com.java04.wibucian.vos.DetailInvoiceUpdateVO;
import com.java04.wibucian.vos.DetailInvoiceVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class DetailInvoiceService {

    @Autowired
    private DetailInvoiceRepository detailInvoiceRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    ProductRepository productRepository;

    public String save(DetailInvoiceVO vO) {
        DetailInvoice bean = new DetailInvoice();
        BeanUtils.copyProperties(vO, bean);
        Invoice invoice = invoiceRepository.findById(vO.getIdInvoice()).orElseThrow(()-> new NoSuchElementException());
        Product product = productRepository.findById(vO.getIdProduct()).orElseThrow(() -> new NoSuchElementException());
        bean.setProduct(product);
        bean.setInvoice(invoice);
        bean.setTotalMoney(product.getPrice()*vO.getQuantity());
        bean = detailInvoiceRepository.save(bean);
        return bean.getId();
    }

    public void delete(String id) {
        detailInvoiceRepository.deleteById(id);
    }

    public void update(String id, DetailInvoiceUpdateVO vO) {
        DetailInvoice bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        detailInvoiceRepository.save(bean);
    }

    public DetailInvoiceDTO getById(String id) {
        DetailInvoice original = requireOne(id);
        return toDTO(original);
    }

    public Page<DetailInvoiceDTO> query(DetailInvoiceQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private DetailInvoiceDTO toDTO(DetailInvoice original) {
        DetailInvoiceDTO bean = new DetailInvoiceDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private DetailInvoice requireOne(String id) {
        return detailInvoiceRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
