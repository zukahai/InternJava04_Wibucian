package com.java04.wibucian.services;

import com.java04.wibucian.commons.*;
import com.java04.wibucian.dtos.ShiftRotateDTO;
import com.java04.wibucian.exception.BadRequestException;
import com.java04.wibucian.exception.ForbiddenException;
import com.java04.wibucian.exception.NotFoundException;
import com.java04.wibucian.models.Employee;
import com.java04.wibucian.models.Shift;
import com.java04.wibucian.models.ShiftRotate;
import com.java04.wibucian.repositories.EmployeeRepository;
import com.java04.wibucian.repositories.ShiftRepository;
import com.java04.wibucian.repositories.ShiftRotateRepository;
import com.java04.wibucian.vos.ShiftRotateUpdateVO;
import com.java04.wibucian.vos.ShiftRotateVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.Schedules;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ShiftRotateService {
    private final int PAGE_SIZE = 10;

    @Autowired
    private ShiftRotateRepository shiftRotateRepository;
    @Autowired
    private ShiftRepository shiftRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * Phương thức dùng để lấy tất cả các yêu cầu xoay ca cần được phê duyệt
     *
     * @return
     */
    public List<ShiftRotateDTO> getAllShiftRotatesToBeApproved() {
        return this.shiftRotateRepository.getAllByStatusOrderByCreateTimeDesc(
                           ShiftRotateStatus.ACCEPTED.getValue())
                                         .stream()
                                         .map(this::toDTO)
                                         .toList();
    }

    /**
     * Phương thức dùng để lấy tất cả các yêu cầu xoay ca được request của nhân viên
     *
     * @return
     */
    public List<ShiftRotateDTO> getAllShiftRotatesToBeAccepted(Employee employee) {
        List<ShiftRotateDTO> list =
                this.shiftRotateRepository.getAllByStatusAndEmployeeChangeOrderByCreateTimeDesc(
                            ShiftRotateStatus.CREATED.getValue(), employee)
                                          .stream()
                                          .map(this::toDTO)
                                          .toList();
        return this.shiftRotateRepository.getAllByStatusAndEmployeeChangeOrderByCreateTimeDesc(
                           ShiftRotateStatus.CREATED.getValue(), employee)
                                         .stream()
                                         .map(this::toDTO)
                                         .toList();
    }

    /**
     * Phương thức dùng để lấy tất cả các yêu cầu xoay ca của employee với status CREATED
     * hoặc ACCEPTED
     *
     * @return
     */
    public List<ShiftRotateDTO> getOwnShiftRotates(Employee employee) {
        return this.shiftRotateRepository.getPendingShiftRotatesOfEmployee(employee,
                                                                           ShiftRotateStatus.CREATED.getValue(),
                                                                           ShiftRotateStatus.ACCEPTED.getValue())
                                         .stream()
                                         .map(this::toDTO)
                                         .toList();
    }

    private ShiftRotateDTO toDTO(ShiftRotate shiftRotate) {
        ShiftRotateDTO shiftRotateDTO = new ShiftRotateDTO();
        BeanUtils.copyProperties(shiftRotate, shiftRotateDTO);
        Employee employeeCreate = shiftRotate.getEmployeeCreate();
        Employee employeeChange = shiftRotate.getEmployeeChange();
        Shift shift = shiftRotate.getShift();
        Shift shiftExchange = shiftRotate.getShiftExchange();

        shiftRotateDTO.setEmployeeId(employeeCreate.getId());
        shiftRotateDTO.setEmployeeName(employeeCreate.getName());

        shiftRotateDTO.setEmployeeChangeId(employeeChange.getId());
        shiftRotateDTO.setEmployeeChangeName(employeeChange.getName());

        shiftRotateDTO.setShiftId(shift.getId());
        shiftRotateDTO.setShiftDate(shift.getShiftDate());
        shiftRotateDTO.setShiftCode(shift.getShiftCode());

        shiftRotateDTO.setShiftExchangeId(shiftExchange.getId());
        shiftRotateDTO.setShiftExchangeDate(shiftExchange.getShiftDate());
        shiftRotateDTO.setShiftExchangeCode(shiftExchange.getShiftCode());

        return shiftRotateDTO;
    }

    private ShiftRotate requireOne(String shiftRotateId) {
        return shiftRotateRepository.findById(shiftRotateId)
                                    .orElseThrow(() -> new NotFoundException(
                                            "Không tìm thấy yêu cầu xoay ca"));
    }

    /**
     * Phương thức dùng để tạo yêu cầu xoay ca của nhân viên
     *
     * @param employee      Nhân viên tạo yêu cầu
     * @param shiftRotateVO
     *
     * @return
     */
    public boolean createShiftRotate(Employee employee, ShiftRotateVO shiftRotateVO) {
        // check ca làm có tồn tại không
        Shift shift = this.shiftRepository.findById(shiftRotateVO.getIdShift())
                                          .orElseThrow(() -> new NotFoundException(
                                                  "Ca làm việc không tồn tại"));
        Shift shiftExchange =
                this.shiftRepository.findById(shiftRotateVO.getIdShiftExchange())
                                    .orElseThrow(() -> new NotFoundException(
                                            "Ca làm việc không tồn tại"));
        // check nhân viên nhận xoay ca có tồn tại không
        Employee employeeChange = shiftExchange.getEmployee();

        // check ownership
        if (!shift.getEmployee()
                  .getId()
                  .equals(employee.getId())) {
            throw new ForbiddenException("Không thể tạo yêu cầu xoay ca");
        }
        // check xem có yêu cầu xoay ca với chính mình không =))
        if (employee.getId()
                    .equals(shiftExchange.getEmployee()
                                         .getId())) {
            throw new BadRequestException("Are you kidding me");
        }
        // nếu đã có yêu cầu xoay ca giống đang trong hàng đợi, throw
        this.shiftRotateRepository.findExistPendingShiftRotate(shift, shiftExchange,
                                                               ShiftRotateStatus.CREATED.getValue(),
                                                               ShiftRotateStatus.ACCEPTED.getValue())
                                  .ifPresent(shiftRotate -> {
                                      throw new BadRequestException(
                                              "Không thể tạo yêu cầu xoay ca");
                                  });
        this.shiftRotateRepository.findExistPendingShiftRotate(shiftExchange, shift,
                                                               ShiftRotateStatus.CREATED.getValue(),
                                                               ShiftRotateStatus.ACCEPTED.getValue())
                                  .ifPresent(shiftRotate -> {
                                      throw new BadRequestException(
                                              "Không thể tạo yêu cầu xoay ca");
                                  });

        // check xem có làm trùng buổi với buổi yêu cầu xoay ca không
        this.shiftRepository.findByEmployeeAndShiftDateAndShiftCodeAndRequestShift(
                    employee, shiftExchange.getShiftDate(), shiftExchange.getShiftCode(),
                    false)
                            .ifPresent((shiftExist -> {
                                throw new BadRequestException(
                                        "Buổi xoay ca trùng buổi làm");
                            }));
        this.shiftRepository.findByEmployeeAndShiftDateAndShiftCodeAndRequestShift(
                    employeeChange, shift.getShiftDate(), shift.getShiftCode(), false)
                            .ifPresent((shiftExist -> {
                                throw new BadRequestException(
                                        "Buổi xoay ca trùng buổi làm");
                            }));
        // check giờ tạo yêu cầu xoay ca và ca làm đã từng xoay ca chưa
        if (!canBeRotate(shift) || !canBeRotate(shiftExchange)) {
            throw new BadRequestException("Không thể tạo yêu cầu xoay ca");
        }
        // ok
        ShiftRotate shiftRotate = new ShiftRotate();
        shiftRotate.setEmployeeCreate(employee);
        shiftRotate.setEmployeeChange(employeeChange);
        shiftRotate.setShift(shift);
        shiftRotate.setShiftExchange(shiftExchange);
        shiftRotate.setStatus(ShiftRotateStatus.CREATED.getValue());
        shiftRotate.setCreateTime(new Date().toInstant());
        this.shiftRotateRepository.save(shiftRotate);
        return true;
    }

    /**
     * Hủy yêu cầu xoay ca
     *
     * @param employee      Nhân viên yêu cầu
     * @param shiftRotateId Mã yêu cầu xoay ca
     *
     * @return
     */
    public boolean cancelShiftRotateRequest(Employee employee, String shiftRotateId) {
        // kiểm tra exist
        ShiftRotate shiftRotate = this.shiftRotateRepository.findById(shiftRotateId)
                                                            .orElseThrow(
                                                                    () -> new NotFoundException(
                                                                            "Không tìm thấy yêu cầu xoay ca"));
        // kiểm tra ownership
        if (!shiftRotate.getEmployeeCreate()
                        .getId()
                        .equals(employee.getId())) {
            throw new ForbiddenException("Không thể thực hiện");
        }
        // chỉ cho phép cancel khi status vẫn là created, nghĩa là chưa được chấp nhận
        // bởi nhân viên nhận xoay ca
        if (shiftRotate.getStatus() != ShiftRotateStatus.CREATED.getValue()) {
            throw new BadRequestException("Không thể hủy yêu cầu xoay ca");
        }
        // ok
        shiftRotate.setStatus(ShiftRotateStatus.CANCELLED.getValue());
        this.shiftRotateRepository.save(shiftRotate);
        return true;
    }

    /**
     * Phương thức dùng để accept yêu cầu xoay ca của nhân viên
     *
     * @param employee            Nhân viên accept
     * @param shiftRotateUpdateVO
     *
     * @return
     */
    public boolean acceptShiftRotateRequest(Employee employee,
                                            ShiftRotateUpdateVO shiftRotateUpdateVO) {
        ShiftRotate shiftRotate = this.requireOne(shiftRotateUpdateVO.getShiftRotateId());
        // nếu employee không phải là employee nhận request, throw
        if (!employee.getId()
                     .equals(shiftRotate.getEmployeeChange()
                                        .getId())) {
            throw new ForbiddenException("Không thể thực hiện");
        }

        // nếu status của yêu cầu xoay ca khác CREATED, throw
        if (shiftRotate.getStatus() != ShiftRotateStatus.CREATED.getValue()) {
            throw new BadRequestException("Không thể thực hiện");
        }

        // change data và save
        if (shiftRotateUpdateVO.isApprove()) {
            shiftRotate.setStatus(ShiftRotateStatus.ACCEPTED.getValue());
            shiftRotate.setAcceptTime(new Date().toInstant());
        } else {
            shiftRotate.setStatus(ShiftRotateStatus.REJECTED.getValue());
            shiftRotate.setRejectTime(new Date().toInstant());
        }
        this.shiftRotateRepository.save(shiftRotate);
        return true;
    }

    /**
     * Phương thức dùng để approve yêu cầu xoay ca của admin
     *
     * @param shiftRotateUpdateVO
     *
     * @return
     */
    public boolean approveShiftRotateRequest(ShiftRotateUpdateVO shiftRotateUpdateVO) {
        ShiftRotate shiftRotate = this.requireOne(shiftRotateUpdateVO.getShiftRotateId());
        // status của shiftRotate phải là 1 (ACCEPTED), tương ứng với người nhận xoay
        // ca đã đồng ý
        if (shiftRotate.getStatus() != ShiftRotateStatus.ACCEPTED.getValue()) {
            throw new BadRequestException("Không thể thay đổi yêu cầu xoay ca");
        }
        // nếu là reject, chỉ cần set status và thời gian reject
        if (!shiftRotateUpdateVO.isApprove()) {
            shiftRotate.setStatus(ShiftRotateStatus.REJECTED.getValue());
            shiftRotate.setRejectTime(new Date().toInstant());
            this.shiftRotateRepository.save(shiftRotate);
            return true;
        }
        // nếu là approve: (quan trọng): mọi request xoay ca liên quan đến 2 ca làm có
        // status khác REJECTED đều phải set status thành REJECTED
        Shift shift = shiftRotate.getShift();
        Shift shiftExchange = shiftRotate.getShiftExchange();
        List<ShiftRotate> relateShiftRotate =
                this.shiftRotateRepository.getAllByShiftOrShiftExchangeAndStatusNot(shift,
                                                                                    shift,
                                                                                    ShiftRotateStatus.REJECTED.getValue());
        relateShiftRotate.addAll(
                this.shiftRotateRepository.getAllByShiftOrShiftExchangeAndStatusNot(
                        shiftExchange, shiftExchange,
                        ShiftRotateStatus.REJECTED.getValue()));
        relateShiftRotate.forEach(shiftRotate1 -> {
            shiftRotate1.setStatus(ShiftRotateStatus.REJECTED.getValue());
        });
        this.shiftRotateRepository.saveAll(relateShiftRotate);

        // thêm thông tin nhân viên xoay ca vào các record shift
        shift.setEmployeeChange(shiftRotate.getEmployeeChange());
        this.shiftRepository.save(shift);
        shiftExchange.setEmployeeChange(shiftRotate.getEmployeeCreate());
        this.shiftRepository.save(shiftExchange);

        // lưu thông tin xoay ca
        shiftRotate.setStatus(ShiftRotateStatus.APPROVED.getValue());
        shiftRotate.setApproveTime(new Date().toInstant());
        this.shiftRotateRepository.save(shiftRotate);
        return true;
    }

    /* các yêu cầu xoay ca phải được review trước lúc bắt đầu ca làm việc 3 tiếng, các
     * yêu cầu xoay ca quá thời hạn đó vẫn chưa được phê duyệt thì đều bị từ chối
     * sử dụng một cron job run trong các khoảng thời gian trước lúc bắt đầu các ca làm
     * việc 3 tiếng, query database để lấy các yêu cầu xoay ca ứng với ca làm đó mà có
     * status là CREATED hoặc ACCEPTED và set thành REJECTED
     */
    @Scheduled(cron = Constant.MORNING_SHIFT_ROTATE_CLOSE_CRON)
    public void scanForPendingShiftRotateOfMorningShift() {
        Calendar currentDate = Utils.currentDateWithoutTime();
        this.scanForPendingShiftRotate(currentDate.getTime(),
                                       ShiftOfDay.MORNING.getValue());
    }

    @Scheduled(cron = Constant.AFTERNOON_SHIFT_ROTATE_CLOSE_CRON)
    public void scanForPendingShiftRotateOfAfternoonShift() {
        Calendar currentDate = Utils.currentDateWithoutTime();
        this.scanForPendingShiftRotate(currentDate.getTime(),
                                       ShiftOfDay.AFTERNOON.getValue());
    }

    @Scheduled(cron = Constant.EVENING_SHIFT_ROTATE_CLOSE_CRON)
    public void scanForPendingShiftRotateOfEveningShift() {
        Calendar currentDate = Utils.currentDateWithoutTime();
        this.scanForPendingShiftRotate(currentDate.getTime(),
                                       ShiftOfDay.EVENING.getValue());
    }

    private void scanForPendingShiftRotate(Date shiftDate, int shiftCode) {
        List<Shift> shifts =
                this.shiftRepository.findAllByShiftDateAndShiftCodeOrderByRequestTimeDesc(
                        shiftDate, shiftCode);
        List<ShiftRotate> allPendingShiftRotates = new ArrayList<>();
        shifts.forEach(shift -> {
            List<ShiftRotate> shiftRotates =
                    this.shiftRotateRepository.getPendingShiftRotateByShift(shift,
                                                                            ShiftRotateStatus.CREATED.getValue(),
                                                                            ShiftRotateStatus.ACCEPTED.getValue());
            allPendingShiftRotates.addAll(shiftRotates);
        });
        allPendingShiftRotates.forEach(shiftRotate -> {
            shiftRotate.setStatus(ShiftRotateStatus.REJECTED.getValue());
            shiftRotate.setRejectTime(Utils.getCurrentDate()
                                           .toInstant());
        });
        this.shiftRotateRepository.saveAll(allPendingShiftRotates);
    }

    public void checkCanCreateShiftRotate(Employee employee, Shift shift) {
        // check ownership
        if (!shift.getEmployee()
                  .getId()
                  .equals(employee.getId())) {
            throw new ForbiddenException("Không phải chủ ca làm việc");
        }

        // check xem ca làm có thể xoay ca không
        if (!canBeRotate(shift)) {
            throw new BadRequestException("Không thể tạo yêu cầu xoay ca");
        }
    }

    private boolean canBeRotate(Shift shift) {
        // nếu là ca làm đăng ký, chưa phải là chính thức, throw
        if (shift.getRequestShift()) {
            return false;
        }
        // đã xoay ca thì không thể xoay ca nữa
        if (shift.getEmployeeChange() != null) {
            return false;
        }

        // check thời gian tạo yêu cầu
        Calendar current = Utils.getCurrentDate();
        String shiftStartFormat =
                Utils.getDateFormat(shift.getShiftDate(), Constant.DD_MM_YYYY_FORMAT)
                        + " " + ShiftOfDayConversion.getShiftOfDayFromValue(
                                                            shift.getShiftCode())
                                                    .getStartTime();
        Calendar shiftStart = Utils.getCalendarInstanceFromFormat(shiftStartFormat,
                                                                  Constant.DD_MM_YYYY_HH_MM_SS_FORMAT);

        return Utils.hoursBetween(current, shiftStart)
                >= Constant.MIN_HOURS_TO_CREATE_SHIFT_ROTATE_REQUEST;
    }

    public Map<DayOfWeek, Map<ShiftOfDay, List<Shift>>> getCandidateShiftToRotate(
            Employee employee, Shift shift, Calendar weekStartDay, Calendar weekEndDay) {
        List<String> shiftIds =
                this.shiftRotateRepository.getCandidateShiftsToRotate(employee.getId(),
                                                                      shift.getShiftDate(),
                                                                      shift.getShiftCode(),
                                                                      shift.getId(),
                                                                      weekStartDay.getTime(),
                                                                      weekEndDay.getTime());
        List<Shift> shiftList = this.shiftRepository.findAllById(shiftIds);
        return convertShiftList(shiftList.stream()
                                         .filter(this::canBeRotate)
                                         .toList());
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
}
