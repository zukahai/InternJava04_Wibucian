package com.java04.wibucian.services;

import com.java04.wibucian.dtos.TablecfDTO;
import com.java04.wibucian.models.Tablecf;
import com.java04.wibucian.repositories.TablecfRepository;
import com.java04.wibucian.vos.TablecfQueryVO;
import com.java04.wibucian.vos.TablecfUpdateVO;
import com.java04.wibucian.vos.TablecfVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class TablecfService {

    @Autowired
    private TablecfRepository tablecfRepository;

    public String save(TablecfVO vO) {
        Tablecf bean = new Tablecf();
        BeanUtils.copyProperties(vO, bean);
        bean = tablecfRepository.save(bean);
        return bean.getId();
    }

    public void delete(String id) {
        tablecfRepository.deleteById(id);
    }

    public void update(String id, TablecfUpdateVO vO) {
        Tablecf bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        tablecfRepository.save(bean);
    }

    public TablecfDTO getById(String id) {
        Tablecf original = requireOne(id);
        return toDTO(original);
    }

    public Page<TablecfDTO> query(TablecfQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private TablecfDTO toDTO(Tablecf original) {
        TablecfDTO bean = new TablecfDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private Tablecf requireOne(String id) {
        return tablecfRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
