package com.java04.wibucian.controllers.admin;

import com.java04.wibucian.dtos.DetailProductDTO;
import com.java04.wibucian.services.DetailProductService;
import com.java04.wibucian.vos.DetailProductQueryVO;
import com.java04.wibucian.vos.DetailProductUpdateVO;
import com.java04.wibucian.vos.DetailProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/detailProduct")
public class DetailProductController {

    @Autowired
    private DetailProductService detailProductService;

    @PostMapping
    public String save(@Valid @RequestBody DetailProductVO vO) {
        return detailProductService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") String id) {
        detailProductService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") String id,
                       @Valid @RequestBody DetailProductUpdateVO vO) {
        detailProductService.update(id, vO);
    }

    @GetMapping("/{id}")
    public DetailProductDTO getById(@Valid @NotNull @PathVariable("id") String id) {
        return detailProductService.getById(id);
    }

    @GetMapping
    public Page<DetailProductDTO> query(@Valid DetailProductQueryVO vO) {
        return detailProductService.query(vO);
    }
}
