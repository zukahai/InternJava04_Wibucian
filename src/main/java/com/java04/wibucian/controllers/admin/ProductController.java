package com.java04.wibucian.controllers.admin;

import com.java04.wibucian.dtos.ProductDTO;
import com.java04.wibucian.services.ProductService;
import com.java04.wibucian.vos.ProductQueryVO;
import com.java04.wibucian.vos.ProductUpdateVO;
import com.java04.wibucian.vos.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public String save(@Valid @RequestBody ProductVO vO) {
        return productService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") String id) {
        productService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") String id,
                       @Valid @RequestBody ProductUpdateVO vO) {
        productService.update(id, vO);
    }

    @GetMapping("/{id}")
    public ProductDTO getById(@Valid @NotNull @PathVariable("id") String id) {
        return productService.getById(id);
    }

    @GetMapping
    public Page<ProductDTO> query(@Valid ProductQueryVO vO) {
        return productService.query(vO);
    }
}
