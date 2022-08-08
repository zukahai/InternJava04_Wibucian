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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.HashMap;

@Validated
@Controller
@RequestMapping("admin/typeTable")
public class TypeTableController {
    public static final int limit = 5;
    @Autowired
    private TypeTableService typeTableService;

    @GetMapping("/")
    public String Home(ModelMap modelMap)throws Exception {
        int page = 1;
        Pageable pageable = PageRequest.of(page - 1, limit);
        int totalPage = typeTableService.getTotalPage(limit);
        modelMap.addAttribute("page", page);
        modelMap.addAttribute("totalPage", totalPage);
        modelMap.addAttribute("typeTables", typeTableService.findAll(pageable));
        return "admin/typeTable/index";
    }

    @GetMapping("/page/{page}")
    public String HomePage(ModelMap modelMap, @Valid @NotNull @PathVariable("page") int page)throws Exception {
        int totalPage = typeTableService.getTotalPage(limit);
        page = (page < 1) ? 1 : page;
        page = (page > totalPage) ? totalPage : page;
        Pageable pageable = PageRequest.of(page - 1, limit);
        modelMap.addAttribute("page", page);
        modelMap.addAttribute("totalPage", totalPage);
        modelMap.addAttribute("typeTables", typeTableService.findAll(pageable));
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
        return "redirect:/admin/typeTable/";
    }

    @PostMapping
    public String save(@Valid @RequestBody TypeTableVO vO) {
        return typeTableService.save(vO).toString();
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseEntity<Object> delete(@PathVariable("id") String id) {
        try {
            typeTableService.delete(id);
            HashMap<String, Object> map = new HashMap<>();
            map.put("check", true);
            map.put("value", "test");
            return ResponseEntity.ok().body(map);
        } catch (Exception e) {
            e.printStackTrace();
            HashMap<String, Object> map = new HashMap<>();
            map.put("check", false);
            map.put("value", "test");
            return ResponseEntity.ok().body(map);
        }
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
