package com.java04.wibucian.services;

import com.java04.wibucian.commons.Constant;
import com.java04.wibucian.dtos.ShiftDTO;
import com.java04.wibucian.models.Employee;
import com.java04.wibucian.models.Shift;
import com.java04.wibucian.repositories.EmployeeRepository;
import com.java04.wibucian.repositories.ShiftRepository;
import com.java04.wibucian.vos.ShiftQueryVO;
import com.java04.wibucian.vos.ShiftUpdateVO;
import com.java04.wibucian.vos.ShiftVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ShiftService {

    private final int SHIFT_REQUEST_START_DAY = Constant.SHIFT_REQUEST_START_DAY;
    private final int SHIFT_REQUEST_END_DAY = Constant.SHIFT_REQUEST_END_DAY;
    @Autowired
    private ShiftRepository shiftRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public String createShift(ShiftVO shiftVO) {
        // query employee
        Employee employee = this.employeeRepository.findById(shiftVO.getIdEmployee())
                                                   .orElseThrow(
                                                           NoSuchElementException::new);

        Calendar currentDate = new GregorianCalendar();
        currentDate.setTime(new Date());

        // check xem có đang trong thời gian đăng ký ca làm việc
        if (!this.isRegisterShiftTime(currentDate)) {
            throw new RuntimeException("Không trong thời gian đăng ký ca làm việc");
        }

        // check xem ngày của ca đăng ký có hợp lệ không
        Calendar requestDate = new GregorianCalendar();
        requestDate.setTime(shiftVO.getShiftDate());
        if (!this.isValidRegisterShiftTime(currentDate, requestDate)) {
            throw new RuntimeException("Ngày của ca đăng ký không hợp lệ");
        }

        // nếu đã đăng ký trước đó thì không cho phép đăng ký nữa
        Shift requestedShift =
                this.shiftRepository.findByEmployeeAndShiftDateAndShiftCode(employee,
                                                                            shiftVO.getShiftDate(),
                                                                            shiftVO.getShiftCode());
        if (requestedShift != null) {
            throw new RuntimeException("Đã đăng ký ca làm việc trước đó");
        }

        // check số ca đăng ký của nhân viên, nếu vượt quá số lượng tối đa thì chỉ cho
        // phép đăng ký dự bị, ngược lại thì có thể đăng ký bình thường
        boolean canRequestNormally =
                this.canEmployeeRequestNormally(employee, currentDate);

        // check xem ca làm đó đã có đủ người đăng ký chưa
        // nếu có ít hơn 2 ca đăng ký, thì chỉ việc tạo ca đăng ký mới
        List<Shift> shifts =
                this.shiftRepository.findAllByShiftDateAndShiftCodeOrderByRequestTimeDesc(
                        shiftVO.getShiftDate(), shiftVO.getShiftCode());
        Shift shift = null;
        if (shifts.size() < 2) {
            shift = new Shift();
            BeanUtils.copyProperties(shiftVO, shift);
            shift.setRequestShift(true);
            shift.setOvertimeRequest(!canRequestNormally);
        } else {
            // nếu đã có 2 ca đăng ký, check xem các ca đăng ký đó có phải dự bị không,
            // nếu phải thì trám vào các vị trí dự bị đó, ngược lại thì trả về kết
            // quả đăng ký không thành công
            if (!canRequestNormally) {
                throw new RuntimeException("Không thể đăng ký dự bị");
            }
            for (Shift existShift : shifts) {
                if (existShift.getOvertimeRequest()) {
                    shift = existShift;
                    shift.setOvertimeRequest(false);
                    break;
                }
                throw new RuntimeException("Ca làm việc đã đầy");
            }
        }
        shift.setEmployee(employee);
        shift.setRequestTime(currentDate.toInstant());
        shift = shiftRepository.save(shift);
        return shift.getId();
    }

    public void delete(String id) {
        shiftRepository.deleteById(id);
    }

    public void update(String id, ShiftUpdateVO vO) {
        Shift bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        shiftRepository.save(bean);
    }

    public ShiftDTO getById(String id) {
        Shift original = requireOne(id);
        return toDTO(original);
    }

    public Page<ShiftDTO> query(ShiftQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private ShiftDTO toDTO(Shift original) {
        ShiftDTO bean = new ShiftDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private Shift requireOne(String id) {
        return shiftRepository.findById(id)
                              .orElseThrow(() -> new NoSuchElementException(
                                      "Resource not found: " + id));
    }

    /**
     * Phương thức dùng để query tất cả các record đăng ký ca làm việc trong tuần tiếp
     * theo
     *
     * @return
     */
    //    public String getAllShiftRequestsForNextWeak() {
    //        Calendar currentDate = new GregorianCalendar();
    //        currentDate.setTime(new Date());
    //        int dayOfWeek = currentDate.get(Calendar.DAY_OF_WEEK);
    //        if (dayOfWeek < this.SHIFT_REQUEST_START_DAY
    //                || dayOfWeek > this.SHIFT_REQUEST_END_DAY) {
    //            throw new
    //        }
    //    }
    private boolean isRegisterShiftTime(Calendar currentDate) {
        int dayOfWeek = currentDate.get(Calendar.DAY_OF_WEEK);
        return dayOfWeek >= this.SHIFT_REQUEST_START_DAY
                && dayOfWeek <= this.SHIFT_REQUEST_END_DAY;
    }

    private boolean isValidRegisterShiftTime(Calendar currentDate, Calendar requestDate) {
        Calendar currentDateStartTime =
                new GregorianCalendar(currentDate.get(Calendar.YEAR),
                                      currentDate.get(Calendar.MONTH),
                                      currentDate.get(Calendar.DATE));
        currentDateStartTime.set(Calendar.YEAR, currentDate.get(Calendar.YEAR));
        currentDateStartTime.set(Calendar.MONTH, currentDate.get(Calendar.MONTH));
        currentDateStartTime.set(Calendar.DATE, currentDate.get(Calendar.DATE));
        return requestDate.compareTo(this.getFirstDayOfNextWeek(currentDateStartTime))
                >= 0
                && requestDate.compareTo(this.getLastDayOfNextWeek(currentDateStartTime))
                < 1;
    }

    private Calendar getFirstDayOfNextWeek(Calendar currentDay) {
        Calendar firstDayOfNextWeek = new GregorianCalendar();
        firstDayOfNextWeek.set(Calendar.YEAR, currentDay.get(Calendar.YEAR));
        firstDayOfNextWeek.set(Calendar.MONTH, currentDay.get(Calendar.MONTH));
        firstDayOfNextWeek.set(Calendar.DATE, currentDay.get(Calendar.DATE));
        firstDayOfNextWeek.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        firstDayOfNextWeek.add(Calendar.DATE, Calendar.DAY_OF_WEEK);
        return firstDayOfNextWeek;
    }

    private Calendar getLastDayOfNextWeek(Calendar currentDay) {
        Calendar lastDayOfNextWeek = this.getFirstDayOfNextWeek(currentDay);
        lastDayOfNextWeek.add(Calendar.DATE, -1 + Calendar.DAY_OF_WEEK);
        return lastDayOfNextWeek;
    }

    private boolean canEmployeeRequestNormally(Employee employee, Calendar currentDay) {
        int employeeNumOfRequestOfNextWeek =
                this.shiftRepository.countByEmployeeAndShiftDateBetween(employee,
                                                                        this.getFirstDayOfNextWeek(
                                                                                    currentDay)
                                                                            .getTime(),
                                                                        this.getLastDayOfNextWeek(
                                                                                    currentDay)
                                                                            .getTime());
        System.out.println(employeeNumOfRequestOfNextWeek);
        return employeeNumOfRequestOfNextWeek < 3;
    }
}
