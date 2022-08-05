package com.java04.wibucian.controllers.admin;

import com.java04.wibucian.dtos.TypeTableDTO;
import com.java04.wibucian.services.TypeTableService;
import com.java04.wibucian.vos.TypeTableQueryVO;
import com.java04.wibucian.vos.TypeTableUpdateVO;
import com.java04.wibucian.vos.TypeTableVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@Controller
@RequestMapping("admin/typeTable")
public class TypeTableController {

    @Autowired
    private TypeTableService typeTableService;

    @GetMapping("/")
    public String Home(ModelMap modelMap)throws Exception {

        modelMap.addAttribute("typeTables", typeTableService.findAll());
        return "admin/typeTable/index";
    }

    @GetMapping("/create")
    public String createProductPage(ModelMap modelMap) throws Exception {
        return "admin/typeTable/create";
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String createNewProduct(ModelMap modelMap, @Valid TypeTableVO typeTableVO) throws Exception {
        String productId = this.typeTableService.save(typeTableVO);
        System.out.println(typeTableVO);
        return "redirect:/admin/typeTable/";
    }

    @PostMapping
    public String save(@Valid @RequestBody TypeTableVO vO) {
        return typeTableService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") String id) {
        typeTableService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") String id,
                       @Valid @RequestBody TypeTableUpdateVO vO) {
        typeTableService.update(id, vO);
    }

    @GetMapping("/{id}")
    public TypeTableDTO getById(@Valid @NotNull @PathVariable("id") String id) {
        return typeTableService.getById(id);
    }

    @GetMapping
    public Page<TypeTableDTO> query(@Valid TypeTableQueryVO vO) {
        return typeTableService.query(vO);
    }
}
