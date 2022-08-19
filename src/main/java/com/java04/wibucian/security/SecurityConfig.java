package com.java04.wibucian.security;

import com.java04.wibucian.commons.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.SecurityBuilder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("myUserDetailService")
    private UserDetailsService userDetailsService;
    @Autowired
    @Qualifier("accessAnonymousPathHandler")
    private AccessAnonymousPathHandler accessAnonymousPathHandler;
    @Autowired
    private CustomPasswordEncoder customPasswordEncoder;
    @Autowired
    @Qualifier("customAccessDeniedHandler")
    private CustomAccessDeniedHandler customAccessDeniedHandler;

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(this.userDetailsService);
        authProvider.setPasswordEncoder(this.customPasswordEncoder);
        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws
            Exception {
        auth.authenticationProvider(this.authProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws
            Exception {
        http.formLogin()
            .defaultSuccessUrl("/")
            .loginPage("/login")
            .failureUrl("/login?error=true");

        http.csrf()
            .disable()
            .authorizeRequests()
            .antMatchers("/admin", "/admin/**")
            .hasAuthority(Role.ADMIN.toString())
            .antMatchers("/staff", "/staff/**")
            .hasAuthority(Role.STAFF.toString())
            .and()
            .exceptionHandling()
            .accessDeniedHandler(customAccessDeniedHandler)
            .and()
            .logout()
            .logoutUrl("/logout")
            .invalidateHttpSession(true)
            .deleteCookies("JSESSIONID")
            .logoutSuccessUrl("/");

        // restrict access to anonymous path to only anonymous
        http.authorizeRequests()
            .antMatchers("/login")
            .anonymous()
            .and()
            .exceptionHandling()
            .accessDeniedHandler(accessAnonymousPathHandler);

        http.authorizeRequests()
            .anyRequest()
            .permitAll();
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
           .antMatchers("/admin/assets/**");
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


}
