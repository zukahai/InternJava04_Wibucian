package com.java04.wibucian.controllers.admin;

import com.java04.wibucian.dtos.GroupTableDTO;
import com.java04.wibucian.services.GroupTableService;
import com.java04.wibucian.vos.GroupTableQueryVO;
import com.java04.wibucian.vos.GroupTableUpdateVO;
import com.java04.wibucian.vos.GroupTableVO;
import com.java04.wibucian.vos.TablecfVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@Controller
@RequestMapping("admin/groupTable")
public class GroupTableController {

    @Autowired
    private GroupTableService groupTableService;

    @GetMapping("/")
    public String Home(ModelMap modelMap)throws Exception {
        modelMap.addAttribute("groupTables", groupTableService.findAll());
        return "admin/groupTable/index";
    }

    @GetMapping("/create")
    public String createProductPage(ModelMap modelMap) throws Exception {
        return "admin/groupTable/create";
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String createNewProduct(ModelMap modelMap,  @RequestBody MultiValueMap<String, String> formData) throws Exception {
        String nameGroupTable = formData.get("groupName").get(0);
        groupTableService.save(nameGroupTable);
        System.out.println(nameGroupTable);
        return "redirect:/admin/groupTable/";
    }

    @PostMapping
    public String save(@Valid @RequestBody GroupTableVO vO) {
        return groupTableService.save(vO).toString();
    }

    @DeleteMapping("/{id}")
    public void delete(@Valid @NotNull @PathVariable("id") String id) {
        groupTableService.delete(id);
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
