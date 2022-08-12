package com.java04.wibucian.services;

import com.java04.wibucian.dtos.DetailIngredientDTO;
import com.java04.wibucian.models.DetailIngredient;
import com.java04.wibucian.repositories.DetailIngredientRepository;
import com.java04.wibucian.vos.DetailIngredientQueryVO;
import com.java04.wibucian.vos.DetailIngredientUpdateVO;
import com.java04.wibucian.vos.DetailIngredientVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class DetailIngredientService {

    @Autowired
    private DetailIngredientRepository detailIngredientRepository;

    public String save(DetailIngredientVO vO) {
        DetailIngredient bean = new DetailIngredient();
        BeanUtils.copyProperties(vO, bean);
        bean = detailIngredientRepository.save(bean);
        return bean.getId();
    }

    public void delete(String id) {
        detailIngredientRepository.deleteById(id);
    }

    public void update(String id, DetailIngredientUpdateVO vO) {
        DetailIngredient bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        detailIngredientRepository.save(bean);
    }

    public DetailIngredientDTO getById(String id) {
        DetailIngredient original = requireOne(id);
        return toDTO(original);
    }

    public Page<DetailIngredientDTO> query(DetailIngredientQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private DetailIngredientDTO toDTO(DetailIngredient original) {
        DetailIngredientDTO bean = new DetailIngredientDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private DetailIngredient requireOne(String id) {
        return detailIngredientRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
