package com.java04.wibucian.services;

import com.java04.wibucian.dtos.DetailGroupTableDTO;
import com.java04.wibucian.models.DetailGroupTable;
import com.java04.wibucian.models.GroupTable;
import com.java04.wibucian.repositories.DetailGroupTableRepository;
import com.java04.wibucian.repositories.GroupTableRepository;
import com.java04.wibucian.vos.DetailGroupTableQueryVO;
import com.java04.wibucian.vos.DetailGroupTableUpdateVO;
import com.java04.wibucian.vos.DetailGroupTableVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class DetailGroupTableService {

    @Autowired
    private DetailGroupTableRepository detailGroupTableRepository;
    private GroupTableRepository groupTableRepository;

    public DetailGroupTableService(GroupTableRepository groupTableRepository) {
        this.groupTableRepository = groupTableRepository;
    }

    public String save(DetailGroupTableVO vO) {
        DetailGroupTable bean = new DetailGroupTable();
        BeanUtils.copyProperties(vO, bean);
        bean = detailGroupTableRepository.save(bean);
        return bean.getId();
    }

    public void delete(String id) {
        detailGroupTableRepository.deleteById(id);
    }

    public void update(String id, DetailGroupTableUpdateVO vO) {
        DetailGroupTable bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        detailGroupTableRepository.save(bean);
    }

    public DetailGroupTableDTO getById(String id) {
        DetailGroupTable original = requireOne(id);
        return toDTO(original);
    }

    public DetailGroupTable findById(String id) {
        return requireOne(id);
    }

    public List<DetailGroupTable> getByIdGroupTable(String idGroupTable) {
        GroupTable groupTable = groupTableRepository.findById(idGroupTable).orElse(null);
        System.out.println("GroupTable" + groupTable);
        return detailGroupTableRepository.getByGroupTable(groupTable);
    }

    public Page<DetailGroupTableDTO> query(DetailGroupTableQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private DetailGroupTableDTO toDTO(DetailGroupTable original) {
        DetailGroupTableDTO bean = new DetailGroupTableDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private DetailGroupTable requireOne(String id) {
        return detailGroupTableRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
