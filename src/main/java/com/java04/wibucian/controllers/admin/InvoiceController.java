package com.java04.wibucian.controllers.admin;

import com.java04.wibucian.services.InvoiceService;
import com.java04.wibucian.vos.InvoiceUpdateVO;
import com.java04.wibucian.vos.InvoiceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;

@Validated
@Controller
@RequestMapping("/invoice")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping
    public String index(ModelMap modelMap) {
        modelMap.addAttribute("listInvoice", invoiceService.findAll());
        return "/admin/invoice/index";
    }

    @GetMapping(value = {"/detail/{id}"})
    public String detail(@PathVariable String id, ModelMap modelMap) {
        modelMap.addAttribute("products", invoiceService.findAllProduct());
        modelMap.addAttribute("invoice", invoiceService.findById(id));
        return "/admin/invoice/detail";
    }

    @RequestMapping(value = {"store-one/", "store-one"}, method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Object> storeOne(@Valid @RequestBody InvoiceVO vO) {
        vO.setIdEmployee("Employee00005");
        return ResponseEntity.ok().body(invoiceService.save_one(vO));
    }

    @DeleteMapping(value = {"/{id}"})
    @ResponseBody
    public ResponseEntity<Object> delete(@PathVariable String id) {
        try {
            return ResponseEntity.ok().body(invoiceService.delete(id));
        } catch (Exception e) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("check", false);
            map.put("value", "Lỗi");
            return ResponseEntity.ok().body(map);
        }
    }

    @PutMapping(value = {"/{id}"})
    @ResponseBody
    public ResponseEntity<Object> update(@PathVariable String id, @Valid @RequestBody InvoiceUpdateVO vO) {
        try {
            System.out.println(vO.getStatus());
            invoiceService.update(id, vO);
            HashMap<String, Object> map = new HashMap<>();
            map.put("check", true);
            map.put("value", "Cập nhật thành công");
            return ResponseEntity.ok().body(map);
        } catch (Exception e) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("check", false);
            map.put("value", "Lỗi");
            return ResponseEntity.ok().body(map);
        }
    }

}


