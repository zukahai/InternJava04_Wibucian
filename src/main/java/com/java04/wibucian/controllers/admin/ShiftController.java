package com.java04.wibucian.controllers.admin;

import com.java04.wibucian.dtos.ShiftDTO;
import com.java04.wibucian.services.ShiftService;
import com.java04.wibucian.vos.ShiftQueryVO;
import com.java04.wibucian.vos.ShiftUpdateVO;
import com.java04.wibucian.vos.ShiftVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/shift")
public class ShiftController {

    @Autowired
    private ShiftService shiftService;

    @PostMapping
    public String save(@Valid @RequestBody ShiftVO vO) {
        return shiftService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") String id) {
        shiftService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") String id,
                       @Valid @RequestBody ShiftUpdateVO vO) {
        shiftService.update(id, vO);
    }

    @GetMapping("/{id}")
    public ShiftDTO getById(@Valid @NotNull @PathVariable("id") String id) {
        return shiftService.getById(id);
    }

    @GetMapping
    public Page<ShiftDTO> query(@Valid ShiftQueryVO vO) {
        return shiftService.query(vO);
    }
}
