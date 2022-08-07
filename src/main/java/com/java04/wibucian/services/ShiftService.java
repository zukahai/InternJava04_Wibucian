package com.java04.wibucian.services;

import com.java04.wibucian.commons.*;
import com.java04.wibucian.dtos.ShiftDTO;
import com.java04.wibucian.exception.AccessDeniedException;
import com.java04.wibucian.exception.BadRequestException;
import com.java04.wibucian.exception.NotFoundException;
import com.java04.wibucian.models.Employee;
import com.java04.wibucian.models.Shift;
import com.java04.wibucian.models.ShiftApprove;
import com.java04.wibucian.repositories.EmployeeRepository;
import com.java04.wibucian.repositories.ShiftApproveRepository;
import com.java04.wibucian.repositories.ShiftRepository;
import com.java04.wibucian.vos.AdminShiftVO;
import com.java04.wibucian.vos.ShiftQueryVO;
import com.java04.wibucian.vos.ShiftUpdateVO;
import com.java04.wibucian.vos.ShiftVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ShiftService {

    private final int SHIFT_REQUEST_START_DAY = Constant.SHIFT_REQUEST_START_DAY;
    private final int SHIFT_REQUEST_END_DAY = Constant.SHIFT_REQUEST_END_DAY;
    private final int SHIFT_APPROVE_DAY = Constant.SHIFT_APPROVE_DAY;
    @Autowired
    private ShiftRepository shiftRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ShiftApproveRepository shiftApproveRepository;

    /**
     * Phương thức dùng để tạo ca làm việc mới
     *
     * @param shiftVO Đối tượng ShiftVO chứa các thông tin cần thiết để tạo ca làm việc
     *
     * @return
     */
    public String createShift(ShiftVO shiftVO, String employeeId) {
        // query employee
        Employee employee = this.employeeRepository.findById(employeeId)
                                                   .orElse(null);

        Calendar currentDate = Utils.getCurrentDate();
        Calendar firstDayOfNextWeek = Utils.getFirstDayOfNextWeek();
        Calendar lastDayOfNextWeek = Utils.getLastDayOfNextWeek();
        Calendar requestDate = Calendar.getInstance();
        requestDate.setTime(shiftVO.getShiftDate());
        boolean adminCreateShift = shiftVO instanceof AdminShiftVO;
        this.checkValidRequestShiftData(employee, requestDate, firstDayOfNextWeek,
                                        lastDayOfNextWeek, shiftVO, adminCreateShift);
        if (adminCreateShift) {
            return adminCreateShift(employee, currentDate, firstDayOfNextWeek,
                                    lastDayOfNextWeek, shiftVO);
        }
        return staffCreateShift(employee, currentDate, firstDayOfNextWeek,
                                lastDayOfNextWeek, shiftVO);
    }

    /**
     * Phương thức tạo ca làm việc của Admin
     *
     * @param employee           Nhân viên
     * @param currentDate        Ngày hiện tại
     * @param firstDayOfNextWeek Ngày thứ 2 của tuần tiếp theo
     * @param lastDayOfNextWeek  Ngày Chủ nhật của tuần tiếp theo
     * @param shiftVO            ShiftVO
     *
     * @return Mã ca làm việc vừa tạo
     */
    private String adminCreateShift(Employee employee, Calendar currentDate,
                                    Calendar firstDayOfNextWeek,
                                    Calendar lastDayOfNextWeek, ShiftVO shiftVO) {
        Shift shift = null;
        // check xem ca làm đó đã có đủ người đăng ký chưa
        // nếu có nhiều hơn 2 ca đăng ký thì throw
        List<Shift> shifts =
                this.shiftRepository.findAllByShiftDateAndShiftCodeOrderByRequestTimeDesc(
                        shiftVO.getShiftDate(), shiftVO.getShiftCode());
        if (shifts.size() >= 2) {
            throw new BadRequestException("Ca làm việc đã đầy");
        }
        shift = new Shift();
        BeanUtils.copyProperties(shiftVO, shift);
        shift.setRequestShift(true);
        shift.setEmployee(employee);
        shift.setRequestTime(currentDate.toInstant());
        shift = shiftRepository.save(shift);
        return shift.getId();
    }

    /**
     * Phương thức tạo ca làm việc của Nhân viên
     *
     * @param employee           Nhân viên
     * @param currentDate        Ngày hiện tại
     * @param firstDayOfNextWeek Ngày thứ 2 của tuần tiếp theo
     * @param lastDayOfNextWeek  Ngày chủ nhật của tuần tiếp theo
     * @param shiftVO            ShiftVO
     *
     * @return Mã ca làm việc vừa tạo
     */
    private String staffCreateShift(Employee employee, Calendar currentDate,
                                    Calendar firstDayOfNextWeek,
                                    Calendar lastDayOfNextWeek, ShiftVO shiftVO) {
        Shift shift = null;
        // check số ca đăng ký chính thức của nhân viên, nếu vượt quá số lượng tối đa
        // thì chỉ cho phép đăng ký dự bị, ngược lại thì có thể đăng ký bình thường
        boolean canRequestNormally =
                this.canEmployeeRequestNormally(employee, firstDayOfNextWeek,
                                                lastDayOfNextWeek);

        // check xem ca làm đó đã có đủ người đăng ký chưa
        // nếu có ít hơn 2 ca đăng ký, thì chỉ việc tạo ca đăng ký mới
        List<Shift> shifts =
                this.shiftRepository.findAllByShiftDateAndShiftCodeOrderByRequestTimeDesc(
                        shiftVO.getShiftDate(), shiftVO.getShiftCode());
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
                throw new BadRequestException("Không thể đăng ký dự bị");
            }
            for (Shift existShift : shifts) {
                if (existShift.isOvertimeRequest()) {
                    shift = existShift;
                    shift.setOvertimeRequest(false);
                    break;
                }
                throw new BadRequestException("Ca làm việc đã đủ slot");
            }
        }
        shift.setEmployee(employee);
        shift.setRequestTime(currentDate.toInstant());
        shift = shiftRepository.save(shift);
        return shift.getId();
    }

    /**
     * Xóa đăng ký ca làm việc
     *
     * @param shiftId  Mã ca làm việc đã đăng ký
     * @param employee Nhân viên thực hiện xóa, null nếu là Admin
     */
    public boolean delete(String shiftId, Employee employee) {
        Shift shift = requireOne(shiftId);

        if (!shift.getRequestShift()) {
            throw new AccessDeniedException("Không thể thực hiện tác vụ này");
        }

        if (employee != null && !employee.getId()
                                         .equals(shift.getEmployee()
                                                      .getId())) {
            throw new AccessDeniedException("Không thể thực hiện tác vụ này");
        }
        shiftRepository.delete(shift);
        return true;
    }

    /**
     * Phương thức dùng để cập nhật ca làm việc (đổi nhân viên nhận ca) của Admin
     *
     * @param shiftId       Mã ca làm việc
     * @param shiftUpdateVO ShiftUpdateVO
     *
     * @return Kết quả thực hiện
     */
    public boolean update(String shiftId, ShiftUpdateVO shiftUpdateVO) {
        Shift shift = requireOne(shiftId);
        String employeeId = shiftUpdateVO.getIdEmployee();
        Employee employee = this.employeeRepository.findById(employeeId)
                                                   .orElseThrow(
                                                           () -> new NotFoundException(
                                                                   "Nhân viên "
                                                                           + employeeId
                                                                           + " không "
                                                                           + "tồn tại"));
        this.shiftRepository.findByEmployeeAndShiftDateAndShiftCode(employee,
                                                                    shift.getShiftDate(),
                                                                    shift.getShiftCode())
                            .ifPresent(shift1 -> {
                                throw new BadRequestException(
                                        "Nhân viên đã đăng ký ca trong cùng buổi");
                            });

        shift.setEmployee(employee);
        shift.setRequestTime(new Date().toInstant());
        shiftRepository.save(shift);
        return true;
    }

    /**
     * Phương thức dùng để xác nhận các đăng ký ca làm việc cho tuần tiếp theo
     *
     * @return Kết quả thực hiện
     */
    public boolean approveShiftRequest() {
        if (!this.isInShiftApproveTime()) {
            throw new AccessDeniedException("Không thể thực hiện tác vụ");
        }
        if (this.isAlreadyApprovedForNextWeek()) {
            throw new BadRequestException("Chốt lịch làm việc không thành công");
        }
        Calendar firstDayOfNextWeek = Utils.getFirstDayOfNextWeek();
        Calendar lastDayOfNextWeek = Utils.getLastDayOfNextWeek();
        List<Shift> allShiftRequestForNextWeek =
                this.shiftRepository.findAllByRequestShiftAndShiftDateBetween(true,
                                                                              firstDayOfNextWeek.getTime(),
                                                                              lastDayOfNextWeek.getTime());
        allShiftRequestForNextWeek.forEach(shift -> shift.setRequestShift(false));
        this.shiftRepository.saveAll(allShiftRequestForNextWeek);
        ShiftApprove shiftApprove = new ShiftApprove();
        shiftApprove.setApproveTime(new Date().toInstant());
        shiftApprove.setApproveFor(firstDayOfNextWeek.getTime());
        shiftApproveRepository.save(shiftApprove);
        return true;
    }

    public boolean isAlreadyApprovedForNextWeek() {
        Calendar firstDayOfNextWeek = Utils.getFirstDayOfNextWeek();
        return this.shiftApproveRepository.findByApproveFor(firstDayOfNextWeek.getTime())
                                          .isPresent();
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

    private Shift requireOne(String shiftId) {
        return shiftRepository.findById(shiftId)
                              .orElseThrow(() -> new NotFoundException(
                                      "Ca làm việc " + shiftId + " không tồn tại"));
    }


    /**
     * Phương thức dùng để query tất cả các record đăng ký ca làm việc trong tuần tiếp
     * theo
     *
     * @return
     */
    public Map<DayOfWeek, Map<ShiftOfDay, List<Shift>>> getAllShiftRequestsForNextWeak() {
        Map<DayOfWeek, Map<ShiftOfDay, List<Shift>>> result = new HashMap<>();
        Calendar currentDate = new GregorianCalendar();
        currentDate.setTime(new Date());
        List<Shift> allShifts =
                this.shiftRepository.findAllByRequestShiftAndShiftDateBetween(true,
                                                                              Utils.getFirstDayOfNextWeek()
                                                                                   .getTime(),
                                                                              Utils.getLastDayOfNextWeek()
                                                                                   .getTime());

        return this.convertShiftList(allShifts);
    }

    public boolean isInShiftRequestTime() {
        Calendar currentDate = Utils.getCurrentDate();
        int dayOfWeek = currentDate.get(Calendar.DAY_OF_WEEK);
        return dayOfWeek >= this.SHIFT_REQUEST_START_DAY
                && dayOfWeek <= this.SHIFT_REQUEST_END_DAY;
    }

    public boolean isInShiftApproveTime() {
        Calendar currentDate = Utils.getCurrentDate();
        int dayOfWeek = currentDate.get(Calendar.DAY_OF_WEEK);
        return dayOfWeek == this.SHIFT_APPROVE_DAY;
    }


    private boolean isValidRegisterShiftTime(Calendar requestDate,
                                             Calendar firstDayOfNextWeek,
                                             Calendar lastDayOfNextWeek) {
        return requestDate.compareTo(firstDayOfNextWeek) >= 0
                && requestDate.compareTo(lastDayOfNextWeek) < 1;
    }

    private boolean canEmployeeRequestNormally(Employee employee,
                                               Calendar firstDayOfNextWeek,
                                               Calendar lastDayOfNextWeek) {
        int employeeNumOfRequestOfNextWeek =
                this.shiftRepository.countByEmployeeAndOvertimeRequestAndShiftDateBetween(
                        employee, false, firstDayOfNextWeek.getTime(),
                        lastDayOfNextWeek.getTime());
        return employeeNumOfRequestOfNextWeek < Constant.MAX_SHIFT_REQUEST_PER_WEEK;
    }

    private Map<DayOfWeek, Map<ShiftOfDay, List<Shift>>> convertShiftList(
            List<Shift> shiftList) {
        Map<DayOfWeek, Map<ShiftOfDay, List<Shift>>> result = new HashMap<>();
        Calendar utilCalendar = Calendar.getInstance();
        for (Shift shift : shiftList) {
            utilCalendar.setTime(shift.getShiftDate());
            DayOfWeek dayOfWeek = DayOfWeekConversion.getDayOfWeekFromValue(
                    utilCalendar.get(Calendar.DAY_OF_WEEK));
            ShiftOfDay shiftOfDay =
                    ShiftOfDayConversion.getShiftOfDayFromValue(shift.getShiftCode());

            // check xem dữ liệu của ngày làm việc có tồn tại không
            Map<ShiftOfDay, List<Shift>> shiftOfDayToShiftListMap = result.get(dayOfWeek);
            if (shiftOfDayToShiftListMap == null) {
                shiftOfDayToShiftListMap = new HashMap<>();
            }

            // check xem dữ liệu của buổi làm việc có tồn tại không
            List<Shift> shifts = shiftOfDayToShiftListMap.get(shiftOfDay);
            if (shifts == null) {
                shifts = new ArrayList<>();
            }

            // add dữ liệu
            shifts.add(shift);
            shiftOfDayToShiftListMap.put(shiftOfDay, shifts);
            result.put(dayOfWeek, shiftOfDayToShiftListMap);
        }
        return result;
    }

    private void checkValidRequestShiftData(Employee employee, Calendar requestDate,
                                            Calendar firstDayOfNextWeek,
                                            Calendar lastDayOfNextWeek, ShiftVO shiftVO,
                                            boolean adminCreateShift) {
        // check xem có nhân viên không
        if (employee == null) {
            throw new NotFoundException("Nhân viên không tồn tại");
        }

        // check xem có đang trong thời gian đăng ký ca làm việc
        if (adminCreateShift) {
            if (!this.isInShiftApproveTime()) {
                throw new BadRequestException("Không trong thời gian tạo ca làm việc");
            }
        } else {
            if (!this.isInShiftRequestTime()) {
                throw new BadRequestException(
                        "Không trong thời gian đăng ký ca làm việc");
            }
        }

        // check xem ngày của ca đăng ký có hợp lệ không
        if (!this.isValidRegisterShiftTime(requestDate, firstDayOfNextWeek,
                                           lastDayOfNextWeek)) {
            throw new BadRequestException("Ngày của ca đăng ký không hợp lệ");
        }

        // nếu đã đăng ký trước đó thì không cho phép đăng ký nữa
        this.shiftRepository.findByEmployeeAndShiftDateAndShiftCode(employee,
                                                                    shiftVO.getShiftDate(),
                                                                    shiftVO.getShiftCode())
                            .ifPresent((shift) -> {
                                throw new BadRequestException("Đã đăng ký ca làm việc");
                            });

    }

}
