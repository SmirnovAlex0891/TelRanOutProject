package com.example.bankapp.dto;

import lombok.Value;

import java.util.List;
@Value
public class ErrorResponseDto {
    String errorCode;
    List<ErrorExtensionDto> errorExtensions;
}
