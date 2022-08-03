package com.java04.wibucian.controllers.admin;

import com.java04.wibucian.dtos.DetailInvoiceDTO;
import com.java04.wibucian.services.DetailInvoiceService;
import com.java04.wibucian.vos.DetailInvoiceQueryVO;
import com.java04.wibucian.vos.DetailInvoiceUpdateVO;
import com.java04.wibucian.vos.DetailInvoiceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/detailInvoice")
public class DetailInvoiceController {

    @Autowired
    private DetailInvoiceService detailInvoiceService;

    @PostMapping
    public String save(@Valid @RequestBody DetailInvoiceVO vO) {
        return detailInvoiceService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") String id) {
        detailInvoiceService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") String id,
                       @Valid @RequestBody DetailInvoiceUpdateVO vO) {
        detailInvoiceService.update(id, vO);
    }

    @GetMapping("/{id}")
    public DetailInvoiceDTO getById(@Valid @NotNull @PathVariable("id") String id) {
        return detailInvoiceService.getById(id);
    }

    @GetMapping
    public Page<DetailInvoiceDTO> query(@Valid DetailInvoiceQueryVO vO) {
        return detailInvoiceService.query(vO);
    }
}
