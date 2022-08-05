package com.java04.wibucian.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.SecurityBuilder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private MyUserDetailsService myUserDetailsService;

    public SecurityConfig(MyUserDetailsService myUserDetailsService) {
        this.myUserDetailsService = myUserDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin();
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/admin/").hasRole("ADMIN")
                .antMatchers("/staff/").hasRole("STAFF")
                .and()
                .logout()
                .logoutUrl("/logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID");
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    /**
     * Register CustomAccessDeniedHandler as a Java Bean
     *
     * @return CustomAccessDeniedHandler
     */
//    @Bean
//    public CustomAccessDeniedHandler accessDeniedHandler() {
//        return new CustomAccessDeniedHandler();
//    }
//
//    /**
//     * Register a temporary InMemoryDatabase for authentication
//     * in real project we would use DaoAuthenticationProvider
//     * @return
//     */
//    @Bean
//    public InMemoryUserDetailsManager userDetailsService() {
//        PasswordEncoder encoder =
//                PasswordEncoderFactories.createDelegatingPasswordEncoder();
//        UserDetails staffUser = User.builder()
//                                    .username("staff")
//                                    .password(encoder.encode("password"))
//                                    .roles("STAFF")
//                                    .build();
//        UserDetails adminUser = User.builder()
//                                    .username("admin")
//                                    .password(encoder.encode("password"))
//                                    .roles("ADMIN")
//                                    .build();
//        return new InMemoryUserDetailsManager(staffUser, adminUser);
//    }
//
//    /**
//     * Specify and register security chain
//     * @param http HttpSecurity builder
//     * @return SecurityFilterChain
//     * @throws Exception Exception
//     */
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws
//            Exception {
//        // disable csrf because we have no csrf token in form
//        http.csrf()
//            .disable();
//
//        http.authorizeRequests()
//            .antMatchers("/admin")
//            .hasRole("ADMIN")
//            .and()
//            .authorizeRequests()
//            .antMatchers("/staff")
//            .hasRole("STAFF")
//            .and()
//            .exceptionHandling()
//            .accessDeniedHandler(accessDeniedHandler());
//
//        http.formLogin();
//
//        return http.build();
//    }



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService);
    }
}
