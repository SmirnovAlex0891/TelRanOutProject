package com.example.bankapp.dto;

import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ManagerDto {
    private String firstName;
    private String LastName;
    private String status;
    private LocalDateTime updatedAt;

}
