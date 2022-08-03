package com.java04.wibucian.controllers.admin;

import com.java04.wibucian.dtos.TypeTableDTO;
import com.java04.wibucian.services.TypeTableService;
import com.java04.wibucian.vos.TypeTableQueryVO;
import com.java04.wibucian.vos.TypeTableUpdateVO;
import com.java04.wibucian.vos.TypeTableVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/typeTable")
public class TypeTableController {

    @Autowired
    private TypeTableService typeTableService;

    @PostMapping
    public String save(@Valid @RequestBody TypeTableVO vO) {
        return typeTableService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") String id) {
        typeTableService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") String id,
                       @Valid @RequestBody TypeTableUpdateVO vO) {
        typeTableService.update(id, vO);
    }

    @GetMapping("/{id}")
    public TypeTableDTO getById(@Valid @NotNull @PathVariable("id") String id) {
        return typeTableService.getById(id);
    }

    @GetMapping
    public Page<TypeTableDTO> query(@Valid TypeTableQueryVO vO) {
        return typeTableService.query(vO);
    }
}
