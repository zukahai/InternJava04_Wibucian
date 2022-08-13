package com.java04.wibucian.services;

import com.java04.wibucian.dtos.OrdercfDTO;
import com.java04.wibucian.models.*;
import com.java04.wibucian.repositories.*;
import com.java04.wibucian.vos.OrdercfQueryVO;
import com.java04.wibucian.vos.OrdercfUpdateVO;
import com.java04.wibucian.vos.OrdercfVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class OrdercfService {

    @Autowired
    private OrdercfRepository ordercfRepository;

    @Autowired
    private OrdercfNoMapPingRepository ordercfNoMapPingRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductNoMapPingRepository productNoMapPingRepository;

    @Autowired
    private GroupTableRepository groupTableRepository;

    @Autowired
    private GroupTableNoMapPingRepository groupTableNoMapPingRepository;

    public String save(OrdercfVO vO) {
        Ordercf bean = new Ordercf();
        if (vO.getId() == null) {
            BeanUtils.copyProperties(vO, bean);
            Product product = productRepository.findById(vO.getIdProduct()).orElseThrow(() -> new NoSuchElementException());
            GroupTable groupTable = groupTableRepository.findById(vO.getIdGroupTable()).orElseThrow(() -> new NoSuchElementException());
            bean.setProduct(product);
            bean.setGroupTable(groupTable);
            bean.setTimeOrder(Instant.now());
            bean = ordercfRepository.save(bean);
        } else if (vO.getId() != null) {
            update(String.valueOf(vO.getIdOrdercf()), vO);
        }
        return bean.getId();
    }


    //delete return json
    public void delete(String id) {
        ordercfRepository.deleteById(id);
    }

    public void update(String id, OrdercfVO vO) {
        Ordercf bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        ordercfRepository.save(bean);
    }

    public OrdercfDTO getById(String id) {
        Ordercf original = requireOne(id);
        return toDTO(original);
    }

    public OrdercfNoMapPing getByIdNoMapPing(String id) {
        OrdercfNoMapPing original = ordercfNoMapPingRepository.findById(id).orElseThrow(() -> new NoSuchElementException());
        return original;
    }

    public Page<OrdercfDTO> query(OrdercfQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private OrdercfDTO toDTO(Ordercf original) {
        OrdercfDTO bean = new OrdercfDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private Ordercf requireOne(String id) {
        return ordercfRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }

    public List<Ordercf> findAll() {
        return ordercfRepository.findAll();
    }

    public List<OrdercfNoMapPing> findByGroupTableId(String id) {
        return ordercfNoMapPingRepository.findByGroupTableId(id);
    }

    public ProductNoMapPing findByProductId(String id) {
        return productNoMapPingRepository.findById(id).orElseThrow(() -> new NoSuchElementException());
    }

    //findByGroupTableId(String groupTableId);
    public GroupTableNoMapPing findGroupTableId(String id) {
        return groupTableNoMapPingRepository.findById(id).orElseThrow(() -> new NoSuchElementException());
    }

    public List<OrdercfNoMapPing> findAllNoMapp() {
        return ordercfNoMapPingRepository.findAll(Sort.by(Sort.Direction.ASC, "timeOrder"));
    }

}