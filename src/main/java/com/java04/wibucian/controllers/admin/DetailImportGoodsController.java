package com.java04.wibucian.controllers.admin;

import com.java04.wibucian.dtos.DetailImportGoodsDTO;
import com.java04.wibucian.services.DetailImportGoodsService;
import com.java04.wibucian.vos.DetailImportGoodsQueryVO;
import com.java04.wibucian.vos.DetailImportGoodsUpdateVO;
import com.java04.wibucian.vos.DetailImportGoodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/admin/detailImportGoods")
public class DetailImportGoodsController {

    @Autowired
    private DetailImportGoodsService detailImportGoodsService;

    @PostMapping
    @ResponseBody
    public ResponseEntity<Object> save(@Valid @RequestBody DetailImportGoodsVO vO) {
        return ResponseEntity.ok().body(detailImportGoodsService.save(vO));
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Object> delete(@Valid @NotNull @PathVariable("id") String id) {
        return ResponseEntity.ok().body( detailImportGoodsService.delete(id));
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Object> update(@Valid @NotNull @PathVariable("id") String id,
                                         @Valid @RequestBody DetailImportGoodsUpdateVO vO) {
        return ResponseEntity.ok().body(detailImportGoodsService.update(id, vO));
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
