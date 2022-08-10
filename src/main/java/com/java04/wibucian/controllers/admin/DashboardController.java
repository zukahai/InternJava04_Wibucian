package com.java04.wibucian.controllers.admin;

import com.java04.wibucian.dtos.TypeTableDTO;
import com.java04.wibucian.models.TypeTable;
import com.java04.wibucian.services.TypeTableService;
import com.java04.wibucian.vos.TablecfUpdateVO;
import com.java04.wibucian.vos.TypeTableQueryVO;
import com.java04.wibucian.vos.TypeTableUpdateVO;
import com.java04.wibucian.vos.TypeTableVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
@RequestMapping("admin/dashboard")
public class DashboardController {

    @Autowired

    private TypeTableService typeTableService;

    @GetMapping("/")
    public String Home(ModelMap modelMap)throws Exception {
        modelMap.addAttribute("typeTables", typeTableService.findAll());
        return "admin/dashboard/index";
    }
}
