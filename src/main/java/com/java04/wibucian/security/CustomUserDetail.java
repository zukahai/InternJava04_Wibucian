package com.java04.wibucian.security;

import com.java04.wibucian.models.Employee;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class CustomUserDetail extends User {
    private Employee employee;

    public CustomUserDetail(String username, String password,
                            Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
