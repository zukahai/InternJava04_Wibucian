package com.java04.wibucian.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;

public class AuthProvider implements AuthenticationProvider {

    private MyUserDetailsService myUserDetailsService;
    private AuthenticationManager authenticationManager;

    public AuthProvider(MyUserDetailsService myUserDetailsService, AuthenticationManager authenticationManager) {
        this.myUserDetailsService = myUserDetailsService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userName = authentication.getName();
        String password = String.valueOf(authentication.getCredentials());
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName,password));
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }
}
