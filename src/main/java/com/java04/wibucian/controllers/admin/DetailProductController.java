package com.java04.wibucian.controllers.admin;

import com.java04.wibucian.dtos.DetailProductDTO;
import com.java04.wibucian.services.DetailProductService;
import com.java04.wibucian.services.IngredientService;
import com.java04.wibucian.services.ProductService;
import com.java04.wibucian.vos.DetailProductQueryVO;
import com.java04.wibucian.vos.DetailProductUpdateVO;
import com.java04.wibucian.vos.DetailProductVO;
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
@RequestMapping("admin/detailProduct")
public class DetailProductController {

    @Autowired
    private DetailProductService detailProductService;
    private ProductService productService;

    private IngredientService ingredientService; ;

    public DetailProductController(ProductService productService, IngredientService ingredientService) {
        this.productService = productService;
        this.ingredientService = ingredientService;
    }

    @GetMapping("/view/{idProduct}")
    public String HomeView(ModelMap modelMap, @Valid @NotNull @PathVariable("idProduct") String idProduct)throws Exception {
        productService.updatePrice(idProduct);
        modelMap.addAttribute("product", productService.findById(idProduct));
        modelMap.addAttribute("ingredients", detailProductService.getIngredientNotSelect(idProduct));
        modelMap.addAttribute("detailProducts", detailProductService.findAllByProductId(idProduct));
        modelMap.addAttribute("priceProduct", detailProductService.getPriceProductFormIngerdienrt(idProduct));
        modelMap.addAttribute("priceSell", detailProductService.getProductSellPrice(idProduct));
        return "admin/product/detailIngredient";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseEntity<Object> delete(@PathVariable("id") String id) {
        try {
            detailProductService.delete(id);
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

    @RequestMapping(value = "/view/{id}",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String addIngredient(ModelMap modelMap,
                                @Valid @NotNull @PathVariable("id") String id,
                                @RequestBody MultiValueMap<String, String> formData) throws Exception {
        String idIngredient = formData.get("idIngredient").get(0);
        Float quantity = Float.parseFloat(formData.get("quantity").get(0));
        detailProductService.save(id, idIngredient, quantity);
        productService.updatePrice(id);
        return "redirect:/admin/detailProduct/view/" + id;
    }

    @PostMapping
    public String save(@Valid @RequestBody DetailProductVO vO) {
        return detailProductService.save(vO).toString();
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") String id,
                       @Valid @RequestBody DetailProductUpdateVO vO) {
        detailProductService.update(id, vO);
    }

    @GetMapping("/{id}")
    public DetailProductDTO getById(@Valid @NotNull @PathVariable("id") String id) {
        return detailProductService.getById(id);
    }

    @GetMapping
    public Page<DetailProductDTO> query(@Valid DetailProductQueryVO vO) {
        return detailProductService.query(vO);
    }
}
