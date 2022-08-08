package com.java04.wibucian.controllers.admin;

import com.java04.wibucian.dtos.TablecfDTO;
import com.java04.wibucian.models.Tablecf;
import com.java04.wibucian.models.TypeTable;
import com.java04.wibucian.services.TablecfService;
import com.java04.wibucian.services.TypeTableService;
import com.java04.wibucian.vos.TablecfQueryVO;
import com.java04.wibucian.vos.TablecfUpdateVO;
import com.java04.wibucian.vos.TablecfVO;
import com.java04.wibucian.vos.TypeTableUpdateVO;
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
@RequestMapping("admin/table")
public class TablecfController {
    public static final int limit = 5;
    @Autowired
    private TablecfService tablecfService;
    private TypeTableService typeTableService;

    public TablecfController(TypeTableService typeTableService) {
        this.typeTableService = typeTableService;
    }

    @GetMapping("/")
    public String Home(ModelMap modelMap)throws Exception {
        int page = 1;
        Pageable pageable = PageRequest.of(page - 1, limit);
        int totalPage = tablecfService.getTotalPage(limit);
        modelMap.addAttribute("page", page);
        modelMap.addAttribute("totalPage", totalPage);
        modelMap.addAttribute("tables", tablecfService.findAllHaiZuka(pageable));
        return "admin/table/index";
    }

    @GetMapping("/page/{page}")
    public String HomePage(ModelMap modelMap, @Valid @NotNull @PathVariable("page") int page)throws Exception {
        int totalPage = tablecfService.getTotalPage(limit);
        page = (page < 1) ? 1 : page;
        page = (page > totalPage) ? totalPage : page;
        Pageable pageable = PageRequest.of(page - 1, limit);
        modelMap.addAttribute("page", page);
        modelMap.addAttribute("totalPage", totalPage);
        modelMap.addAttribute("tables", tablecfService.findAllHaiZuka(pageable));
        return "admin/table/index";
    }

    @GetMapping("/create")
    public String createProductPage(ModelMap modelMap) throws Exception {
        modelMap.addAttribute("typeTables", typeTableService.findAll());
        return "admin/table/create";
    }

    @GetMapping("/edit/{id}")
    public String HomeEdit(ModelMap modelMap, @Valid @NotNull @PathVariable("id") String id)throws Exception {
        Tablecf tablecf = tablecfService.findById(id);
        modelMap.addAttribute("typeTables", typeTableService.findAll());
        modelMap.addAttribute("tablecf", tablecf);
        return "admin/table/edit";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseEntity<Object> delete(@PathVariable("id") String id) {
        try {
            tablecfService.delete(id);
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
//
//    @GetMapping("/delete/{id}")
//    public String HomeDelete(ModelMap modelMap, @Valid @NotNull @PathVariable("id") String id)throws Exception {
//        tablecfService.delete(id);
//        return "redirect:/admin/table/";
//    }


    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String createNewTable(ModelMap modelMap, @Valid TablecfVO tablecf, @RequestBody MultiValueMap<String, String> formData) throws Exception {
        String idTypeTable =  formData.get("idTypeTable").get(0);
        String typeTableId = this.tablecfService.save(tablecf, idTypeTable);
        return "redirect:/admin/table/";
    }

    @RequestMapping(value = "/edit",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String editTable(ModelMap modelMap,
                                @Valid TablecfUpdateVO tablecfUpdateVO,
                                @RequestBody MultiValueMap<String, String> formData) throws Exception {
        String idTable =  formData.get("idTablecf").get(0);
        String idTypeTable = formData.get("idTypeTable").get(0);
        this.tablecfService.update(idTable, tablecfUpdateVO, idTypeTable);
        return "redirect:/admin/table/";
    }

    @PostMapping
    public String save(@Valid @RequestBody TablecfVO vO) {
        return tablecfService.save(vO).toString();
    }

//    @DeleteMapping("/{id}")
//    public void delete(@Valid @NotNull @PathVariable("id") String id) {
//        tablecfService.delete(id);
//    }

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
