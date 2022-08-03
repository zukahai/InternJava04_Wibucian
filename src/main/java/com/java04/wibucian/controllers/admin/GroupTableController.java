package com.java04.wibucian.controllers.admin;

import com.java04.wibucian.dtos.GroupTableDTO;
import com.java04.wibucian.services.GroupTableService;
import com.java04.wibucian.vos.GroupTableQueryVO;
import com.java04.wibucian.vos.GroupTableUpdateVO;
import com.java04.wibucian.vos.GroupTableVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/groupTable")
public class GroupTableController {

    @Autowired
    private GroupTableService groupTableService;

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
