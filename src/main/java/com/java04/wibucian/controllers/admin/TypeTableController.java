package com.java04.wibucian.controllers.admin;

import com.java04.wibucian.dtos.TypeTableDTO;
import com.java04.wibucian.models.TypeTable;
import com.java04.wibucian.services.TypeTableService;
import com.java04.wibucian.vos.TablecfUpdateVO;
import com.java04.wibucian.vos.TypeTableQueryVO;
import com.java04.wibucian.vos.TypeTableUpdateVO;
import com.java04.wibucian.vos.TypeTableVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
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


    @GetMapping("/edit/{id}")
    public String HomeEdit(ModelMap modelMap, @Valid @NotNull @PathVariable("id") String id)throws Exception {
        TypeTable typeTable = typeTableService.findById(id);
        modelMap.addAttribute("typeTable", typeTable);
        return "admin/typeTable/edit";
    }

    @GetMapping("/create")
    public String createTypeTablePage(ModelMap modelMap) throws Exception {
        return "admin/typeTable/create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String createTypeTable(ModelMap modelMap, @Valid TypeTableVO typeTableVO) throws Exception {
        String productId = this.typeTableService.save(typeTableVO);
        System.out.println(typeTableVO);
        return "redirect:/admin/typeTable/";
    }

    @RequestMapping(value = "/edit",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String editTypeTable(ModelMap modelMap,
                                @Valid TypeTableUpdateVO typeTableUpdateVO,
                                @RequestBody MultiValueMap<String, String> formData) throws Exception {
        String idTypeTable =  formData.get("idTypeTable").get(0);
        this.typeTableService.update(idTypeTable, typeTableUpdateVO);
        System.out.println(typeTableUpdateVO);
        System.out.println("Editor");
        return "redirect:/admin/typeTable/";
    }

    @PostMapping
    public String save(@Valid @RequestBody TypeTableVO vO) {
        return typeTableService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") String id) {
        System.out.println("delete ");
        typeTableService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") String id,
                       @Valid @RequestBody TypeTableUpdateVO vO) {
        typeTableService.update(id, vO);
    }

    @GetMapping
    public Page<TypeTableDTO> query(@Valid TypeTableQueryVO vO) {
        return typeTableService.query(vO);
    }
}
