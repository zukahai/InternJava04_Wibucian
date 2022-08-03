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

import java.util.NoSuchElementException;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public String save(EmployeeVO vO) {
        Employee bean = new Employee();
        BeanUtils.copyProperties(vO, bean);
        bean = employeeRepository.save(bean);
        return bean.getIdEmployee();
    }

    public void delete(String id) {
        employeeRepository.deleteById(id);
    }

    public void update(String id, EmployeeUpdateVO vO) {
        Employee bean = requireOne(id);
        BeanUtils.copyProperties(vO, bean);
        employeeRepository.save(bean);
    }

    public EmployeeDTO getById(String id) {
        Employee original = requireOne(id);
        return toDTO(original);
    }

    public Page<EmployeeDTO> query(EmployeeQueryVO vO) {
        throw new UnsupportedOperationException();
    }

    private EmployeeDTO toDTO(Employee original) {
        EmployeeDTO bean = new EmployeeDTO();
        BeanUtils.copyProperties(original, bean);
        return bean;
    }

    private Employee requireOne(String id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resource not found: " + id));
    }
}
