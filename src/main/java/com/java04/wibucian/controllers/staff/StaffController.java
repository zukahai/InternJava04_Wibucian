//package com.java04.wibucian.controllers.staff;
//
//import com.java04.wibucian.controllers.admin.EmployeeController;
//import com.java04.wibucian.services.EmployeeService;
//import com.java04.wibucian.vos.EmployeeVO;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//import javax.servlet.http.HttpServletResponse;
//import javax.validation.Valid;
//import java.io.IOException;
//
//@RestController
//@RequestMapping("/staff")
//public class StaffController {
//
//    private EmployeeService employeeService;
//
//    public StaffController(EmployeeService employeeService) {
//        this.employeeService = employeeService;
//    }
//
//    @RequestMapping(method = RequestMethod.GET)
//    @ResponseBody
//    public ResponseEntity<String> getStaffMainPage() {
//        return ResponseEntity.ok().body("staff main");
//    }
//
//    @PostMapping(
//            value = "/update",
//            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
//            produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
//    )
//    public void updateEmployee(HttpServletResponse response, EmployeeVO employeeVO) throws IOException {
//        employeeService.save(employeeVO);
//        response.sendRedirect("/staff");
//    }
//}