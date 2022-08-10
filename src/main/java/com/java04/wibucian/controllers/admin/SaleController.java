package com.java04.wibucian.controllers.admin;

import com.java04.wibucian.dtos.SaleDTO;
import com.java04.wibucian.models.Product;
import com.java04.wibucian.models.Sale;
import com.java04.wibucian.services.ProductService;
import com.java04.wibucian.services.SaleService;
import com.java04.wibucian.vos.ProductUpdateVO;
import com.java04.wibucian.vos.SaleQueryVO;
import com.java04.wibucian.vos.SaleUpdateVO;
import com.java04.wibucian.vos.SaleVO;
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
@RequestMapping("admin/sale")
public class SaleController {

    @Autowired
    private SaleService saleService;
    private ProductService productService;
       public SaleController(SaleService saleService, ProductService productService) {
        this.saleService = saleService;
        this.productService = productService;
    }

    @GetMapping("/")
    public String Home(ModelMap modelMap)throws Exception {

        modelMap.addAttribute("sale", saleService.findAll());
        return "admin/sale/index";
    }

    //edit
    @GetMapping("/edit/{id}")
    public String HomeEdit(ModelMap modelMap, @Valid @NotNull @PathVariable("id") String id)throws Exception {
        Sale sale = saleService.findById(id);
        modelMap.addAttribute("sale", sale);
        return "admin/sale/edit";
    }

    @RequestMapping(value = "/edit",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String editSale(ModelMap modelMap,
                              @Valid SaleUpdateVO saleUpdateVO,
                              @RequestBody MultiValueMap<String, String> formData) throws Exception {
        String idSale =  formData.get("idSale").get(0);
        this.saleService.update(idSale, saleUpdateVO);
        return "redirect:/admin/sale/";
    }

    //delete

     @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseEntity<Object> delete(@PathVariable("id") String id) {
        try {
            saleService.delete(id);
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

    //edit

    @GetMapping("/create")
    public String createSaletPage(ModelMap modelMap) throws Exception {
        modelMap.addAttribute("sale", this.saleService.findAll());
        return "admin/sale/create";
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String createNewSales(ModelMap modelMap, @Valid SaleVO saleVO) throws Exception {
        String productId = this.saleService.save(saleVO);
        return "redirect:/admin/sale/";
    }

    //edit
    /*
    @GetMapping("/edit/{id}")
    public String HomeEdit(ModelMap modelMap, @Valid @NotNull @PathVariable("id") String id)throws Exception {
        Sale sale = saleService.findById(id);
        modelMap.addAttribute("sale", typeProductService.findAll());
        modelMap.addAttribute("product", product);
        return "admin/sale/edit";
    }



    @RequestMapping(value = "/edit",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String editProduct(ModelMap modelMap,
                              @Valid ProductUpdateVO productUpdateVO,
                              @RequestBody MultiValueMap<String, String> formData) throws Exception {
        String idProduct =  formData.get("idProduct").get(0);
        String idProductType = formData.get("idProductType").get(0);
        this.productService.update(idProduct, productUpdateVO, idProductType);
        return "redirect:/admin/product/";
    }


     */


    @PostMapping
    public String save(@Valid SaleVO vO) {
        return saleService.save(vO).toString();
    }

    /*
    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") String id) {
        saleService.delete(id);
    }
    */


    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") String id,
                       @Valid @RequestBody SaleUpdateVO vO) {
        saleService.update(id, vO);
    }

    @GetMapping("/{id}")
    public SaleDTO getById(@Valid @NotNull @PathVariable("id") String id) {
        return saleService.getById(id);
    }

    @GetMapping
    public Page<SaleDTO> query(@Valid SaleQueryVO vO) {
        return saleService.query(vO);
    }
}
