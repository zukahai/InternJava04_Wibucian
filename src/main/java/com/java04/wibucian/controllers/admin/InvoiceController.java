package com.java04.wibucian.controllers.admin;

import com.java04.wibucian.services.InvoiceService;
import com.java04.wibucian.vos.InvoiceUpdateVO;
import com.java04.wibucian.vos.InvoiceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;

@Validated
@Controller
@RequestMapping("admin/invoice")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;



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
    //paginate page
    @GetMapping(value = {"/{page}", "/", ""})
    public String paginate(@PathVariable(required = false) String page, ModelMap modelMap, @RequestParam(defaultValue = "10") int limit) {
        //check page != null
        if (page == null) {
            page = "1";
        }
        int pageInt;
        try {
            pageInt = Integer.parseInt(page);
        } catch (Exception e) {
            return "redirect:/invoice";
        }
        Pageable pageable = PageRequest.of(pageInt - 1, limit);
        int allPage = invoiceService.getTotalPage(limit);
        int count = invoiceService.getCount();
        System.out.println(pageInt);
        if (limit > count || limit<=0) {
            limit = 10;
        }
        if (pageInt > allPage) {
            return "redirect:/invoice/paginate/";
        }
        if (pageInt < 1 ) {
            return "redirect:/invoice/paginate/";
        }
        modelMap.addAttribute("currentPage", pageInt);
        modelMap.addAttribute("allPage", allPage);
        modelMap.addAttribute(("limit"), limit);
        modelMap.addAttribute("listInvoice", invoiceService.getPage(pageable));
        return "/admin/invoice/index";
    }
    @RequestMapping(value = {"/statistical"}, method = RequestMethod.GET)
    public String statistical(ModelMap modelMap) {
        return "/admin/invoice/statistical";
    }


}
