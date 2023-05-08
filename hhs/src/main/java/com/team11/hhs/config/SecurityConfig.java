package com.team11.hhs.config;

import com.team11.hhs.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.Collection;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private UserDetailsService userDetailsService;

    @Autowired
    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests()
                .requestMatchers("/bed/**").hasRole("ADMIN")
                .requestMatchers("/bedPatients/**").hasRole("DOCTOR")
                .requestMatchers("/reset/**").permitAll()
                .requestMatchers("/register/**").permitAll() // everyone can /register
                .requestMatchers("/showBills/**").permitAll() // everyone can pay a bill
                .requestMatchers("/index").permitAll() // everyone can view /index
                .requestMatchers("/users/**").hasRole("ADMIN") // only go to /users if user is an admin
                .requestMatchers("/doctors/**").hasRole("DOCTOR") // only go to /doctorHome if user is a doctor
                .requestMatchers("/patients/**").hasRole("PATIENT") // only users with PATIENT role can access /patients/**
                .and()
                .formLogin() // on successful login go to users
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/index")
                .successHandler((request, response, authentication) -> {
                    Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
                    for (GrantedAuthority authority : authorities) {
                        if (authority.getAuthority().equals("ROLE_ADMIN")) {
                            response.sendRedirect("/users"); //"/admin/dashboard"
                        } else if (authority.getAuthority().equals("ROLE_DOCTOR")) {
                            response.sendRedirect("/doctorHome"); //"/doctor/dashboard"
                        } else if (authority.getAuthority().equals("ROLE_PATIENT")) {
                            response.sendRedirect("/index"); //"/patient/dashboard"
                        }
                    }
                })
                .permitAll()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .permitAll();
        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }
//
}

