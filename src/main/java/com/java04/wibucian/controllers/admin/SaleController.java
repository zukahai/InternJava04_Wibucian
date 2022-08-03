package com.java04.wibucian.controllers.admin;

import com.java04.wibucian.dtos.SaleDTO;
import com.java04.wibucian.services.SaleService;
import com.java04.wibucian.vos.SaleQueryVO;
import com.java04.wibucian.vos.SaleUpdateVO;
import com.java04.wibucian.vos.SaleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/sale")
public class SaleController {

    @Autowired
    private SaleService saleService;

    @PostMapping
    public String save(@Valid @RequestBody SaleVO vO) {
        return saleService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") String id) {
        saleService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") String id,
                       @Valid @RequestBody SaleUpdateVO vO) {
        saleService.update(id, vO);
    }

    @GetMapping("/{id}")
    public SaleDTO getById(@Valid @NotNull @PathVariable("id") String id) {
        return saleService.getById(id);
    }

    @GetMapping
    public Page<SaleDTO> query(@Valid SaleQueryVO vO) {
        return saleService.query(vO);
    }
}
