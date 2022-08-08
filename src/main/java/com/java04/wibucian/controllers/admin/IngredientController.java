package com.java04.wibucian.controllers.admin;

import com.java04.wibucian.dtos.IngredientDTO;
import com.java04.wibucian.services.IngredientService;
import com.java04.wibucian.vos.IngredientQueryVO;
import com.java04.wibucian.vos.IngredientUpdateVO;
import com.java04.wibucian.vos.IngredientVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/ingredient")
public class IngredientController {

    @Autowired
    private IngredientService ingredientService;

    @PostMapping
    public String save(@Valid @RequestBody IngredientVO vO) {
        return ingredientService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") String id) {
        ingredientService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") String id,
                       @Valid @RequestBody IngredientUpdateVO vO) {
        ingredientService.update(id, vO);
    }

    @GetMapping("/{id}")
    public IngredientDTO getById(@Valid @NotNull @PathVariable("id") String id) {
        return ingredientService.getById(id);
    }

    @GetMapping
    public Page<IngredientDTO> query(@Valid IngredientQueryVO vO) {
        return ingredientService.query(vO);
    }
}
