package com.java04.wibucian.services;

import com.java04.wibucian.dtos.DetailImportGoodsDTO;
import com.java04.wibucian.models.DetailImportGoods;
import com.java04.wibucian.repositories.DetailImportGoodsRepository;
import com.java04.wibucian.vos.DetailImportGoodsQueryVO;
import com.java04.wibucian.vos.DetailImportGoodsUpdateVO;
import com.java04.wibucian.vos.DetailImportGoodsVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class DetailImportGoodsService {

    @Autowired
    private DetailImportGoodsRepository detailImportGoodsRepository;

    public String save(DetailImportGoodsVO vO) {
        DetailImportGoods bean = new DetailImportGoods();
        BeanUtils.copyProperties(vO, bean);
        bean = detailImportGoodsRepository.save(bean);
        return bean.getIdDetailImportGoods();
    }

    public void delete(String id) {
        detailImportGoodsRepository.deleteById(id);
    }

    public void update(String id, DetailImportGoodsUpdateVO vO) {
        DetailImportGoods bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        detailImportGoodsRepository.save(bean);
    }

    public DetailImportGoodsDTO getById(String id) {
        DetailImportGoods original = requireOne(id);
        return toDTO(original);
    }

    public Page<DetailImportGoodsDTO> query(DetailImportGoodsQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private DetailImportGoodsDTO toDTO(DetailImportGoods original) {
        DetailImportGoodsDTO bean = new DetailImportGoodsDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private DetailImportGoods requireOne(String id) {
        return detailImportGoodsRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
