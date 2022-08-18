package com.java04.wibucian.services;

import com.java04.wibucian.dtos.DetailImportGoodsDTO;
import com.java04.wibucian.models.DetailImportGoods;
import com.java04.wibucian.models.DetailIngredient;
import com.java04.wibucian.models.ImportGoods;
import com.java04.wibucian.models.Ingredient;
import com.java04.wibucian.repositories.DetailImportGoodsRepository;
import com.java04.wibucian.repositories.DetailIngredientRepository;
import com.java04.wibucian.repositories.ImportGoodsRepository;
import com.java04.wibucian.repositories.IngredientRepository;
import com.java04.wibucian.vos.DetailImportGoodsQueryVO;
import com.java04.wibucian.vos.DetailImportGoodsUpdateVO;
import com.java04.wibucian.vos.DetailImportGoodsVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashMap;
import java.util.NoSuchElementException;

@Service
public class DetailImportGoodsService {

    @Autowired
    private DetailIngredientRepository detailIngredientRepository;
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
            Double curentQuantity = ingredient.getQuantity();
            ingredient.setQuantity(ingredient.getQuantity() + vO.getQuantity());
            bean.setIngredient(ingredient);
            bean = detailImportGoodsRepository.save(bean);
            save(ingredient.getId(), "Nhập hàng",  ingredient.getQuantity() - curentQuantity);
            map.put("check", true);
            map.put("value", toDTO(bean));
            return map;
        } else {
            BeanUtils.copyProperties(vO, bean);
            ImportGoods importGoods = importGoodsRepository.findById(vO.getIdImportGoods()).orElseThrow(() -> new NoSuchElementException("Resource not found: " + vO.getIdImportGoods()));
            Ingredient ingredient = ingredientRepository.findById(vO.getIdIngredient()).orElseThrow(() -> new NoSuchElementException("Resource not found: " + vO.getIdIngredient()));
            bean.setImportGoods(importGoods);
            Double curentQuantity = ingredient.getQuantity();
            ingredient.setQuantity(ingredient.getQuantity() + vO.getQuantity());
            bean.setIngredient(ingredient);
            bean = detailImportGoodsRepository.save(bean);
            save(ingredient.getId(), "Nhập hàng", ingredient.getQuantity()- curentQuantity);
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
            Ingredient ingredient = detailImportGoods.getIngredient();
            ingredient.setQuantity(ingredient.getQuantity() - detailImportGoods.getQuantity());
            ingredientRepository.save(ingredient);
            detailImportGoodsRepository.delete(detailImportGoods);
            save(ingredient.getId(), "Cập nhật nhập hàng", -ingredient.getQuantity());
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
            Ingredient ingredient = bean.getIngredient();
            Double currentQuantity = ingredient.getQuantity();
            ingredient.setQuantity(ingredient.getQuantity() - bean.getQuantity() + vO.getQuantity());
            BeanUtils.copyProperties(vO, bean);
            bean.setIngredient(ingredient);
            detailImportGoodsRepository.save(bean);
            save(ingredient.getId(), "Cập nhật nhập hàng",  ingredient.getQuantity() - currentQuantity);
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
