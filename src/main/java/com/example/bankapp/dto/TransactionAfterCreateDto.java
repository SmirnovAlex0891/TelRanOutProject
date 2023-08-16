package com.example.bankapp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
@NoArgsConstructor
@Getter
@Setter
public class TransactionAfterCreateDto {
    private String id;
    private String type;
    private Double amount;
    private String description;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate createdAt;

    private String debitAccountName;
    private String debitClientFirstName;
    private String debitClientLastName;
    private String creditAccountName;
    private String creditAccountFirstName;
    private String creditAccountLastName;


}
