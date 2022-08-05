package com.java04.wibucian.controllers.admin;

import com.java04.wibucian.dtos.OrdercfDTO;
import com.java04.wibucian.models.Ordercf;
import com.java04.wibucian.services.OrdercfService;
import com.java04.wibucian.vos.OrdercfQueryVO;
import com.java04.wibucian.vos.OrdercfUpdateVO;
import com.java04.wibucian.vos.OrdercfVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Validated
@Controller
@RequestMapping("/ordercf")
public class OrdercfController {
    @Autowired
    private OrdercfService ordercfService;

    @RequestMapping(path = {"/", ""}, method = {RequestMethod.POST, RequestMethod.GET})
    private String index(ModelMap modelMap) {


        return "admin/order/index";
    }
    @RequestMapping(path = {"/create", "/create/"}, method = {RequestMethod.POST, RequestMethod.GET})
    private String create(ModelMap modelMap) {
        return "admin/order/create";
    }


    //    @Autowired
//    private OrdercfService ordercfService;
//
//    @PostMapping
//    public String save(@Valid @RequestBody OrdercfVO vO) {
//        return ordercfService.save(vO).toString();
//    }
//
//    @DeleteMapping("/{id}")
//    public void delete(@Valid @NotNull @PathVariable("id") String id) {
//        ordercfService.delete(id);
//    }
//
//    @PutMapping("/{id}")
//    public void update(@Valid @NotNull @PathVariable("id") String id,
//                       @Valid @RequestBody OrdercfUpdateVO vO) {
//        ordercfService.update(id, vO);
//    }
//
//    @GetMapping("/{id}")
//    public OrdercfDTO getById(@Valid @NotNull @PathVariable("id") String id) {
//        return ordercfService.getById(id);
//    }
//
//    @GetMapping
//    public Page<OrdercfDTO> query(@Valid OrdercfQueryVO vO) {
//        return ordercfService.query(vO);
//    }
    //delete get method

}

