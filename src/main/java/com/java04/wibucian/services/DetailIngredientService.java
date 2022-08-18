package com.java04.wibucian.services;

import com.java04.wibucian.dtos.DetailIngredientDTO;
import com.java04.wibucian.models.DetailIngredient;
import com.java04.wibucian.models.Ingredient;
import com.java04.wibucian.repositories.DetailIngredientRepository;
import com.java04.wibucian.repositories.IngredientRepository;
import com.java04.wibucian.vos.DetailIngredientQueryVO;
import com.java04.wibucian.vos.DetailIngredientUpdateVO;
import com.java04.wibucian.vos.DetailIngredientVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class DetailIngredientService {

    @Autowired
    private DetailIngredientRepository detailIngredientRepository;
    private IngredientRepository ingredientRepository;

    public DetailIngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

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

    public List<DetailIngredient> findAll() {
        return detailIngredientRepository.findAll();
    }

    public List<DetailIngredient> findAllByIngredientId(String ingredientId) {
        Ingredient ingredient = ingredientRepository.findById(ingredientId).orElse(null);
        return detailIngredientRepository.findAllByIdIngredient(ingredient);
    }

    public String save(String idIngredient, String content, Double valueChange) {
        DetailIngredient bean = new DetailIngredient();
        bean.setValueChange(valueChange);
        bean.setIdIngredient(ingredientRepository.findById(idIngredient).orElse(null));
        bean.setDateTime(Instant.now());
        bean.setContent(content);
        bean = detailIngredientRepository.save(bean);
        return bean.getId();
    }

}
