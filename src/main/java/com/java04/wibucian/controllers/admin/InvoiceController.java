package com.java04.wibucian.controllers.admin;

import com.java04.wibucian.dtos.InvoiceDTO;
import com.java04.wibucian.models.InvoiceNoMapPing;
import com.java04.wibucian.services.InvoiceService;
import com.java04.wibucian.vos.InvoiceQueryVO;
import com.java04.wibucian.vos.InvoiceUpdateVO;
import com.java04.wibucian.vos.InvoiceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

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
        modelMap.addAttribute("invoice", invoiceService.findById(id));
        return "/admin/invoice/detail";
    }

    @RequestMapping(value = {"store-one/", "store-one"}, method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Object> storeOne(@Valid @RequestBody InvoiceVO vO) {
        vO.setIdEmployee("Employee00005");
        return ResponseEntity.ok().body(invoiceService.save_one(vO));
    }
}


