package com.java04.wibucian.security;

import com.java04.wibucian.commons.Role;
import com.java04.wibucian.models.Account;
import com.java04.wibucian.models.Employee;
import com.java04.wibucian.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("myUserDetailService")
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws
            UsernameNotFoundException {
        Account account = this.accountRepository.findById(username)
                                                .orElseThrow(
                                                        () -> new UsernameNotFoundException(
                                                                "User not found"));
        Employee employee = account.getEmployee();
        GrantedAuthority role = new SimpleGrantedAuthority(
                account.getRole() == Role.ADMIN.getValue()
                        ? Role.ADMIN.toString()
                        : Role.STAFF.toString());
        CustomUserDetail user =
                new CustomUserDetail(account.getId(), account.getPassword(),
                                     List.of(role));
        user.setEmployee(employee);
        return user;
    }
}
