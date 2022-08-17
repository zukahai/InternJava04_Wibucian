package com.java04.wibucian.controllers.admin;

import com.java04.wibucian.dtos.DetailIngredientDTO;
import com.java04.wibucian.services.DetailIngredientService;
import com.java04.wibucian.services.IngredientService;
import com.java04.wibucian.vos.DetailIngredientQueryVO;
import com.java04.wibucian.vos.DetailIngredientUpdateVO;
import com.java04.wibucian.vos.DetailIngredientVO;
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
@RequestMapping("admin/detailIngredient")
public class DetailIngredientController {

    @Autowired
    private DetailIngredientService detailIngredientService;
    private IngredientService ingredientService;

    public DetailIngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping("/")
    public String Home(ModelMap modelMap)throws Exception {
        modelMap.addAttribute("detailIngredients", detailIngredientService.findAll());
        return "admin/ingredient/detail";
    }

    @GetMapping("/view/{idIngredient}")
    public String HomeView(ModelMap modelMap, @Valid @NotNull @PathVariable("idIngredient") String idIngredient)throws Exception {
        modelMap.addAttribute("detailIngredients", detailIngredientService.findAllByIngredientId(idIngredient));
        modelMap.addAttribute("Ingredient", ingredientService.getById(idIngredient));
        return "admin/ingredient/detail";
    }

    @PostMapping
    public String save(@Valid @RequestBody DetailIngredientVO vO) {
        return detailIngredientService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") String id) {
        detailIngredientService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") String id,
                       @Valid @RequestBody DetailIngredientUpdateVO vO) {
        detailIngredientService.update(id, vO);
    }

    @GetMapping("/{id}")
    public DetailIngredientDTO getById(@Valid @NotNull @PathVariable("id") String id) {
        return detailIngredientService.getById(id);
    }

    @GetMapping
    public Page<DetailIngredientDTO> query(@Valid DetailIngredientQueryVO vO) {
        return detailIngredientService.query(vO);
    }
}
