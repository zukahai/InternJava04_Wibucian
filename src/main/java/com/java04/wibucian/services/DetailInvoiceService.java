package com.java04.wibucian.services;

import com.java04.wibucian.dtos.DetailInvoiceDTO;
import com.java04.wibucian.models.DetailInvoice;
import com.java04.wibucian.models.Invoice;
import com.java04.wibucian.models.Product;
import com.java04.wibucian.models.Sale;
import com.java04.wibucian.repositories.DetailInvoiceRepository;
import com.java04.wibucian.repositories.InvoiceRepository;
import com.java04.wibucian.repositories.ProductRepository;
import com.java04.wibucian.vos.DetailInvoiceQueryVO;
import com.java04.wibucian.vos.DetailInvoiceUpdateVO;
import com.java04.wibucian.vos.DetailInvoiceVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

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
                Instant sale_start = sale.getTimeStart();
                Instant sale_end = sale.getTimeEnd();
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
            Instant sale_start = sale.getTimeStart();
            Instant sale_end = sale.getTimeEnd();
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
}
