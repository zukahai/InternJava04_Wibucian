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
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

@Validated
@Controller
@RequestMapping("admin/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/")
    public String Home(ModelMap modelMap) throws Exception {
        modelMap.addAttribute("DanhSach", employeeService.findAll());
        return "admin/employee/index";
    }

    @GetMapping("/create")
    public String createEmployeePage(ModelMap modelMap) throws Exception {
        return "admin/employee/create";
    }
    @GetMapping("/login")
    public String LoginEmployeePage(ModelMap modelMap) throws Exception {
        return "admin/employee/login";
    }

    @GetMapping("/view/{id}")
    public String viewEmployeePage(ModelMap modelMap, @Valid @NotNull @PathVariable("id") String id) throws Exception {
        modelMap.addAttribute("id", id);
        Employee employee = employeeService.getById(id);
        //System.out.println(id)
        modelMap.addAttribute("employee", employee);
        return "admin/employee/view";
    }

    @GetMapping("/update/{id}")
    public String updateEmployeePage(ModelMap modelMap, @Valid @NotNull @PathVariable("id") String id) throws Exception {
        modelMap.addAttribute("id", id);
        Employee employee = employeeService.getById(id);
        //System.out.println(id)
        modelMap.addAttribute("employee", employee);
        return "admin/employee/update";
    }


    @RequestMapping(value = "/update/{id}",
            method = RequestMethod.POST,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String update(ModelMap modelMap, @Valid EmployeeUpdateVO employeeUpdateVO, @PathVariable("id") String idEmployee,
                         @RequestParam MultiValueMap<String, String> formData) throws Exception {
        System.out.println(employeeUpdateVO);
        try {
            String fileName = StringUtils.cleanPath(employeeUpdateVO.getAvatar().getOriginalFilename());
            ClassLoader classLoader = getClass().getClassLoader();
            if (!fileName.equals("")) {
                String filePath = String.valueOf(System.currentTimeMillis()) + "." + fileName.split("\\.")[1];
                File file = new File(classLoader.getResource(".").getFile() + "static/admin/assets/file-upload/" + filePath);
                employeeUpdateVO.getAvatar().transferTo(file);
                employeeUpdateVO.setSrcEmployee(filePath);
            }
//            System.out.println(employeeUpdateVO.getAvatar());
            this.employeeService.update(idEmployee, employeeUpdateVO);
            return "redirect:/admin/employee/";
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return "Lỗi tè le";
        }
    }


    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String createNewEmployee(ModelMap modelMap, @Valid EmployeeVO employeeVO) throws Exception {
        try {
            String fileName = StringUtils.cleanPath(employeeVO.getAvatar().getOriginalFilename());
//            Path path = Paths.get("src/main/resources/static/admin/assets/file-upload");

            ClassLoader classLoader = getClass().getClassLoader();
            String filePath = String.valueOf(System.currentTimeMillis()) + "." + fileName.split("\\.")[1];
//            System.out.println(classLoader.getResource(".").getFile() + "static/admin/assets/file-upload/" + filePath);
            File file = new File(classLoader.getResource(".").getFile() + "static/admin/assets/file-upload/" + filePath);
//            File file = new File(path.resolve(filePath).toUri()); this line to save file in src/main/resource
            employeeVO.getAvatar().transferTo(file);

            employeeVO.setSrcEmployee(filePath);

            String employeeId = this.employeeService.save(employeeVO);
            System.out.println(employeeVO);
            return "redirect:/admin/employee/";
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return "Lỗi tè le";
        }
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
        return employeeService.getDTOById(id);
    }

    @GetMapping
    public Page<EmployeeDTO> query(@Valid EmployeeQueryVO vO) {
        return employeeService.query(vO);
    }
}