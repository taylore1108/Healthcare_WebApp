package com.team11.hhs.config;

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
                .authorizeHttpRequests((authorize) ->
                        authorize.requestMatchers("/register/**").permitAll() // everyone can /register
                                .requestMatchers("/index").permitAll() // everyone can view /index
                                .requestMatchers("/users/**").hasRole("ADMIN") // only go to /users if user is an admin
                                .requestMatchers("/doctors/**").hasRole("DOCTOR") // only go to /users if user is an admin
                                .requestMatchers("/patients/**").hasRole("PATIENT") // only users with PATIENT role can access /patients/**
                ).formLogin( // on successful login go to users
                        form -> form
                                .loginPage("/login")
                                .loginProcessingUrl("/login")
                                .defaultSuccessUrl("/index")
                                .successHandler((request, response, authentication) -> {
                                    //for (GrantedAuthority auth : authentication.getAuthorities()) {
                                        if (request.isUserInRole("ADMIN")) { //"ADMIN".equals(auth.getAuthority())||
                                            response.sendRedirect("/users");
                                        } else if (request.isUserInRole("DOCTOR")) {
                                            response.sendRedirect("/hello");
                                        } else if (request.isUserInRole("PATIENT")){//.equals(auth.getAuthority())) {
                                            response.sendRedirect("/index");
                                        } else {
                                            response.sendRedirect("/index"); // for all other roles, redirect to the root URL
                                        }
                                    //}
                                })
                                .permitAll()
                ).logout(
                        logout -> logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .permitAll()
                );
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
