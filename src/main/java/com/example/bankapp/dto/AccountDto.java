package com.example.bankapp.dto;

import com.example.bankapp.entity.Agreement;
import com.example.bankapp.entity.Client;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDateTime;

@Component
@NoArgsConstructor
@Getter
@Setter
public class AccountDto {

    private Long id;

    private String name;

    private String type;

    private String status;

    private Double balance;

    private String currencyCode;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime createdAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime updatedAt;

    private String clientLastName;

    private Long agreementId;
}
