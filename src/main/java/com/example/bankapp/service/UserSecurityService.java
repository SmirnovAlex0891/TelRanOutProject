package com.example.bankapp.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserSecurityService extends UserDetailsService {
    @Override
    UserDetails loadUserByUsername(String s);
}
