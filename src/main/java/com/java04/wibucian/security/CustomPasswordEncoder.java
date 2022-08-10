package com.java04.wibucian.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomPasswordEncoder extends BCryptPasswordEncoder {
}
