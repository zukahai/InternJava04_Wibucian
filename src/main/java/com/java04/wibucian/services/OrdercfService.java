package com.java04.wibucian.services;

import com.java04.wibucian.dtos.OrdercfDTO;
import com.java04.wibucian.models.GroupTable;
import com.java04.wibucian.models.Ordercf;
import com.java04.wibucian.models.Product;
import com.java04.wibucian.repositories.GroupTableRepository;
import com.java04.wibucian.repositories.OrdercfRepository;
import com.java04.wibucian.repositories.ProductRepository;
import com.java04.wibucian.vos.OrdercfQueryVO;
import com.java04.wibucian.vos.OrdercfUpdateVO;
import com.java04.wibucian.vos.OrdercfVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class OrdercfService {

    @Autowired
    private OrdercfRepository ordercfRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private GroupTableRepository groupTableRepository;

    public String save(OrdercfVO vO) {
        Ordercf bean = new Ordercf();
        BeanUtils.copyProperties(vO, bean);
        Product product = productRepository.findById(vO.getIdProduct()).orElseThrow(() -> new NoSuchElementException());
        GroupTable groupTable = groupTableRepository.findById(vO.getIdGroupTable()).orElseThrow(() -> new NoSuchElementException());
        bean.setProduct(product);
        bean.setGroupTable(groupTable);
        bean.setTimeOrder(Instant.now());
        bean = ordercfRepository.save(bean);
        return bean.getId();
    }

   //delete return json
    public void delete(String id) {
        ordercfRepository.deleteById(id);
    }

    public void update(String id, OrdercfUpdateVO vO) {
        Ordercf bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        ordercfRepository.save(bean);
    }

    public OrdercfDTO getById(String id) {
        Ordercf original = requireOne(id);
        return toDTO(original);
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

    public List<Ordercf> findALl() {
        return ordercfRepository.findAll();
    }
}
