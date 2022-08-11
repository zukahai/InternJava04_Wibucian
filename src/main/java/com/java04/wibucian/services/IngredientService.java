package com.java04.wibucian.services;

import com.java04.wibucian.dtos.IngredientDTO;
import com.java04.wibucian.models.ImportGoods;
import com.java04.wibucian.models.Ingredient;
import com.java04.wibucian.repositories.IngredientRepository;
import com.java04.wibucian.vos.IngredientQueryVO;
import com.java04.wibucian.vos.IngredientUpdateVO;
import com.java04.wibucian.vos.IngredientVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class IngredientService {

    @Autowired
    private IngredientRepository ingredientRepository;

    public String save(IngredientVO vO) {
        Ingredient bean = new Ingredient();
        BeanUtils.copyProperties(vO, bean);
        bean = ingredientRepository.save(bean);
        return bean.getId();
    }

    public void delete(String id) {
        ingredientRepository.deleteById(id);
    }

    public void update(String id, IngredientUpdateVO vO) {
        Ingredient bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        ingredientRepository.save(bean);
    }

    public List<Ingredient> findAll(){
        return ingredientRepository.findAll();
    }

    public Ingredient findById(String id) {
        return requireOne(id);
    }
    public IngredientDTO getById(String id) {
        Ingredient original = requireOne(id);
        return toDTO(original);
    }

    public Page<IngredientDTO> query(IngredientQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private IngredientDTO toDTO(Ingredient original) {
        IngredientDTO bean = new IngredientDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private Ingredient requireOne(String id) {
        return ingredientRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }


}
