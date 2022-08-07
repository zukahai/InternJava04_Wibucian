package com.java04.wibucian.services;

import com.java04.wibucian.dtos.ShiftRotateDTO;
import com.java04.wibucian.models.ShiftRotate;
import com.java04.wibucian.repositories.ShiftRotateRepository;
import com.java04.wibucian.vos.ShiftRotateQueryVO;
import com.java04.wibucian.vos.ShiftRotateUpdateVO;
import com.java04.wibucian.vos.ShiftRotateVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class ShiftRotateService {

    @Autowired
    private ShiftRotateRepository shiftRotateRepository;

    public String save(ShiftRotateVO vO) {
        ShiftRotate bean = new ShiftRotate();
        BeanUtils.copyProperties(vO, bean);
        bean = shiftRotateRepository.save(bean);
        return bean.getId();
    }
//gen lại cái service model luôn đc k :vv
    public void delete(String id) {
        shiftRotateRepository.deleteById(id);
    }

    public void update(String id, ShiftRotateUpdateVO vO) {
        ShiftRotate bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        shiftRotateRepository.save(bean);
    }

    public ShiftRotateDTO getById(String id) {
        ShiftRotate original = requireOne(id);
        return toDTO(original);
    }

    public Page<ShiftRotateDTO> query(ShiftRotateQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private ShiftRotateDTO toDTO(ShiftRotate original) {
        ShiftRotateDTO bean = new ShiftRotateDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private ShiftRotate requireOne(String id) {
        return shiftRotateRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
