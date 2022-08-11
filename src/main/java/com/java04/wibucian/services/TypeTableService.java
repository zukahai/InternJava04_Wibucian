package com.java04.wibucian.services;

import com.java04.wibucian.dtos.TypeTableDTO;
import com.java04.wibucian.models.TypeTable;
import com.java04.wibucian.repositories.TypeTableRepository;
import com.java04.wibucian.vos.TypeTableQueryVO;
import com.java04.wibucian.vos.TypeTableUpdateVO;
import com.java04.wibucian.vos.TypeTableVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TypeTableService {

    @Autowired
    private TypeTableRepository typeTableRepository;

    public String save(TypeTableVO vO) {
        TypeTable bean = new TypeTable();
        BeanUtils.copyProperties(vO, bean);
        bean = typeTableRepository.save(bean);
        return bean.getId();
    }

    public void delete(String id) {
        typeTableRepository.deleteById(id);
    }

    public void update(String id, TypeTableUpdateVO vO) {
        TypeTable bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        typeTableRepository.save(bean);
    }

    public TypeTableDTO getById(String id) {
        TypeTable original = requireOne(id);
        return toDTO(original);
    }

    public TypeTable findById(String id) {
        return requireOne(id);
    }

    public Page<TypeTableDTO> query(TypeTableQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private TypeTableDTO toDTO(TypeTable original) {
        TypeTableDTO bean = new TypeTableDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private TypeTable requireOne(String id) {
        return typeTableRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }

    public List<TypeTable> findAll(){
        return typeTableRepository.findAll();
    }

    public List<TypeTable> findAll(Pageable pageable) {
        return typeTableRepository.findAll(pageable).getContent();
    }

    public List<TypeTable> findAllHaiZuka(Pageable pageable) {
        int start = pageable.getPageNumber() * pageable.getPageSize();
        List<TypeTable> all = typeTableRepository.findAll();
        List<TypeTable> answer = new ArrayList();

        for (int i = start; i < start + pageable.getPageSize() && i < all.size(); i++) {
            answer.add(all.get(i));
        }
        return answer;
    }

    public int getTotalPage(int limit) {
        return (int) Math.ceil(typeTableRepository.count() / (float)limit);
    }
}