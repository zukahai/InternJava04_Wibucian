package com.java04.wibucian.controllers.admin;

import com.java04.wibucian.dtos.ImportGoodsDTO;
import com.java04.wibucian.models.ImportGoods;
import com.java04.wibucian.models.Ingredient;
import com.java04.wibucian.services.ImportGoodsService;
import com.java04.wibucian.vos.*;
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
@RequestMapping("admin/importgoods")
public class ImportGoodsController {
    public static final int limit = 5;
    @Autowired
    private ImportGoodsService importGoodsService;

    @GetMapping("/")
    public String Home(ModelMap modelMap)throws Exception {
        int page = 1;
        Pageable pageable = PageRequest.of(page - 1, limit);
        int totalPage = importGoodsService.getTotalPage(limit);
        modelMap.addAttribute("page", page);
        modelMap.addAttribute("totalPage", totalPage);
        modelMap.addAttribute("nhaphang", importGoodsService.findAll(pageable));

        return "admin/importgoods/index";
    }



    @GetMapping("/page/{page}")
    public String HomePage(ModelMap modelMap, @Valid @NotNull @PathVariable("page") int page)throws Exception {
        int totalPage = importGoodsService.getTotalPage(limit);
        page = (page < 1) ? 1 : page;
        page = (page > totalPage) ? totalPage : page;
        Pageable pageable = PageRequest.of(page - 1, limit);
        modelMap.addAttribute("page", page);
        modelMap.addAttribute("totalPage", totalPage);
        modelMap.addAttribute("nhaphang", importGoodsService.findAll(pageable));
        return "admin/importgoods/index";
    }


    @GetMapping("/create")
    public String createImportGoodsPage(ModelMap modelMap) throws Exception {
        return "admin/importgoods/create";
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String createImportGoods(ModelMap modelMap, @Valid ImportGoodsVO importgoodsVO) throws Exception {
        System.out.println(importgoodsVO);
        String productId = this.importGoodsService.save(importgoodsVO);

        return "redirect:/admin/importgoods/";
    }

    @PostMapping
    public String save(@Valid @RequestBody ImportGoodsVO vO) {
        // check xem mã thằng nhân viên

        return importGoodsService.save(vO).toString();
    }
    @GetMapping("/edit/{id}")
    public String HomeEdit(ModelMap modelMap, @Valid @NotNull @PathVariable("id") String id)throws Exception {
        ImportGoods importgoods = importGoodsService.findById(id);
        modelMap.addAttribute("nhaphang", importgoods);
        return "admin/importgoods/edit";
    }

    @RequestMapping(value = "/edit",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String editImportGoods(ModelMap modelMap,
                                 @Valid ImportGoodsUpdateVO importGoodsUpdateVO,
                                 @RequestBody MultiValueMap<String, String> formData) throws Exception {
        String idImportgoods =  formData.get("importgoodsId").get(0);
        this.importGoodsService.update(idImportgoods, importGoodsUpdateVO);
        return "redirect:/admin/importgoods/";
    }
//    @DeleteMapping("/{id}")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseEntity<Object> delete(@PathVariable("id") String id) {
        try {
            importGoodsService.delete(id);
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