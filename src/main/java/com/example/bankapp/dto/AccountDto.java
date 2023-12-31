package com.example.bankapp.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Value;

import java.util.Set;

@Value
public class AccountDto {
    @ApiModelProperty(name = "id", value = "Database generate Account(id)", dataType = "Long", example = "3001", position = 1)
    Long id;
    @ApiModelProperty(value = "Account name (unique)", dataType = "String", example = "PL10 1050 0099 7603 1234 5678 9123", position = 2)
    String name;
    @ApiModelProperty(value = "Account type", dataType = "String", example = "CURRENT", position = 3)
    String type;
    @ApiModelProperty(value = "Account status", dataType = "String", example = "IN_USE", position = 4)
    String status;
    @ApiModelProperty(value = "Account balance", dataType = "Double", example = "1000.0", position = 5)
    Double balance;
    @ApiModelProperty(value = "Account currency type", dataType = "String", example = "USD", position = 6)
    String currencyType;
    @ApiModelProperty(value = "Date of creat account", dataType = "String", example = "2021-03-02", position = 7)
    String createdAt;
    @ApiModelProperty(value = "Date of last account update", dataType = "String", example = "2023-06-04", position = 8)
    String updatedAt;
    @ApiModelProperty(value = "Account holder's last name", dataType = "String", example = "Cherkasov", position = 9)
    String clientLastName;
    @ApiModelProperty(value = "Id of agreement", dataType = "Long", example = "6001", position = 10)
    Long agreementId;
    @ApiModelProperty(value = "List Id's sent transfers", dataType = "Set<long>", example = "[\n4001\n]", position = 11)
    Set<Long> creditTransactions;
    @ApiModelProperty(value = "List Id's received transfers", dataType = "Set<long>", example = "[\n4002,\n4003,\n4004,\n4005\n]", position = 12)
    Set<Long> debitTransactions;
}
