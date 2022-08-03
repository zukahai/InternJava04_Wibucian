package com.java04.wibucian.controllers.admin;

import com.java04.wibucian.dtos.InvoiceDTO;
import com.java04.wibucian.services.InvoiceService;
import com.java04.wibucian.vos.InvoiceQueryVO;
import com.java04.wibucian.vos.InvoiceUpdateVO;
import com.java04.wibucian.vos.InvoiceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/invoice")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @PostMapping
    public String save(@Valid @RequestBody InvoiceVO vO) {
        return invoiceService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") String id) {
        invoiceService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") String id,
                       @Valid @RequestBody InvoiceUpdateVO vO) {
        invoiceService.update(id, vO);
    }

    @GetMapping("/{id}")
    public InvoiceDTO getById(@Valid @NotNull @PathVariable("id") String id) {
        return invoiceService.getById(id);
    }

    @GetMapping
    public Page<InvoiceDTO> query(@Valid InvoiceQueryVO vO) {
        return invoiceService.query(vO);
    }
}
