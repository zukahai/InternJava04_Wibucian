package com.java04.wibucian.services;

import com.java04.wibucian.dtos.GroupTableDTO;
import com.java04.wibucian.interfaces.models.GroupTable;
import com.java04.wibucian.repositories.GroupTableRepository;
import com.java04.wibucian.vos.GroupTableQueryVO;
import com.java04.wibucian.vos.GroupTableUpdateVO;
import com.java04.wibucian.vos.GroupTableVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class GroupTableService {

    @Autowired
    private GroupTableRepository groupTableRepository;

    public String save(GroupTableVO vO) {
        GroupTable bean = new GroupTable();
        BeanUtils.copyProperties(vO, bean);
        bean = groupTableRepository.save(bean);
        return bean.getId();
    }

    public String save(String beanName) {
        GroupTable bean = new GroupTable();
        bean.setGroupName(beanName);
        bean.setFoundedTime(Instant.now());
        bean = groupTableRepository.save(bean);
        return bean.getId();
    }

    public void delete(String id) {
        groupTableRepository.deleteById(id);
    }

    public void update(String id, GroupTableUpdateVO vO) {
        GroupTable bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        groupTableRepository.save(bean);
    }

    public GroupTableDTO getById(String id) {
        GroupTable original = requireOne(id);
        return toDTO(original);
    }

    public Page<GroupTableDTO> query(GroupTableQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private GroupTableDTO toDTO(GroupTable original) {
        GroupTableDTO bean = new GroupTableDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private GroupTable requireOne(String id) {
        return groupTableRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }

    public List<GroupTable> findAll() {return groupTableRepository.findAll();}
}
