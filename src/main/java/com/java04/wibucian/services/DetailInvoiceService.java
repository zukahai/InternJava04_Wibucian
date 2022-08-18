package com.java04.wibucian.services;

import com.java04.wibucian.dtos.DetailInvoiceDTO;
import com.java04.wibucian.models.*;
import com.java04.wibucian.repositories.DetailInvoiceRepository;
import com.java04.wibucian.repositories.InvoiceRepository;
import com.java04.wibucian.repositories.ProductRepository;
import com.java04.wibucian.vos.DetailInvoiceQueryVO;
import com.java04.wibucian.vos.DetailInvoiceUpdateVO;
import com.java04.wibucian.vos.DetailInvoiceVO;
import com.java04.wibucian.vos.StatisticalResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;

@Service
public class DetailInvoiceService {

    @Autowired
    private DetailInvoiceRepository detailInvoiceRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    ProductRepository productRepository;

    public String save(DetailInvoiceVO vO) {
        DetailInvoice bean = new DetailInvoice();
        DetailInvoice detailInvoice = detailInvoiceRepository.findByIdProduct(vO.getIdProduct(), vO.getIdInvoice());
        if (detailInvoice != null) {
            DetailInvoiceUpdateVO detailInvoiceUpdateVO = new DetailInvoiceUpdateVO();
            BeanUtils.copyProperties(detailInvoice, detailInvoiceUpdateVO);
            detailInvoiceUpdateVO.setQuantity(detailInvoiceUpdateVO.getQuantity() + vO.getQuantity());
            update(detailInvoice.getId(), detailInvoiceUpdateVO);
        } else {
            BeanUtils.copyProperties(vO, bean);
            Invoice invoice = invoiceRepository.findById(vO.getIdInvoice()).orElseThrow(() -> new NoSuchElementException());
            Product product = productRepository.findById(vO.getIdProduct()).orElseThrow(() -> new NoSuchElementException());
            Double salePrice = product.getPrice();
            Sale sale = product.getSale();
            if (sale != null) {
                Instant now = Instant.now();
                Instant sale_start = sale.getTimeStart().toInstant();
                Instant sale_end = sale.getTimeEnd().toInstant();
                if (now.isAfter(sale_start) && now.isBefore(sale_end)) {
                    salePrice = salePrice - (salePrice * Double.parseDouble(sale.getPcent()) / 100);
                    bean.setProduct(product);
                }
            }
            bean.setProduct(product);
            bean.setInvoice(invoice);
            bean.setDateTime(Instant.now());
            bean.setTotalMoney( salePrice * vO.getQuantity());
            Double totalMoney = invoice.getTotalMoney();
            if (totalMoney == null) {
                totalMoney = 0.0;
            }
            totalMoney += bean.getTotalMoney();
            invoice.setTotalMoney(totalMoney);
            invoiceRepository.save(invoice);
            detailInvoiceRepository.save(bean);
        }
        return bean.getId();
    }
    public void delete(String id) {
        detailInvoiceRepository.deleteById(id);
    }
    public void update(String id, DetailInvoiceUpdateVO vO) {
        DetailInvoice bean = requireOne(id);
        Product product = bean.getProduct();
        Double currentTotalMoney = bean.getTotalMoney();
        Double salePrice = product.getPrice();

        Sale sale = product.getSale();
        if (sale != null){
            Instant now = Instant.now();
            Instant sale_start = sale.getTimeStart().toInstant();
            Instant sale_end = sale.getTimeEnd().toInstant();
            bean.setProduct(product);
            if (now.isAfter(sale_start) && now.isBefore(sale_end)) {
                salePrice = salePrice - (salePrice * Double.parseDouble(sale.getPcent()) / 100);
            }
        }

        Double totalMoney = salePrice * vO.getQuantity();
        bean.setTotalMoney(totalMoney);
        bean.setDateTime(Instant.now());
        Invoice invoice = bean.getInvoice();
        Double totalMoneyInvoice = invoice.getTotalMoney();
        if (totalMoneyInvoice == null) {
            totalMoneyInvoice = 0.0;
        }
        totalMoneyInvoice = totalMoneyInvoice - currentTotalMoney + totalMoney;
        System.out.println(totalMoneyInvoice);
        invoice.setTotalMoney(totalMoneyInvoice);
        BeanUtils.copyProperties(vO, bean);
        detailInvoiceRepository.save(bean);
        invoiceRepository.save(invoice);
    }
    public List<Product> findAllProduct(){
        return productRepository.findAll();
    }

    public DetailInvoiceDTO getById(String id) {
        DetailInvoice original = requireOne(id);
        return toDTO(original);
    }

    public Page<DetailInvoiceDTO> query(DetailInvoiceQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private DetailInvoiceDTO toDTO(DetailInvoice original) {
        DetailInvoiceDTO bean = new DetailInvoiceDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private DetailInvoice requireOne(String id) {
        return detailInvoiceRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }

    public List<DetailInvoice> findAllBetweenDate(Instant start, Instant end) {
        List<DetailInvoice> list = detailInvoiceRepository.findByDateTime(start, end);
        return list;
    }
    public List<StatisticalResultVO> getStatisticalResult(Instant start, Instant end) {
        List<DetailInvoice> detailInvoices = findAllBetweenDate(start, end);
        List<String> listIdProduct = new ArrayList<>();
        List<StatisticalResultVO> statisticalResultVOS = new ArrayList<>();
        for (DetailInvoice detailInvoice : detailInvoices) {
            if (!listIdProduct.contains(detailInvoice.getProduct().getId())) {
                listIdProduct.add(detailInvoice.getProduct().getId());
                StatisticalResultVO statisticalResultVO = new StatisticalResultVO();
                statisticalResultVO.setIdProduct(detailInvoice.getProduct().getId());
                statisticalResultVO.setNameProduct(detailInvoice.getProduct().getProductName());
                statisticalResultVO.setQuantity(getQuantityByIdProduct(detailInvoice.getProduct().getId()));
                statisticalResultVO.setTotalMoney(getSumTotalMoneyByIdProduct(detailInvoice.getProduct().getId()));
                Double totalMoney = statisticalResultVO.getTotalMoney();
                Double profitMoney = totalMoney - getPrice(detailInvoice.getProduct(), statisticalResultVO.getQuantity());
                statisticalResultVO.setProfitMoney((double) Math.round(profitMoney));
                statisticalResultVOS.add(statisticalResultVO);
            }
        }
        return statisticalResultVOS;
    }
    public Double getPrice(Product product, Integer quantity) {
        Double price = 0.0;
        for(DetailProduct detailProduct: product.getDetailProducts()){
            price += detailProduct.getIngredient().getPrice() * detailProduct.getQuantity();
        }
        return price * quantity;
    }
    public Double getTurnover(List<StatisticalResultVO> statisticalResultVOS){
        Double totalMoney = 0.0;
        for (StatisticalResultVO statisticalResultVO : statisticalResultVOS) {
            totalMoney += statisticalResultVO.getTotalMoney();
        }
        return totalMoney;
    }
    public Double getProfit(List<StatisticalResultVO> statisticalResultVOS){
        Double profitMoney = 0.0;
        for (StatisticalResultVO statisticalResultVO : statisticalResultVOS) {
            profitMoney += statisticalResultVO.getProfitMoney();
        }
        return profitMoney;
    }

    public DetailInvoiceDTO toDto(DetailInvoice detailInvoice) {
        DetailInvoiceDTO bean = new DetailInvoiceDTO();
        bean.setIdDetailInvoice(detailInvoice.getId());
        bean.setQuantity(detailInvoice.getQuantity());
        bean.setTotalMoney(Float.parseFloat(detailInvoice.getTotalMoney().toString()));
        bean.setDateTime(Date.from(detailInvoice.getDateTime()));
        bean.setIdProduct(detailInvoice.getProduct().getId());
        bean.setIdInvoice(detailInvoice.getInvoice().getId());
        bean.setIdInvoice(detailInvoice.getInvoice().getId());
        bean.setIdProduct(detailInvoice.getProduct().getId());
        return bean;
    }
    public Integer getQuantityByIdProduct(String idProduct){
        return detailInvoiceRepository.getSumQuantity(idProduct);
    }
    public Double getSumTotalMoneyByIdProduct(String idProduct){
        return detailInvoiceRepository.getSumTotalMoney(idProduct);
    }
}