package com.java04.wibucian.controllers.admin;

import com.java04.wibucian.dtos.ShiftRotateDTO;
import com.java04.wibucian.exception.NotFoundException;
import com.java04.wibucian.models.Employee;
import com.java04.wibucian.models.ShiftRotate;
import com.java04.wibucian.services.EmployeeService;
import com.java04.wibucian.services.ShiftRotateService;
import com.java04.wibucian.vos.ShiftRotateQueryVO;
import com.java04.wibucian.vos.ShiftRotateUpdateVO;
import com.java04.wibucian.vos.ShiftRotateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

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
     * @param page  page thứ
     * @param model
     *
     * @return
     */
    @GetMapping("/")
    public String getAllShiftRotateToBeApproved(
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            Model model) {
        Page<ShiftRotate> shiftRotatePage =
                this.shiftRotateService.getAllShiftRotatesToBeApproved(page - 1);
        if (!shiftRotatePage.hasContent()) {
            throw new NotFoundException("Không tìm thấy trang");
        }
        model.addAttribute("shiftRotatesToBeApproved", shiftRotatePage.getContent());
        model.addAttribute("totalPages", shiftRotatePage.getTotalPages());
        model.addAttribute("totalRecords", shiftRotatePage.getTotalElements());
        model.addAttribute("currentPage", page);
        return "admin/shiftRotate/index";
    }

    @PostMapping(value = "/create",
                 consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<Boolean> createShiftRotate(@Valid ShiftRotateVO shiftRotateVO) {
        Employee employee = this.employeeService.getById(shiftRotateVO.getIdEmployee());
        return ResponseEntity.ok()
                             .body(this.shiftRotateService.createShiftRotate(employee,
                                                                             shiftRotateVO));
    }

    @PostMapping(value = "/accept/{shiftRotateId}",
                 consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<Boolean> acceptShiftRotate(
            @Valid ShiftRotateUpdateVO shiftRotateUpdateVO) {
        Employee employee =
                this.employeeService.getById(shiftRotateUpdateVO.getIdEmployee());
        return ResponseEntity.ok()
                             .body(this.shiftRotateService.acceptShiftRotateRequest(
                                     employee, shiftRotateUpdateVO));
    }

    @PostMapping(value = "/cancel/{shiftRotateId}",
                 consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<Boolean> cancelShiftRotate(String idEmployee,
                                                     @PathVariable(
                                                             "shiftRotateId") String shiftRotateId) {
        Employee employee = this.employeeService.getById(idEmployee);
        return ResponseEntity.ok()
                             .body(this.shiftRotateService.cancelShiftRotateRequest(
                                     employee, shiftRotateId));
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
