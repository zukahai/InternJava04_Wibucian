package com.java04.wibucian.services;

import com.java04.wibucian.dtos.ImportGoodsDTO;
import com.java04.wibucian.models.ImportGoods;
import com.java04.wibucian.repositories.ImportGoodsRepository;
import com.java04.wibucian.vos.ImportGoodsQueryVO;
import com.java04.wibucian.vos.ImportGoodsUpdateVO;
import com.java04.wibucian.vos.ImportGoodsVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ImportGoodsService {

    @Autowired
    private ImportGoodsRepository importGoodsRepository;

    public String save(ImportGoodsVO vO) {
        ImportGoods bean = new ImportGoods();
        BeanUtils.copyProperties(vO, bean);
        bean = importGoodsRepository.save(bean);
        return bean.getId();
    }

    public void delete(String id) {
        importGoodsRepository.deleteById(id);
    }

    public void update(String id, ImportGoodsUpdateVO vO) {
        ImportGoods bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        importGoodsRepository.save(bean);
    }

    public List<ImportGoods> findAll(){
        return importGoodsRepository.findAll();
    }

    public ImportGoodsDTO getById(String id) {
        ImportGoods original = requireOne(id);
        return toDTO(original);
    }

    public Page<ImportGoodsDTO> query(ImportGoodsQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private ImportGoodsDTO toDTO(ImportGoods original) {
        ImportGoodsDTO bean = new ImportGoodsDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private ImportGoods requireOne(String id) {
        return importGoodsRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
