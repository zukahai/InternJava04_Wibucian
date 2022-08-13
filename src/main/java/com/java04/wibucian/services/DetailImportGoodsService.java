package com.java04.wibucian.services;

import com.java04.wibucian.dtos.DetailImportGoodsDTO;
import com.java04.wibucian.models.DetailImportGoods;
import com.java04.wibucian.models.ImportGoods;
import com.java04.wibucian.models.Ingredient;
import com.java04.wibucian.repositories.DetailImportGoodsRepository;
import com.java04.wibucian.repositories.ImportGoodsRepository;
import com.java04.wibucian.repositories.IngredientRepository;
import com.java04.wibucian.vos.DetailImportGoodsQueryVO;
import com.java04.wibucian.vos.DetailImportGoodsUpdateVO;
import com.java04.wibucian.vos.DetailImportGoodsVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.NoSuchElementException;

@Service
public class DetailImportGoodsService {

    @Autowired
    private DetailImportGoodsRepository detailImportGoodsRepository;
    @Autowired
    private ImportGoodsRepository importGoodsRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    public HashMap<String, Object> save(DetailImportGoodsVO vO) {
        HashMap<String, Object> map = new HashMap<>();
        DetailImportGoods bean = new DetailImportGoods();
        DetailImportGoods importGoodsCheck = detailImportGoodsRepository.findByIdImportGoodsAndIdIngredient(vO.getIdImportGoods(), vO.getIdIngredient());
        if (importGoodsCheck != null) {
            bean = importGoodsCheck;
            bean.setQuantity(importGoodsCheck.getQuantity() + vO.getQuantity());
            Ingredient ingredient = importGoodsCheck.getIngredient();
            ingredient.setQuantity(ingredient.getQuantity() + vO.getQuantity());
            bean.setIngredient(ingredient);
            bean = detailImportGoodsRepository.save(bean);
            map.put("check", true);
            map.put("value", toDTO(bean));
            return map;
        } else {
            BeanUtils.copyProperties(vO, bean);
            ImportGoods importGoods = importGoodsRepository.findById(vO.getIdImportGoods()).orElseThrow(() -> new NoSuchElementException("Resource not found: " + vO.getIdImportGoods()));
            Ingredient ingredient = ingredientRepository.findById(vO.getIdIngredient()).orElseThrow(() -> new NoSuchElementException("Resource not found: " + vO.getIdIngredient()));
            bean.setImportGoods(importGoods);
            ingredient.setQuantity(ingredient.getQuantity() + vO.getQuantity());
            bean.setIngredient(ingredient);
            bean = detailImportGoodsRepository.save(bean);
            bean = detailImportGoodsRepository.findByIdCustom(bean.getId());
            map.put("check", true);
            map.put("value", toDTO(bean));
            return map;
        }
    }
    public HashMap<String, Object> delete(String id) {
        HashMap<String, Object> map = new HashMap<>();
        DetailImportGoods detailImportGoods = requireOne(id);
        if (detailImportGoods != null) {
            detailImportGoodsRepository.delete(detailImportGoods);
            map.put("check", true);
            map.put("value", "Delete success");
        } else {
            map.put("check", false);
            map.put("value", "Delete fail");
        }
        return map;
    }

    public HashMap<String, Object> update(String id, DetailImportGoodsUpdateVO vO) {
        HashMap<String, Object> map = new HashMap<>();
        DetailImportGoods bean = requireOne(id);
        if (bean != null) {
            BeanUtils.copyProperties(vO, bean);
            Ingredient ingredient = bean.getIngredient();
            ingredient.setQuantity(ingredient.getQuantity() - bean.getQuantity() + vO.getQuantity());
            bean.setIngredient(ingredient);
            detailImportGoodsRepository.save(bean);
            bean = findById(bean.getId());
            map.put("check", true);
            map.put("value", toDTO(bean));
            return map;
        } else {
            map.put("check", false);
            return map;
        }
    }

    public DetailImportGoods findById(String id) {
        return detailImportGoodsRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
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
        bean.setIdImportGoods(original.getImportGoods().getId());
        bean.setIdIngredient(original.getIngredient().getId());
        return bean;
    }

    private DetailImportGoods requireOne(String id) {
        return detailImportGoodsRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
