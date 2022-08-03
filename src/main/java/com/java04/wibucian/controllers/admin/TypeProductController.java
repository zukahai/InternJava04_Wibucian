package com.java04.wibucian.controllers.admin;

import com.java04.wibucian.dtos.TypeProductDTO;
import com.java04.wibucian.services.TypeProductService;
import com.java04.wibucian.vos.TypeProductQueryVO;
import com.java04.wibucian.vos.TypeProductUpdateVO;
import com.java04.wibucian.vos.TypeProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/typeProduct")
public class TypeProductController {

    @Autowired
    private TypeProductService typeProductService;

    @PostMapping
    public String save(@Valid @RequestBody TypeProductVO vO) {
        return typeProductService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") String id) {
        typeProductService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") String id,
                       @Valid @RequestBody TypeProductUpdateVO vO) {
        typeProductService.update(id, vO);
    }

    @GetMapping("/{id}")
    public TypeProductDTO getById(@Valid @NotNull @PathVariable("id") String id) {
        return typeProductService.getById(id);
    }

    @GetMapping
    public Page<TypeProductDTO> query(@Valid TypeProductQueryVO vO) {
        return typeProductService.query(vO);
    }
}
