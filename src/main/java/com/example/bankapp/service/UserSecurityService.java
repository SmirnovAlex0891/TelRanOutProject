package com.example.bankapp.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserSecurityService extends UserDetailsService {
    @Override
    UserDetails loadUserByUsername(String s);
}
