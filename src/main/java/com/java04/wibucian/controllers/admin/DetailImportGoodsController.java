package com.java04.wibucian.controllers.admin;

import com.java04.wibucian.dtos.DetailImportGoodsDTO;
import com.java04.wibucian.services.DetailImportGoodsService;
import com.java04.wibucian.vos.DetailImportGoodsQueryVO;
import com.java04.wibucian.vos.DetailImportGoodsUpdateVO;
import com.java04.wibucian.vos.DetailImportGoodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/detailImportGoods")
public class DetailImportGoodsController {

    @Autowired
    private DetailImportGoodsService detailImportGoodsService;

    @PostMapping
    public String save(@Valid @RequestBody DetailImportGoodsVO vO) {
        return detailImportGoodsService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") String id) {
        detailImportGoodsService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") String id,
                       @Valid @RequestBody DetailImportGoodsUpdateVO vO) {
        detailImportGoodsService.update(id, vO);
    }

    @GetMapping("/{id}")
    public DetailImportGoodsDTO getById(@Valid @NotNull @PathVariable("id") String id) {
        return detailImportGoodsService.getById(id);
    }

    @GetMapping
    public Page<DetailImportGoodsDTO> query(@Valid DetailImportGoodsQueryVO vO) {
        return detailImportGoodsService.query(vO);
    }
}
