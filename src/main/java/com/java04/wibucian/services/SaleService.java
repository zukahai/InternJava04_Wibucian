package com.java04.wibucian.services;

import com.java04.wibucian.dtos.SaleDTO;
import com.java04.wibucian.models.Product;
import com.java04.wibucian.models.Sale;
import com.java04.wibucian.models.TypeProduct;
import com.java04.wibucian.repositories.SaleRepository;
import com.java04.wibucian.vos.SaleQueryVO;
import com.java04.wibucian.vos.SaleUpdateVO;
import com.java04.wibucian.vos.SaleVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class SaleService {

    @Autowired
    private SaleRepository saleRepository;

    public List<Sale> findAll(){
        return saleRepository.findAll();
    }

    public String save(SaleVO vO) {
        Sale bean = new Sale();
        bean.setTimeStart(vO.getTimeStart().toInstant());
        bean.setTimeEnd(vO.getTimeEnd().toInstant());
        BeanUtils.copyProperties(vO, bean);
        bean = saleRepository.save(bean);
        return bean.getId();
    }

    public void delete(String id) {
        saleRepository.deleteById(id);
    }

    public void update(String id, SaleUpdateVO vO) {
        Sale bean = this.requireOne(id);
        bean.setTimeStart(vO.getTimeStart().toInstant());
        bean.setTimeEnd(vO.getTimeEnd().toInstant());
        BeanUtils.copyProperties(vO, bean);
        bean = saleRepository.save(bean);
    }



    public SaleDTO getById(String id) {
        Sale original = requireOne(id);
        return toDTO(original);
    }

    public Page<SaleDTO> query(SaleQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private SaleDTO toDTO(Sale original) {
        SaleDTO bean = new SaleDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private Sale requireOne(String id) {
        return saleRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }

    public Sale findById(String id) {
        return requireOne(id);
    }

    public List<Sale> findAll(Pageable pageable) {
        return saleRepository.findAll(pageable).getContent();
    }

    public List<Sale> findAllHoang(Pageable pageable) {
        int start = pageable.getPageNumber() * pageable.getPageSize();
        List<Sale> all = saleRepository.findAll();
        List<Sale> answer = new ArrayList();

        for (int i = start; i < start + pageable.getPageSize() && i < all.size(); i++) {
            answer.add(all.get(i));
        }
        return answer;
    }

    public int getTotalPage(int limit) {
        return (int) Math.ceil(saleRepository.count() / (float)limit);
    }

}
