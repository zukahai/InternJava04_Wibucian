package com.java04.wibucian.services;

import com.java04.wibucian.dtos.InvoiceDTO;
import com.java04.wibucian.models.*;
import com.java04.wibucian.repositories.EmployeeRepository;
import com.java04.wibucian.repositories.GroupTableRepository;
import com.java04.wibucian.repositories.InvoiceNoMapPingRepository;
import com.java04.wibucian.repositories.InvoiceRepository;
import com.java04.wibucian.vos.InvoiceQueryVO;
import com.java04.wibucian.vos.InvoiceUpdateVO;
import com.java04.wibucian.vos.InvoiceVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class InvoiceService {


    @Autowired
    private GroupTableRepository groupTableRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private InvoiceNoMapPingRepository invoiceNoMapPingRepository;


    public String save(InvoiceVO vO) {
        Invoice bean = new Invoice();
        Employee employee = employeeRepository.findById("Employee00005").orElseThrow(() -> new NoSuchElementException());
        GroupTable groupTable = groupTableRepository.findById(vO.getIdGroupTable()).orElseThrow(() -> new NoSuchElementException());
        BeanUtils.copyProperties(vO, bean);
        bean.setGroupTable(groupTable);
        bean.setEmployee(employee);
        bean = invoiceRepository.save(bean);
        System.out.println(bean);
        InvoiceNoMapPing invoiceNoMapPing = findByIdCustom(bean.getId());
        return invoiceNoMapPing.getIdInvoice();
    }

    public String save_one(InvoiceVO vO) {
        String idGroupTabel = vO.getIdGroupTable();
        List<InvoiceNoMapPing> invoiceNoMapPings = findByIdGroupTableAndStatus(idGroupTabel, "1");
        if (invoiceNoMapPings != null) {

            InvoiceNoMapPing invoiceNoMapPing = invoiceNoMapPings.get(0);
            return invoiceNoMapPing.getIdInvoice();
        }
        else {
            vO.setStatus(1);
            return save(vO);
        }
    }

    public void delete(String id) {
        invoiceRepository.deleteById(id);
    }

    public void update(String id, InvoiceUpdateVO vO) {
        Invoice bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        invoiceRepository.save(bean);
    }

    public List<InvoiceNoMapPing> findByIdGroupTableAndStatus(String id, String status) {
        List<InvoiceNoMapPing> list = new ArrayList<InvoiceNoMapPing>();
        list = invoiceNoMapPingRepository.findByIdGroupTableAndStatus(id, status);
        if (list.size() == 0) {
            return null;
        } else {
            return list;
        }
    }

    public InvoiceDTO getById(String id) {
        Invoice original = requireOne(id);
        return toDTO(original);
    }

    public InvoiceNoMapPing findByIdCustom(String id) {

        InvoiceNoMapPing invoiceNoMapPing = new InvoiceNoMapPing();
        invoiceNoMapPing = invoiceNoMapPingRepository.findByIdCustom(id);
        if (invoiceNoMapPing == null) {
            throw new NoSuchElementException();
        }
        return invoiceNoMapPing;
    }

    public Page<InvoiceDTO> query(InvoiceQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private InvoiceDTO toDTO(Invoice original) {
        InvoiceDTO bean = new InvoiceDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private Invoice requireOne(String id) {
        return invoiceRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}

