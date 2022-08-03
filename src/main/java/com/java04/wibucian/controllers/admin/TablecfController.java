package com.java04.wibucian.controllers.admin;

import com.java04.wibucian.dtos.TablecfDTO;
import com.java04.wibucian.services.TablecfService;
import com.java04.wibucian.vos.TablecfQueryVO;
import com.java04.wibucian.vos.TablecfUpdateVO;
import com.java04.wibucian.vos.TablecfVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/tablecf")
public class TablecfController {

    @Autowired
    private TablecfService tablecfService;

    @PostMapping
    public String save(@Valid @RequestBody TablecfVO vO) {
        return tablecfService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") String id) {
        tablecfService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") String id,
                       @Valid @RequestBody TablecfUpdateVO vO) {
        tablecfService.update(id, vO);
    }

    @GetMapping("/{id}")
    public TablecfDTO getById(@Valid @NotNull @PathVariable("id") String id) {
        return tablecfService.getById(id);
    }

    @GetMapping
    public Page<TablecfDTO> query(@Valid TablecfQueryVO vO) {
        return tablecfService.query(vO);
    }
}
