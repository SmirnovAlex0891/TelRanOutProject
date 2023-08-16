package com.example.bankapp.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@Getter
@Setter
public class ClientCreateDto {
    private String taxCode;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String phone;
    private String managerLastName;
}
