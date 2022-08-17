package com.java04.wibucian.services;

import com.java04.wibucian.dtos.GroupTableDTO;
import com.java04.wibucian.models.DetailGroupTable;
import com.java04.wibucian.models.GroupTable;
import com.java04.wibucian.models.Tablecf;
import com.java04.wibucian.repositories.DetailGroupTableRepository;
import com.java04.wibucian.repositories.GroupTableRepository;
import com.java04.wibucian.vos.GroupTableQueryVO;
import com.java04.wibucian.vos.GroupTableUpdateVO;
import com.java04.wibucian.vos.GroupTableVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class GroupTableService {

    @Autowired
    private GroupTableRepository groupTableRepository;
    private DetailGroupTableRepository detailGroupTableRepository;

    public GroupTableService(DetailGroupTableRepository detailGroupTableRepository) {
        this.detailGroupTableRepository = detailGroupTableRepository;
    }


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
        GroupTable groupTable = groupTableRepository.findById(id).orElse(null);
        List<DetailGroupTable> list = detailGroupTableRepository.getByGroupTable(groupTable);
        for (DetailGroupTable detailGroupTable : list) {
            detailGroupTableRepository.deleteById(detailGroupTable.getId());
        }
        groupTableRepository.deleteById(id);
    }

    public void deleteDetail(String id) {
        GroupTable groupTable = groupTableRepository.findById(id).orElse(null);
        List<DetailGroupTable> list = detailGroupTableRepository.getByGroupTable(groupTable);
        for (DetailGroupTable detailGroupTable : list) {
            detailGroupTableRepository.deleteById(detailGroupTable.getId());
        }
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

    public GroupTable findById(String id) {
        return groupTableRepository.findById(id).orElse(null);
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
    public List<GroupTable> findAll(){
        return groupTableRepository.findAll();
    }

//    public List<GroupTable> findAll() {return groupTableRepository.findAll();}

    public List<GroupTable> findAllHaiZuka(Pageable pageable) {
        int start = pageable.getPageNumber() * pageable.getPageSize();
        List<GroupTable> all = groupTableRepository.findAll();
        List<GroupTable> answer = new ArrayList();

        for (int i = start; i < start + pageable.getPageSize() && i < all.size(); i++) {
            answer.add(all.get(i));
        }
        return answer;
    }

    public int getTotalPage(int limit) {
        return (int) Math.ceil(groupTableRepository.findAll().size() / (float)limit);
    }
}
