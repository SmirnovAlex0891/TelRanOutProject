package com.example.bankapp.config;

import com.example.bankapp.service.UserSecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserSecurityService userSecurityService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/transaction/**").hasRole("CLIENT")
                .antMatchers("/client/**", "/account/**", "/agreement/**").hasAnyRole("CLIENT_MANAGER", "ADMIN_BANK_APP")
                .antMatchers("/product/**").hasAnyRole("PRODUCT_MANAGER", "ADMIN_BANK_APP")
                .antMatchers("/manager/**").hasAnyRole("CLIENT_MANAGER", "PRODUCT_MANAGER", "ADMIN_BANK_APP")
                .antMatchers("/api/swagger-ui/**").permitAll()
                .and()
                .formLogin()
                .and()
                .logout().logoutSuccessUrl("/swagger-ui/#/")
                .and()
                .csrf().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(userSecurityService);
        return authenticationProvider;
    }
}
