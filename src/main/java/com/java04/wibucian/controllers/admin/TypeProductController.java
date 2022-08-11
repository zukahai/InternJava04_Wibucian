package com.java04.wibucian.controllers.admin;

import com.java04.wibucian.dtos.TypeProductDTO;
import com.java04.wibucian.models.Product;
import com.java04.wibucian.models.Sale;
import com.java04.wibucian.models.TypeProduct;
import com.java04.wibucian.models.TypeTable;
import com.java04.wibucian.services.TypeProductService;
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
import java.util.Set;

@Validated
@Controller
@RequestMapping("admin/typeProduct")
public class TypeProductController {

    public static final int limit = 5;

    private TypeProduct TypeProductService;

    @GetMapping("/")
    public String Home(ModelMap modelMap) throws Exception {
        int page = 1;
        Pageable pageable = PageRequest.of(page - 1, limit);
        int totalPage = typeProductService.getTotalPage(limit);
        modelMap.addAttribute("page", page);
        modelMap.addAttribute("totalPage", totalPage);
        modelMap.addAttribute("typeProduct", typeProductService.findAll(pageable));
        return "admin/typeProduct/index";
    }


    @GetMapping("/page/{page}")
    public String HomePage(ModelMap modelMap, @Valid @NotNull @PathVariable("page") int page)throws Exception {
        int totalPage = typeProductService.getTotalPage(limit);
        page = (page < 1) ? 1 : page;
        page = (page > totalPage) ? totalPage : page;
        Pageable pageable = PageRequest.of(page - 1, limit);
        modelMap.addAttribute("page", page);
        modelMap.addAttribute("totalPage", totalPage);
        modelMap.addAttribute("typeProduct", typeProductService.findAll(pageable));
        return "admin/typeProduct/index";
    }
    @GetMapping("/create")
    public String createProductPage(ModelMap modelMap) throws Exception {
        return "admin/typeProduct/create";
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String createNewTypeProduct(ModelMap modelMap, @Valid TypeProductVO typeProductVO) throws Exception {
        String productId = this.typeProductService.save(typeProductVO);
        return "redirect:/admin/typeProduct/";
    }

    //edit

    @GetMapping("/edit/{id}")
    public String HomeEdit(ModelMap modelMap, @Valid @NotNull @PathVariable("id") String id) throws Exception {
        TypeProduct typeProduct = typeProductService.findById(id);
        modelMap.addAttribute("typeProduct", typeProduct);
        return "admin/typeProduct/edit";
    }

    @RequestMapping(value = "/edit/{id}",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String editTypeProduct(ModelMap modelMap,
                                  @Valid TypeProductUpdateVO typeProductUpdateVO,
                                  @PathVariable("id") String typeProductId) throws Exception {
        this.typeProductService.update(typeProductId, typeProductUpdateVO);
        return "redirect:/admin/typeProduct/";
    }
    //edit

    //delete
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseEntity<Object> delete(@PathVariable("id") String id) {
        try {
            typeProductService.delete(id);
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
    //delete

    @Autowired
    private TypeProductService typeProductService;

    @PostMapping
    public String save(@Valid @RequestBody TypeProductVO vO) {
        return typeProductService.save(vO)
                .toString();
    }


    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") String id,
                       @Valid @RequestBody TypeProductUpdateVO vO) {
        typeProductService.update(id, vO);
    }

    @GetMapping("/{id}")
    public TypeProductDTO getById(@Valid @NotNull @PathVariable("id") String id) {
        return typeProductService.getById(id);
    }

    @GetMapping
    public Page<TypeProductDTO> query(@Valid TypeProductQueryVO vO) {
        return typeProductService.query(vO);
    }
}
