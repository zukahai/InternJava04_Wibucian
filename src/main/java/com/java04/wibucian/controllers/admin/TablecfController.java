package com.java04.wibucian.controllers.admin;

import com.java04.wibucian.dtos.TablecfDTO;
import com.java04.wibucian.services.TablecfService;
import com.java04.wibucian.services.TypeTableService;
import com.java04.wibucian.vos.TablecfQueryVO;
import com.java04.wibucian.vos.TablecfUpdateVO;
import com.java04.wibucian.vos.TablecfVO;
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
@RequestMapping("admin/table")
public class TablecfController {

    @Autowired
    private TablecfService tablecfService;
    private TypeTableService typeTableService;

    public TablecfController(TypeTableService typeTableService) {
        this.typeTableService = typeTableService;
    }

    @GetMapping("/")
    public String Home(ModelMap modelMap)throws Exception {
        modelMap.addAttribute("tables", tablecfService.findAll());
        return "admin/table/index";
    }

    @GetMapping("/create")
    public String createProductPage(ModelMap modelMap) throws Exception {
        modelMap.addAttribute("typeTables", typeTableService.findAll());
        return "admin/table/create";
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String createNewProduct(ModelMap modelMap, @Valid TablecfVO tablecf, @RequestBody MultiValueMap<String, String> formData) throws Exception {
        String idTypeTable =  formData.get("idTypeTable").get(0);
        String typeTableId = this.tablecfService.save(tablecf, idTypeTable);
        return "redirect:/admin/table/";
    }

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
