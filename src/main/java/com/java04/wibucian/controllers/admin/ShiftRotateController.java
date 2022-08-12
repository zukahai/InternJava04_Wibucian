package com.java04.wibucian.controllers.admin;

import com.java04.wibucian.models.Employee;
import com.java04.wibucian.models.ShiftRotate;
import com.java04.wibucian.services.EmployeeService;
import com.java04.wibucian.services.ShiftRotateService;
import com.java04.wibucian.vos.ShiftRotateQueryVO;
import com.java04.wibucian.vos.ShiftRotateUpdateVO;
import com.java04.wibucian.vos.ShiftRotateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Validated
@Controller
@RequestMapping("/admin/shiftRotate")
public class ShiftRotateController {

    @Autowired
    private ShiftRotateService shiftRotateService;

    @Autowired
    private EmployeeService employeeService;


    /**
     * Request mapping với endpoint /admin/shiftRotate/, lấy tất cả các yêu cầu xoay ca
     * đang chờ được phê duyệt
     *
     * @param model
     *
     * @return
     */
    @GetMapping()
    public String getApproveShiftRotatePage(Model model) {
        return "admin/shiftRotate/index";
    }

    @GetMapping(value = "/pending")
    @ResponseBody
    public ResponseEntity<Map<?, ?>> getShiftRotatesToBeApproved() {
        return ResponseEntity.ok()
                             .body(Map.of("data",
                                          this.shiftRotateService.getAllShiftRotatesToBeApproved()));
    }

    @PostMapping(value = "/create",
                 consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<Boolean> createShiftRotate(@Valid ShiftRotateVO shiftRotateVO) {
        Employee employee = this.employeeService.getById(shiftRotateVO.getIdEmployee());
        return ResponseEntity.ok()
                             .body(this.shiftRotateService.createShiftRotate(employee,
                                                                             shiftRotateVO));
    }


    /**
     * Request mapping với endpoint /admin/shiftRotate/{shiftRotateId}, method PATCH, dùng
     * để approve hoặc reject một yêu cầu xoay ca
     *
     * @param shiftRotateId       Mã yêu cầu xoay ca
     * @param shiftRotateUpdateVO
     *
     * @return
     */
    @PatchMapping(value = "/{shiftRotateId}",
                  consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<Boolean> changeShiftRotateStatus(
            @PathVariable("shiftRotateId") String shiftRotateId,
            @Valid ShiftRotateUpdateVO shiftRotateUpdateVO) {
        shiftRotateUpdateVO.setShiftRotateId(shiftRotateId);
        return ResponseEntity.ok()
                             .body(this.shiftRotateService.approveShiftRotateRequest(
                                     shiftRotateUpdateVO));
    }
}
