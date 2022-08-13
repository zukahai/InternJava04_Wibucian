package com.java04.wibucian.controllers.admin;

import com.java04.wibucian.dtos.DetailGroupTableDTO;
import com.java04.wibucian.services.DetailGroupTableService;
import com.java04.wibucian.services.GroupTableService;
import com.java04.wibucian.services.TablecfService;
import com.java04.wibucian.vos.DetailGroupTableQueryVO;
import com.java04.wibucian.vos.DetailGroupTableUpdateVO;
import com.java04.wibucian.vos.DetailGroupTableVO;
import com.java04.wibucian.vos.TypeTableUpdateVO;
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
@RequestMapping("admin/detailGroupTable")
public class DetailGroupTableController {
    public static final int limit = 5;
    @Autowired
    private DetailGroupTableService detailGroupTableService;
    private GroupTableService groupTableService;
    private TablecfService tablecfService;

    public DetailGroupTableController(GroupTableService groupTableService, TablecfService tablecfService) {
        this.groupTableService = groupTableService;
        this.tablecfService = tablecfService;
    }

    @GetMapping("/view/{id}")
    public String HomeView(ModelMap modelMap, @Valid @NotNull @PathVariable("id") String id)throws Exception {
        modelMap.addAttribute("groupTable", groupTableService.findById(id));
        modelMap.addAttribute("tablecfs", detailGroupTableService.getTableNotMerged());

        int page = 1;
        Pageable pageable = PageRequest.of(page - 1, limit);
        int totalPage = detailGroupTableService.getTotalPageByIdGroup(limit, id);
        modelMap.addAttribute("page", page);
        modelMap.addAttribute("totalPage", totalPage);
        modelMap.addAttribute("tablecfList", detailGroupTableService.getByIdGroupTableHaiZuka(id, pageable));
        return "admin/groupTable/detail";
    }

    @GetMapping("/view/{id}/page/{page}")
    public String HomePage(ModelMap modelMap,
                           @Valid @NotNull @PathVariable("page") int page,
                           @Valid @NotNull @PathVariable("id") String id)throws Exception {
        modelMap.addAttribute("groupTable", groupTableService.findById(id));
        modelMap.addAttribute("tablecfs", detailGroupTableService.getTableNotMerged());

        int totalPage = detailGroupTableService.getTotalPageByIdGroup(limit, id);
        page = (page < 1) ? 1 : page;
        page = (page > totalPage) ? totalPage : page;

        Pageable pageable = PageRequest.of(page - 1, limit);

        modelMap.addAttribute("page", page);
        modelMap.addAttribute("totalPage", totalPage);
        modelMap.addAttribute("tablecfList", detailGroupTableService.getByIdGroupTableHaiZuka(id, pageable));
        return "admin/groupTable/detail";
    }



    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseEntity<Object> delete(@PathVariable("id") String id) {
        try {
            detailGroupTableService.delete(id);
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
    public String editTypeTable(ModelMap modelMap,
                                @Valid @NotNull @PathVariable("id") String id,
                                @RequestBody MultiValueMap<String, String> formData) throws Exception {
        String idTable = formData.get("idTable").get(0);
        detailGroupTableService.save(id, idTable);
        return "redirect:/admin/detailGroupTable/view/" + id;
    }

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") String id,
                       @Valid @RequestBody DetailGroupTableUpdateVO vO) {
        detailGroupTableService.update(id, vO);
    }

    @GetMapping("/{id}")
    public DetailGroupTableDTO getById(@Valid @NotNull @PathVariable("id") String id) {
        return detailGroupTableService.getById(id);
    }

    @GetMapping
    public Page<DetailGroupTableDTO> query(@Valid DetailGroupTableQueryVO vO) {
        return detailGroupTableService.query(vO);
    }
}
