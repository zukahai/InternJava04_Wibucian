package com.java04.wibucian.services;

import com.java04.wibucian.dtos.EmployeeDTO;
import com.java04.wibucian.models.Employee;
import com.java04.wibucian.repositories.EmployeeRepository;
import com.java04.wibucian.vos.EmployeeQueryVO;
import com.java04.wibucian.vos.EmployeeUpdateVO;
import com.java04.wibucian.vos.EmployeeVO;
import com.java04.wibucian.vos.StaffEmployeeUpdateVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public String save(EmployeeVO vO) {
        Employee bean = new Employee();
        BeanUtils.copyProperties(vO, bean);
        if (vO.getIdEmployee() != null) {
            bean.setId(vO.getIdEmployee());
        }
        bean = employeeRepository.save(bean);
        return bean.getId();
    }

    public void delete(String id) {
        employeeRepository.deleteById(id);
    }

    public void update(String id, EmployeeUpdateVO vO) {
        Employee bean = requireOne(id);
        String oldSrc = bean.getSrcEmployee();
        BeanUtils.copyProperties(vO, bean);
        if (vO.getSrcEmployee().equals("")) {
            bean.setSrcEmployee(oldSrc);
        }
        employeeRepository.save(bean);
    }

    // update bằng employee object, sẽ được nhân viên sử dụng để update thông tin cá nhân
    public void update(Employee employee, StaffEmployeeUpdateVO employeeUpdateVO) {
        BeanUtils.copyProperties(employeeUpdateVO, employee);
        employee.setBirthDay(employeeUpdateVO.getBirthday());
        employeeRepository.save(employee);
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public EmployeeDTO getDTOById(String id) {
        Employee original = requireOne(id);
        return toDTO(original);
    }

    public Employee getById(String id) {
        return requireOne(id);
    }

    public Page<EmployeeDTO> query(EmployeeQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    public EmployeeDTO toDTO(Employee original) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        BeanUtils.copyProperties(original, employeeDTO);
        return employeeDTO;
    }

    private Employee requireOne(String id) {
        return employeeRepository.findById(id)
                                 .orElseThrow(() -> new NoSuchElementException(
                                         "Resource not found: " + id));
    }
}
