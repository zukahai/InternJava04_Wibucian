package com.java04.wibucian.controllers.admin;

import com.java04.wibucian.dtos.DetailGroupTableDTO;
import com.java04.wibucian.services.DetailGroupTableService;
import com.java04.wibucian.vos.DetailGroupTableQueryVO;
import com.java04.wibucian.vos.DetailGroupTableUpdateVO;
import com.java04.wibucian.vos.DetailGroupTableVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/detailGroupTable")
public class DetailGroupTableController {

    @Autowired
    private DetailGroupTableService detailGroupTableService;

    @PostMapping
    public String save(@Valid @RequestBody DetailGroupTableVO vO) {
        return detailGroupTableService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") String id) {
        detailGroupTableService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") String id,
                       @Valid @RequestBody DetailGroupTableUpdateVO vO) {
        detailGroupTableService.update(id, vO);
    }

    @GetMapping("/{id}")
    public DetailGroupTableDTO getById(@Valid @NotNull @PathVariable("id") String id) {
        return detailGroupTableService.getById(id);
    }

    @GetMapping
    public Page<DetailGroupTableDTO> query(@Valid DetailGroupTableQueryVO vO) {
        return detailGroupTableService.query(vO);
    }
}
