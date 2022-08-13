package com.java04.wibucian.services;

import com.java04.wibucian.dtos.InvoiceDTO;
import com.java04.wibucian.models.*;
import com.java04.wibucian.repositories.*;
import com.java04.wibucian.vos.InvoiceQueryVO;
import com.java04.wibucian.vos.InvoiceUpdateVO;
import com.java04.wibucian.vos.InvoiceVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;

@Service
public class InvoiceService {


    @Autowired
    private GroupTableRepository groupTableRepository;

    @Autowired
    private DetailInvoiceRepository detailInvoiceRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private InvoiceNoMapPingRepository invoiceNoMapPingRepository;

    @Autowired
    ProductRepository productRepository;

    public List<InvoiceNoMapPing> getAllInvoiceNoMapPing() {
        return invoiceNoMapPingRepository.findAll();
    }

    public List<Product> findAllProduct() {
        return productRepository.findAll();
    }

    public List<Invoice> findAll() {
        return invoiceRepository.findAll();
    }

    public Invoice findById(String id) {
        return invoiceRepository.findById(id).orElse(null);
    }

    public String save(InvoiceVO vO) {
        Invoice bean = new Invoice();
        Employee employee = employeeRepository.findById("Employee00005").orElseThrow(() -> new NoSuchElementException());
        GroupTable groupTable = groupTableRepository.findById(vO.getIdGroupTable()).orElseThrow(() -> new NoSuchElementException());
        BeanUtils.copyProperties(vO, bean);
        bean.setGroupTable(groupTable);
        bean.setEmployee(employee);
        bean.setTotalMoney(0.0);
        bean = invoiceRepository.save(bean);
        InvoiceNoMapPing invoiceNoMapPing = findByIdCustom(bean.getId());
        return invoiceNoMapPing.getIdInvoice();
    }

    public String save_one(InvoiceVO vO) {
        String idGroupTabel = vO.getIdGroupTable();
        List<InvoiceNoMapPing> invoiceNoMapPings = findByIdGroupTableAndStatus(idGroupTabel, "1");
        if (invoiceNoMapPings != null) {
            InvoiceNoMapPing invoiceNoMapPing = invoiceNoMapPings.get(0);
            return invoiceNoMapPing.getIdInvoice();
        } else {
            vO.setStatus(1);
            return save(vO);
        }
    }

    public HashMap<String, Object> delete(String id) {
        HashMap<String, Object> map = new HashMap<>();
        Invoice invoice = invoiceRepository.findById(id).get();
        if (invoice != null) {
            if (invoice.getStatus() != 2) {
                Set<DetailInvoice> detailInvoices = invoice.getDetailInvoices();
                if (detailInvoices != null) {
                    detailInvoices.forEach(detailInvoice -> {
                        System.out.println(detailInvoice.getId());
                        detailInvoiceRepository.deleteById(detailInvoice.getId());
                    });
                }
                map.put("check", true);
                map.put("value", "Xóa thành công");
                invoiceRepository.deleteById(id);

            } else {
                map.put("check", false);
                map.put("value", "Đơn hàng đã thanh toán không thể xóa");

            }
        } else {
            map.put("check", false);
            map.put("value", "Không tồn tại đơn hàng này");
        }
        return map;
    }

    public void update(String id, InvoiceUpdateVO vO) {
        Invoice bean = requireOne(id);
        if (bean != null) {
            //check validate not null
            if (vO.getStatus() != null) {
                bean.setStatus(vO.getStatus());
                if (vO.getStatus() == 2) {
                    bean.setDateTime(Instant.now());
                }
            }
            if (vO.getToltalMoney() != null) {
                bean.setTotalMoney(vO.getToltalMoney());
            }
            if (vO.getIdGroupTable() != null) {
                GroupTable groupTable = groupTableRepository.findById(vO.getIdGroupTable()).orElseThrow(() -> new NoSuchElementException());
                bean.setGroupTable(groupTable);
            }
            if (vO.getIdEmployee() != null) {
                Employee employee = employeeRepository.findById(vO.getIdEmployee()).orElseThrow(() -> new NoSuchElementException());
                bean.setEmployee(employee);
            }
            if (vO.getCustomerName() != null) {
                bean.setCustomerName(vO.getCustomerName());
            }
            invoiceRepository.save(bean);
        } else {
            throw new NoSuchElementException();
        }
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

    public List<Invoice> getPage(Pageable pageable) {
        return invoiceRepository.findAll(pageable).getContent();
    }

    public int getTotalPage(int limit) {
        return (int) Math.ceil((double) getCount() / limit);
    }

    public int getCount() {
        return (int) invoiceRepository.count();
    }
}
