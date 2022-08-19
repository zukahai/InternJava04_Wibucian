package com.java04.wibucian.controllers.admin;

import com.java04.wibucian.dtos.DetailInvoiceDTO;
import com.java04.wibucian.services.DetailInvoiceService;
import com.java04.wibucian.vos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Validated
@RestController
@RequestMapping("admin/detailInvoice")
public class DetailInvoiceController {

    @Autowired
    private DetailInvoiceService detailInvoiceService;

    @PostMapping
    public String save(@Valid @RequestBody DetailInvoiceVO vO) {
        return detailInvoiceService.save(vO).toString();
    }
    @RequestMapping(value = {"/store","/store/"}, method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Object> store (@Valid @RequestBody DetailInvoiceVO vO){
        try{
            System.out.println(vO);
            detailInvoiceService.save(vO);
            HashMap<String, Object> map = new HashMap<>();
            map.put("check", true);
            map.put("value", "test");
            return ResponseEntity.ok().body(map);
        }
        catch (Exception e){
            HashMap<String, Object> map = new HashMap<>();
            map.put("check", false);
            map.put("value", "test");
            return ResponseEntity.ok().body(map);
        }
    }
    @GetMapping(value = {"/delete/{id}","/delete/{id}/"})
    @ResponseBody
    public ResponseEntity<Object> delete_2 (@PathVariable("id") String id){
        try{
            detailInvoiceService.delete(id);
            HashMap<String, Object> map = new HashMap<>();
            map.put("check", true);
            map.put("value", "test");
            return ResponseEntity.ok().body(map);
        }
        catch (Exception e){
            HashMap<String, Object> map = new HashMap<>();
            map.put("check", false);
            map.put("value", "test");
            return ResponseEntity.ok().body(map);
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") String id) {
        detailInvoiceService.delete(id);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Object> update(@Valid @NotNull @PathVariable("id") String id, @Valid @RequestBody DetailInvoiceUpdateVO vO) {
        try {
            System.out.println(vO);
            detailInvoiceService.update(id, vO);
            HashMap<String, Object> map = new HashMap<>();
            map.put("check", true);
            map.put("value", "ok");
            return ResponseEntity.ok().body(map);
        } catch (Exception e) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("check", false);
            map.put("value", "Lỗi");
            return ResponseEntity.ok().body(map);
        }
    }
    @GetMapping("/{id}")
    public DetailInvoiceDTO getById(@Valid @NotNull @PathVariable("id") String id) {
        return detailInvoiceService.getById(id);
    }

    @GetMapping
    public Page<DetailInvoiceDTO> query(@Valid DetailInvoiceQueryVO vO) {
        return detailInvoiceService.query(vO);
    }

    @PostMapping(value = "/statistical",consumes = "application/json")
    @ResponseBody
    public ResponseEntity<Object> statistical (@Valid @RequestBody StatisticalVO vO){
        System.out.println(vO);
        Instant start = Instant.parse(vO.getTimeStart()+"T00:00:00Z");
        Instant end = Instant.parse(vO.getTimeEnd()+"T00:00:00Z");
        List<StatisticalResultVO> statisticalResultVOList = detailInvoiceService.getStatisticalResult(start, end);
        HashMap<String, Object> map = new HashMap<>();
        map.put("data", statisticalResultVOList);
        map.put("turnover", detailInvoiceService.getTurnover(statisticalResultVOList));
        map.put("profit", detailInvoiceService.getProfit(statisticalResultVOList));
        return ResponseEntity.ok().body(map);
    }
}