package com.java04.wibucian.services;

import com.java04.wibucian.dtos.SaleDTO;
import com.java04.wibucian.interfaces.models.Sale;
import com.java04.wibucian.repositories.SaleRepository;
import com.java04.wibucian.vos.SaleQueryVO;
import com.java04.wibucian.vos.SaleUpdateVO;
import com.java04.wibucian.vos.SaleVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class SaleService {

    @Autowired
    private SaleRepository saleRepository;

    public String save(SaleVO vO) {
        Sale bean = new Sale();
        BeanUtils.copyProperties(vO, bean);
        bean = saleRepository.save(bean);
        return bean.getId();
    }

    public void delete(String id) {
        saleRepository.deleteById(id);
    }

    public void update(String id, SaleUpdateVO vO) {
        Sale bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        saleRepository.save(bean);
    }

    public SaleDTO getById(String id) {
        Sale original = requireOne(id);
        return toDTO(original);
    }

    public Page<SaleDTO> query(SaleQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private SaleDTO toDTO(Sale original) {
        SaleDTO bean = new SaleDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private Sale requireOne(String id) {
        return saleRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
