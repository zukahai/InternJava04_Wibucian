package com.java04.wibucian.services;

import com.java04.wibucian.dtos.EmployeeDTO;
import com.java04.wibucian.models.Employee;
import com.java04.wibucian.repositories.EmployeeRepository;
import com.java04.wibucian.vos.EmployeeQueryVO;
import com.java04.wibucian.vos.EmployeeUpdateVO;
import com.java04.wibucian.vos.EmployeeVO;
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

    //    public List<Employee> findAll() {
    //        return this.employeeRepository.findAll();
    //    }

    public void delete(String id) {
        employeeRepository.deleteById(id);
    }

    // update bằng ID, sẽ được admin sử dụng để update nhân viên
    public void update(String employeeId, EmployeeUpdateVO employeeUpdateVO) {
        Employee employee = requireOne(employeeId);
        BeanUtils.copyProperties(employeeUpdateVO, employee);
        employeeRepository.save(employee);
    }

    // update bằng employee object, sẽ được nhân viên sử dụng để update thông tin cá nhân
    public void update(Employee employee, EmployeeUpdateVO employeeUpdateVO) {
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
