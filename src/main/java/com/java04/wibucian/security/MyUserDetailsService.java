package com.java04.wibucian.security;

import com.java04.wibucian.models.Account;
import com.java04.wibucian.repositories.AccountRepository;
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

@Service
public class MyUserDetailsService implements UserDetailsService {

    private AccountRepository repository;

    public MyUserDetailsService(AccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

//        if(!username.equals("admin")) throw new UsernameNotFoundException("User not found");
//        GrantedAuthority role = new SimpleGrantedAuthority("ROLE_ADMIN");
//        return new User("admin", "1234", List.of(role));

        Optional<Account> accountOptional = repository.findAccountById(username);
        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
            GrantedAuthority role = new SimpleGrantedAuthority(account.getRole() == 1 ? "ROLE_ADMIN" : "ROLE_STAFF");
            return new User(account.getId(), account.getPassword(), List.of(role));
        }
        throw new UsernameNotFoundException("User not found");
    }
}
