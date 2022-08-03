package com.java04.wibucian.services;

import com.java04.wibucian.dtos.ShiftDTO;
import com.java04.wibucian.models.Shift;
import com.java04.wibucian.repositories.ShiftRepository;
import com.java04.wibucian.vos.ShiftQueryVO;
import com.java04.wibucian.vos.ShiftUpdateVO;
import com.java04.wibucian.vos.ShiftVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class ShiftService {

    @Autowired
    private ShiftRepository shiftRepository;

    public String save(ShiftVO vO) {
        Shift bean = new Shift();
        BeanUtils.copyProperties(vO, bean);
        bean = shiftRepository.save(bean);
        return bean.getIdShift();
    }

    public void delete(String id) {
        shiftRepository.deleteById(id);
    }

    public void update(String id, ShiftUpdateVO vO) {
        Shift bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        shiftRepository.save(bean);
    }

    public ShiftDTO getById(String id) {
        Shift original = requireOne(id);
        return toDTO(original);
    }

    public Page<ShiftDTO> query(ShiftQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private ShiftDTO toDTO(Shift original) {
        ShiftDTO bean = new ShiftDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private Shift requireOne(String id) {
        return shiftRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
