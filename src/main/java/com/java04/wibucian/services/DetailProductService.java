package com.java04.wibucian.services;

import com.java04.wibucian.dtos.DetailProductDTO;
import com.java04.wibucian.models.DetailProduct;
import com.java04.wibucian.repositories.DetailProductRepository;
import com.java04.wibucian.vos.DetailProductQueryVO;
import com.java04.wibucian.vos.DetailProductUpdateVO;
import com.java04.wibucian.vos.DetailProductVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class DetailProductService {

    @Autowired
    private DetailProductRepository detailProductRepository;

    public String save(DetailProductVO vO) {
        DetailProduct bean = new DetailProduct();
        BeanUtils.copyProperties(vO, bean);
        bean = detailProductRepository.save(bean);
        return bean.getIdDetailProduct();
    }

    public void delete(String id) {
        detailProductRepository.deleteById(id);
    }

    public void update(String id, DetailProductUpdateVO vO) {
        DetailProduct bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        detailProductRepository.save(bean);
    }

    public DetailProductDTO getById(String id) {
        DetailProduct original = requireOne(id);
        return toDTO(original);
    }

    public Page<DetailProductDTO> query(DetailProductQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private DetailProductDTO toDTO(DetailProduct original) {
        DetailProductDTO bean = new DetailProductDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private DetailProduct requireOne(String id) {
        return detailProductRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
