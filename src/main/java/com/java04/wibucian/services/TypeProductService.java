package com.java04.wibucian.services;

import com.java04.wibucian.dtos.TypeProductDTO;
import com.java04.wibucian.models.TypeProduct;
import com.java04.wibucian.models.TypeTable;
import com.java04.wibucian.repositories.TypeProductRepository;
import com.java04.wibucian.vos.TypeProductQueryVO;
import com.java04.wibucian.vos.TypeProductUpdateVO;
import com.java04.wibucian.vos.TypeProductVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TypeProductService {

    @Autowired
    private TypeProductRepository typeProductRepository;

    public List<TypeProduct> findAll(){
        return typeProductRepository.findAll();
    }


    public String save(TypeProductVO vO) {
        TypeProduct bean = new TypeProduct();
        BeanUtils.copyProperties(vO, bean);
        bean = typeProductRepository.save(bean);
        return bean.getId();
    }

    public void delete(String id) {
        typeProductRepository.deleteById(id);
    }

    public void update(String id, TypeProductUpdateVO vO) {
        TypeProduct bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        typeProductRepository.save(bean);
    }

    public TypeProductDTO getById(String id) {
        TypeProduct original = requireOne(id);
        return toDTO(original);
    }

    public Page<TypeProductDTO> query(TypeProductQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private TypeProductDTO toDTO(TypeProduct original) {
        TypeProductDTO bean = new TypeProductDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private TypeProduct requireOne(String id) {
        return typeProductRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }

    //edit
    public TypeProduct findById(String id) {
        return requireOne(id);
    }

    public List<TypeProduct> findAll(Pageable pageable) {
        return typeProductRepository.findAll(pageable).getContent();
    }

    public List<TypeProduct> findAllHoang(Pageable pageable) {
        int start = pageable.getPageNumber() * pageable.getPageSize();
        List<TypeProduct> all = typeProductRepository.findAll();
        List<TypeProduct> answer = new ArrayList();

        for (int i = start; i < start + pageable.getPageSize() && i < all.size(); i++) {
            answer.add(all.get(i));
        }
        return answer;
    }

    public int getTotalPage(int limit) {
        return (int) Math.ceil(typeProductRepository.count() / (float)limit);
    }
}


