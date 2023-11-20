package com.example.bankapp.service.impl;

import com.example.bankapp.entity.Client;
import com.example.bankapp.entity.Manager;
import com.example.bankapp.entity.enums.ClientStatus;
import com.example.bankapp.repository.ClientRepository;
import com.example.bankapp.repository.ManagerRepository;
import com.example.bankapp.service.UserSecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class UserSecurityServiceImpl implements UserSecurityService {
    private final ClientRepository clientRepository;
    private final ManagerRepository managerRepository;
    @Override
    public UserDetails loadUserByUsername(String email) {
        Optional<Client> optClient = clientRepository.findClientByEmail(email);
        Optional<Manager> optManager = managerRepository.findManagerByEmail(email);

        if (optClient.isPresent() && optClient.get().getStatus().equals(ClientStatus.IN_USE)) {
            Client client = optClient.get();
            return new User(client.getEmail(), client.getPassword(),
                    new ArrayList<>(List.of(new SimpleGrantedAuthority("ROLE_CLIENT"))));
        }
        if (optManager.isPresent()) {
            Manager manager = optManager.get();
            return new User(manager.getEmail(), manager.getPassword(),
                    new ArrayList<>(List.of(new SimpleGrantedAuthority("ROLE_" + manager.getStatus().getValue()))));
        }
        throw new UsernameNotFoundException(String.format("Client or Manager whit email: %s not found", email));
    }
}
