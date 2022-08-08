package com.java04.wibucian.controllers.admin;

import com.java04.wibucian.dtos.ImportGoodsDTO;
import com.java04.wibucian.models.Ingredient;
import com.java04.wibucian.repositories.IngredientRepository;
import com.java04.wibucian.services.ImportGoodsService;
import com.java04.wibucian.vos.ImportGoodsQueryVO;
import com.java04.wibucian.vos.ImportGoodsUpdateVO;
import com.java04.wibucian.vos.ImportGoodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Validated
@Controller
@RequestMapping("admin/importgoods")
public class ImportGoodsController {

    @Autowired
    private ImportGoodsService importGoodsService;



    @GetMapping()
    public String Home(ModelMap modelMap) throws Exception {
        modelMap.addAttribute("importgoods", importGoodsService.findAll());
        return "admin/importgoods/index";
    }

    @GetMapping("/create")
    public String create(ModelMap modelMap)throws Exception {
        modelMap.addAttribute("nhaphang", importGoodsService.findAll());
        return "admin/importgoods/create";
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Object> create(@RequestBody @Valid ImportGoodsVO vo) throws Exception {
        vo.setIdEmployee("Employee00005");
        return ResponseEntity.ok().body(importGoodsService.save(vo));
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
    public String getById(@Valid @NotNull @PathVariable("id") String id, ModelMap modelMap) {
        modelMap.addAttribute("importgood", importGoodsService.findById(id));
        modelMap.addAttribute("ingredients", importGoodsService.findIngredients());
        return "admin/importgoods/detail";
    }


}
