package com.java04.wibucian.controllers.admin;

import com.java04.wibucian.dtos.EmployeeDTO;
import com.java04.wibucian.models.Employee;
import com.java04.wibucian.services.EmployeeService;
import com.java04.wibucian.vos.EmployeeQueryVO;
import com.java04.wibucian.vos.EmployeeUpdateVO;
import com.java04.wibucian.vos.EmployeeVO;
import com.java04.wibucian.vos.TypeTableVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.HashMap;

@Validated
@Controller
@RequestMapping("admin/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/")
    public String Home(ModelMap modelMap)throws Exception {
        modelMap.addAttribute("DanhSach", employeeService.findAll());
        return "admin/employee/index";
    }
    @GetMapping("/create")
    public String createEmployeePage(ModelMap modelMap) throws Exception {
        return "admin/employee/create";
    }
    @GetMapping("/view/{id}")
    public String viewEmployeePage(ModelMap modelMap,@Valid @NotNull @PathVariable("id") String id) throws Exception {
        modelMap.addAttribute("id",id);
        Employee employee = employeeService.findById(id);
        //System.out.println(id)
        modelMap.addAttribute("employee",employee);
        return "admin/employee/view";
    }
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String createNewEmployee(ModelMap modelMap, @Valid EmployeeVO employeeVO) throws Exception {
        String employeeId = this.employeeService.save(employeeVO);
        System.out.println(employeeVO);
        return "redirect:/admin/employee/";
    }

    @PostMapping
    public String save(@Valid @RequestBody EmployeeVO vO) {
        return employeeService.save(vO).toString();
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseEntity<Object> delete(@PathVariable("id") String id) {
        try {
            employeeService.delete(id);
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

    @PutMapping("/{id}")
    public void update(@Valid @NotNull @PathVariable("id") String id,
                       @Valid @RequestBody EmployeeUpdateVO vO) {
        employeeService.update(id, vO);
    }

    @GetMapping("/{id}")
    public EmployeeDTO getById(@Valid @NotNull @PathVariable("id") String id) {
        return employeeService.getById(id);
    }

    @GetMapping
    public Page<EmployeeDTO> query(@Valid EmployeeQueryVO vO) {
        return employeeService.query(vO);
    }
}
