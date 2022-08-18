package com.java04.wibucian.services;

import com.java04.wibucian.dtos.ImportGoodsDTO;
import com.java04.wibucian.models.Employee;
import com.java04.wibucian.models.ImportGoods;
import com.java04.wibucian.models.Ingredient;
import com.java04.wibucian.repositories.EmployeeRepository;
import com.java04.wibucian.repositories.ImportGoodsRepository;
import com.java04.wibucian.repositories.IngredientRepository;
import com.java04.wibucian.vos.ImportGoodsQueryVO;
import com.java04.wibucian.vos.ImportGoodsUpdateVO;
import com.java04.wibucian.vos.ImportGoodsVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ImportGoodsService {

    @Autowired
    private ImportGoodsRepository importGoodsRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    public HashMap<String, Object> save(ImportGoodsVO vO) {
        HashMap<String, Object> map = new HashMap<>();
        ImportGoods bean = new ImportGoods();
        Employee employee = employeeRepository.findById(vO.getIdEmployee()).orElseThrow(() -> new NoSuchElementException("Employee not found"));
        vO.setTimeImport(new Date().toString());
        BeanUtils.copyProperties(vO, bean);
        bean.setTimeImport(Instant.now());
        bean.setEmployee(employee);
        bean = importGoodsRepository.save(bean);
        map.put("check", true);
        map.put("value", findImportGoodsCustom(bean.getId()));
        return map;
    }
    public List<Ingredient> findIngredients() {
        return ingredientRepository.findAll();
    }

    public void delete(String id) {
        importGoodsRepository.deleteById(id);
    }

    public void update(String id, ImportGoodsUpdateVO vO) {
        ImportGoods bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        importGoodsRepository.save(bean);
    }
    public ImportGoodsDTO findImportGoodsCustom(String id){
        ImportGoods importGoods =  importGoodsRepository.getByIdCustom(id);
        System.out.println(importGoods);
        ImportGoodsDTO importGoodsDTO = new ImportGoodsDTO();
        importGoodsDTO.setIdImportGoods(importGoods.getId());
        importGoodsDTO.setIdEmployee(importGoods.getEmployee().getId());
        return importGoodsDTO;
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
    public ImportGoods findById(String id) {
        return importGoodsRepository.findById(id).get();
    }
}