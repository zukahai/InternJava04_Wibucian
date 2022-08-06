package com.java04.wibucian.controllers.admin;

import com.java04.wibucian.models.Ordercf;
import com.java04.wibucian.services.GroupTableService;
import com.java04.wibucian.services.OrdercfService;
import com.java04.wibucian.services.ProductService;
import com.java04.wibucian.vos.OrdercfVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Validated
@Controller
@RequestMapping("/ordercf")
public class OrdercfController {
    @Autowired
    private ProductService productService;

    @Autowired
    private GroupTableService groupTableService;

    @Autowired
    private OrdercfService ordercfService;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(ModelMap modelMap) {
        modelMap.addAttribute("groupTables", groupTableService.findAll());
        modelMap.addAttribute("products", productService.listAll());
        return "admin/order/create";
    }
    @RequestMapping(value = {"/store","/store/"}, method = RequestMethod.POST,
            produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseEntity<Object> store(@RequestBody List<OrdercfVO> ordercfList) {
        try {
            System.out.println(ordercfList);
            for(OrdercfVO ordercfVO: ordercfList){
                ordercfService.save(ordercfVO);
            }
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

}

