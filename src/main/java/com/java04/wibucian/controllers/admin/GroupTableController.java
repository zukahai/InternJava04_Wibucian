package com.java04.wibucian.controllers.admin;

import com.java04.wibucian.dtos.GroupTableDTO;
import com.java04.wibucian.models.GroupTable;
import com.java04.wibucian.models.TypeTable;
import com.java04.wibucian.services.DetailGroupTableService;
import com.java04.wibucian.services.GroupTableService;
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

@Validated
@Controller
@RequestMapping("admin/groupTable")
public class GroupTableController {
    public static final int limit = 5;
    @Autowired
    private GroupTableService groupTableService;

    @GetMapping("/")
    public String Home(ModelMap modelMap)throws Exception {
        int page = 1;
        Pageable pageable = PageRequest.of(page - 1, limit);
        int totalPage = groupTableService.getTotalPage(limit);
        modelMap.addAttribute("page", page);
        modelMap.addAttribute("totalPage", totalPage);
        modelMap.addAttribute("groupTables", groupTableService.findAllHaiZuka(pageable));
        return "admin/groupTable/index";
    }

    @GetMapping("/page/{page}")
    public String HomePage(ModelMap modelMap, @Valid @NotNull @PathVariable("page") int page)throws Exception {
        int totalPage = groupTableService.getTotalPage(limit);
        page = (page < 1) ? 1 : page;
        page = (page > totalPage) ? totalPage : page;
        Pageable pageable = PageRequest.of(page - 1, limit);
        modelMap.addAttribute("page", page);
        modelMap.addAttribute("totalPage", totalPage);
        modelMap.addAttribute("groupTables", groupTableService.findAllHaiZuka(pageable));
        return "admin/groupTable/index";
    }

    @GetMapping("/create")
    public String createProductPage(ModelMap modelMap) throws Exception {
        return "admin/groupTable/create";
    }

    @GetMapping("/edit/{id}")
    public String HomeEdit(ModelMap modelMap, @Valid @NotNull @PathVariable("id") String id)throws Exception {
        GroupTable groupTable = groupTableService.findById(id);
        modelMap.addAttribute("groupTable", groupTable);
        return "admin/groupTable/edit";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseEntity<Object> delete(@PathVariable("id") String id) {
        try {
            groupTableService.delete(id);
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

    @RequestMapping(value = "/deleteDetail/{id}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseEntity<Object> deleteDetail(@PathVariable("id") String id) {
        try {
            groupTableService.deleteDetail(id);
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

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String createNewProduct(ModelMap modelMap,  @RequestBody MultiValueMap<String, String> formData) throws Exception {
        String nameGroupTable = formData.get("groupName").get(0);
        groupTableService.save(nameGroupTable);
        return "redirect:/admin/groupTable/";
    }

    @RequestMapping(value = "/edit",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String editTable(ModelMap modelMap,
                            @Valid GroupTableUpdateVO groupTableUpdateVO,
                            @RequestBody MultiValueMap<String, String> formData) throws Exception {
        String idTable =  formData.get("idGroupTable").get(0);
        this.groupTableService.update(idTable, groupTableUpdateVO);
        return "redirect:/admin/groupTable/";
    }

    @PostMapping
    public String save(@Valid @RequestBody GroupTableVO vO) {
        return groupTableService.save(vO).toString();
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") String id,
                       @Valid @RequestBody GroupTableUpdateVO vO) {
        groupTableService.update(id, vO);
    }

    @GetMapping("/{id}")
    public GroupTableDTO getById(@Valid @NotNull @PathVariable("id") String id) {
        return groupTableService.getById(id);
    }

    @GetMapping
    public Page<GroupTableDTO> query(@Valid GroupTableQueryVO vO) {
        return groupTableService.query(vO);
    }
}
