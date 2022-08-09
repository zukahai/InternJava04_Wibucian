package com.java04.wibucian.controllers.admin;

import com.java04.wibucian.dtos.ShiftRotateDTO;
import com.java04.wibucian.services.ShiftRotateService;
import com.java04.wibucian.vos.ShiftRotateQueryVO;
import com.java04.wibucian.vos.ShiftRotateUpdateVO;
import com.java04.wibucian.vos.ShiftRotateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/shiftRotate")
public class ShiftRotateController {

    @Autowired
    private ShiftRotateService shiftRotateService;

    @PostMapping
    public String save(@Valid @RequestBody ShiftRotateVO vO) {
        return shiftRotateService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") String id) {
        shiftRotateService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") String id,
                       @Valid @RequestBody ShiftRotateUpdateVO vO) {
        shiftRotateService.update(id, vO);
    }

    @GetMapping("/{id}")
    public ShiftRotateDTO getById(@Valid @NotNull @PathVariable("id") String id) {
        return shiftRotateService.getById(id);
    }

    @GetMapping
    public Page<ShiftRotateDTO> query(@Valid ShiftRotateQueryVO vO) {
        return shiftRotateService.query(vO);
    }
}
