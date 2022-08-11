package com.java04.wibucian.controllers.admin;

import com.java04.wibucian.dtos.ImportGoodsDTO;
import com.java04.wibucian.models.ImportGoods;
import com.java04.wibucian.services.ImportGoodsService;
import com.java04.wibucian.vos.ImportGoodsQueryVO;
import com.java04.wibucian.vos.ImportGoodsUpdateVO;
import com.java04.wibucian.vos.ImportGoodsVO;
import com.java04.wibucian.vos.TypeTableUpdateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    @GetMapping("/edit/{id}")
    public String HomeEdit(ModelMap modelMap, @Valid @NotNull @PathVariable("id") String id)throws Exception {
        ImportGoods importGoods = importGoodsService.findById(id);
        modelMap.addAttribute("typeTable", importGoods);
        return "admin/importgoods/edit";
    }

    @RequestMapping(value = "/edit",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String editTypeTable(ModelMap modelMap,
                                @Valid TypeTableUpdateVO typeTableUpdateVO,
                                @RequestBody MultiValueMap<String, String> formData) throws Exception {
        String idTypeTable =  formData.get("idImportGoods").get(0);
        this.importGoodsService.update(idTypeTable, new ImportGoodsUpdateVO());
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