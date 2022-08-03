package com.java04.wibucian.services;

import com.java04.wibucian.dtos.DetailInvoiceDTO;
import com.java04.wibucian.models.DetailInvoice;
import com.java04.wibucian.repositories.DetailInvoiceRepository;
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

    public String save(DetailInvoiceVO vO) {
        DetailInvoice bean = new DetailInvoice();
        BeanUtils.copyProperties(vO, bean);
        bean = detailInvoiceRepository.save(bean);
        return bean.getIdDetailInvoice();
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
