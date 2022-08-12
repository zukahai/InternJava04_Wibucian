package com.java04.wibucian.interceptor;

import com.java04.wibucian.models.Employee;
import com.java04.wibucian.models.RollCall;
import com.java04.wibucian.models.Shift;
import com.java04.wibucian.security.CustomUserDetail;
import com.java04.wibucian.services.RollCallService;
import com.java04.wibucian.services.ShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component("rollCallInterceptor")
public class RollCallInterceptor implements HandlerInterceptor {
    @Autowired
    private ShiftService shiftService;

    @Autowired
    private RollCallService rollCallService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws
            Exception {
        Employee employee = ((CustomUserDetail) SecurityContextHolder.getContext()
                                                                     .getAuthentication()
                                                                     .getPrincipal()).getEmployee();
        Shift shift = this.shiftService.getCurrentShiftOfEmployee(employee)
                                       .orElse(null);
        if (shift == null) {
            // không có ca làm trong lúc này
            request.setAttribute("haveShift", false);
            return true;
        }
        // có ca làm, check xem đã điểm danh chưa
        RollCall rollCall = this.rollCallService.getByShift(shift).orElse(null);
        if (rollCall == null) {
            this.rollCallService.createRollCall(shift);
        }
        request.setAttribute("haveShift", true);
        return true;
    }
}
