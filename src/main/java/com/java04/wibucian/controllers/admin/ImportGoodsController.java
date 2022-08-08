package com.java04.wibucian.controllers.admin;

import com.java04.wibucian.dtos.ImportGoodsDTO;
import com.java04.wibucian.services.ImportGoodsService;
import com.java04.wibucian.vos.ImportGoodsQueryVO;
import com.java04.wibucian.vos.ImportGoodsUpdateVO;
import com.java04.wibucian.vos.ImportGoodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@Controller
@RequestMapping("admin/importgoods")
public class ImportGoodsController {

    @Autowired
    private ImportGoodsService importGoodsService;

    @GetMapping("/")
    public String Home(ModelMap modelMap)throws Exception {
        modelMap.addAttribute("nhaphang", importGoodsService.findAll());
        return "admin/importgoods/index";
    }

    @GetMapping("/create")
    public String create(ModelMap modelMap)throws Exception {
        modelMap.addAttribute("nhaphang", importGoodsService.findAll());
        return "admin/importgoods/create";
    }

    @PostMapping
    public String save(@Valid @RequestBody ImportGoodsVO vO) {
        // check xem mã thằng nhân viên

        return importGoodsService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") String id) {
        importGoodsService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") String id,
                       @Valid @RequestBody ImportGoodsUpdateVO vO) {
        importGoodsService.update(id, vO);
    }

    @GetMapping("/{id}")
    public ImportGoodsDTO getById(@Valid @NotNull @PathVariable("id") String id) {
        return importGoodsService.getById(id);
    }

    @GetMapping
    public Page<ImportGoodsDTO> query(@Valid ImportGoodsQueryVO vO) {
        return importGoodsService.query(vO);
    }
}