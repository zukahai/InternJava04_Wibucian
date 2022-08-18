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
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ImportGoodsService {

    @Autowired
    private ImportGoodsRepository importGoodsRepository;

    public String save(ImportGoodsVO vO) {
        ImportGoods bean = new ImportGoods();
        BeanUtils.copyProperties(vO, bean);
        bean.setTimeImport(vO.getTimeImport());
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

    public ImportGoods findById(String id) {
        return requireOne(id);
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

    public List<ImportGoods> findAll(Pageable pageable) {return importGoodsRepository.findAll(pageable).getContent();}

    public List<ImportGoods> findAllNamZuka(Pageable pageable) {
        int start = pageable.getPageNumber() * pageable.getPageSize();
        List<ImportGoods> all = importGoodsRepository.findAll();
        List<ImportGoods> answer = new ArrayList();

        for (int i = start; i < start + pageable.getPageSize() && i < all.size(); i++) {
            answer.add(all.get(i));
        }
        return answer;
    }

    public int getTotalPage(int limit) {
        return (int) Math.ceil(importGoodsRepository.count() / (float)limit);
    }

}