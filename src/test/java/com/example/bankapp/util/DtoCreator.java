package com.example.bankapp.util;

import com.example.bankapp.dto.*;
import com.example.bankapp.entity.Account;
import com.example.bankapp.entity.Client;
import com.example.bankapp.entity.Transaction;
import com.example.bankapp.entity.enums.AccountStatus;
import com.example.bankapp.entity.enums.AccountType;
import com.example.bankapp.entity.enums.CurrencyType;
import com.example.bankapp.entity.enums.TransactionType;
import com.example.bankapp.service.exception.ErrorCode;
import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@UtilityClass
public class DtoCreator {
    public static AccountDto getAccountDto() {
        return new AccountDto(3001L,
                "PL10 1050 0099 7603 1234 5678 9123",
                "CURRENT",
                "IN_USE",
                1000.0,
                "USD",
                "2021-03-02T00:00:00",
                "2023-06-04T00:00:00",
                "Cherkasov",
                6001L,
                Set.of(4001L),
                Set.of(4002L, 4003L, 4004L, 4005L));
    }
    public static ClientDto getClientDto() {
        return new ClientDto(2003L,
                "IN_USE",
                "XXX",
                "Andrey",
                "Pchelkin",
                "rojkov@gmail.com",
                "Brest, st. Masherova 27/99",
                "+375 (33) 777-44-22",
                "2021-01-15T00:00:00",
                "2023-08-01T00:00:00",
                "Ivanov",
                Set.of("LT60 1010 0123 4567 8901"));
    }
    public static List<ManagerDto> getListManagersDto() {
        return new ArrayList<>(List.of(new ManagerDto(
                "Petr",
                "Sorokin",
                "CLIENT_MANAGER",
                "2016-05-18T00:00:00",
                "2023-06-03T00:00:00",
                Set.of(new ClientAndAccountBalanceDto(
                        "Igor",
                        "Usmanov",
                        "BY86 AKBB 1010 0000 0029 6602 0000",
                        "10000.0"
                ))
        )));
    }
    public static ProductDto getIncomingProductDto() {
        return new ProductDto(
                "credit",
                "BLOCKED",
                "USD",
                "0.001",
                "5000",
                null,
                null
        );
    }
    public static ProductDto getOutputProductDto() {
        return new ProductDto(
                "credit",
                "BLOCKED",
                "USD",
                "0.001",
                "5000",
                "2019-10-01",
                LocalDate.now().toString()
        );
    }
    public static CreateTransactionDto getCreateTransactionDto() {
        return new CreateTransactionDto(
                "TRANSFER",
                280D,
                "280 usd from Sender_client_name to Recipient_client_name",
                "BY86 AKBB 1010 0000 0029 6602 0000",
                "LT60 1010 0123 4567 8901"
        );
    }
    public static TransactionDto getTransactionDto() {
        return new TransactionDto(
                "TRANSFER",
                280.0,
                "280 usd from Sender_client_name to Recipient_client_name",
                LocalDate.now().toString(),
                "BY86 AKBB 1010 0000 0029 6602 0000",
                "Igor",
                "Usmanov",
                "LT60 1010 0123 4567 8901",
                "Andrey",
                "Pchelkin"
        );
    }
    public static TransactionDto getTransactionDtoWithNull() {
        return new TransactionDto(
                null, null, "280 usd from Sender_client_name to Recipient_client_name",
                LocalDate.now().toString(), null, null,
                null, null, null, null
        );
    }
    public static CreateTransactionDto getDtoWithNegativeAmount() {
        return new CreateTransactionDto(
                "TRANSFER",
                -50D,
                "280 usd from Sender_client_name to Recipient_client_name",
                "BY86 AKBB 1010 0000 0029 6602 0000",
                "LT60 1010 0123 4567 8901"
        );
    }
    public static CreateTransactionDto getDtoWithWrongAccount() {
        return new CreateTransactionDto(
                "TRANSFER",
                50D,
                "280 usd from Sender_client_name to Recipient_client_name",
                "BY86 AKBB 1010 0000 0029 6602 0099",
                "LT60 1010 0123 4567 8901"
        );
    }
    public static CreateTransactionDto getDtoWithNotActiveAccount() {
        return new CreateTransactionDto(
                "TRANSFER",
                50D,
                "50 usd from Sender_client_name to Recipient_client_name",
                "LT60 1010 0123 4567 8911",
                "BY86 AKBB 1010 0000 0029 6602 0000"
        );
    }
    public static CreateTransactionDto getDtoWithInsufficientFoundAccount() {
        return new CreateTransactionDto(
                "TRANSFER",
                5000D,
                "280 usd from Sender_client_name to Recipient_client_name",
                "BY86 AKBB 1010 0000 0029 6602 0000",
                "LT60 1010 0123 4567 8901"
        );
    }
    public static Transaction getTransactionWithNull() {
        return new Transaction(
                null,
                null,
                null,
                "280 usd from Sender_client_name to Recipient_client_name",
                LocalDate.now(),
                new Account(
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        new Client(
                                null,
                                null,
                                null,
                                null,
                                null,
                                null,
                                null,
                                null,
                                null,
                                null,
                                null,
                                null,
                                null
                        ),
                        null,
                        null,
                        null
                ),
                new Account(
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        new Client(
                                null,
                                null,
                                null,
                                null,
                                null,
                                null,
                                null,
                                null,
                                null,
                                null,
                                null,
                                null,
                                null
                        ),
                        null,
                        null,
                        null
                )
        );
    }
    public static Transaction getTransactionWithClientsIsNull() {
        return new Transaction(
                null,
                null,
                null,
                "280 usd from Sender_client_name to Recipient_client_name",
                LocalDate.now(),
                new Account(
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null
                ),
                new Account(
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null
                )
        );
    }
    public static Transaction getTransactionWithAccountsIsNull() {
        return new Transaction(
                null,
                null,
                null,
                "280 usd from Sender_client_name to Recipient_client_name",
                LocalDate.now(),
                null,
                null
        );
    }
    public static ClientFirstNameLastNameDto getClientFirstNameLastNameDto() {
        return new ClientFirstNameLastNameDto("Nikolay", "Cherkasov");
    }
    public static Collection<AgreementDto> getAgreementDto() {
        return new ArrayList<>(List.of(new AgreementDto(
                "PL10 1050 0099 7603 1234 5678 9123", "IN_USE", "Nikolay Cherkasov",
                "currency account", "0.0", "IN_USE",
                "5000.0", "1000.0"
        )));
    }
    public static ErrorExtensionDto getClientNotFoundExceptionDto() {
        return new ErrorExtensionDto(HttpStatus.NOT_FOUND.toString(),
                "Client with ID: 200 not found");
    }
    public static ErrorExtensionDto getAccountNotFoundExceptionDto() {
        return new ErrorExtensionDto(HttpStatus.NOT_FOUND.toString(),
                "Account with Transaction amount: 888,00 and Client ID: 2009 not found");
    }
    public static ErrorExtensionDto getManagerNotFoundExceptionDto() {
        return new ErrorExtensionDto(HttpStatus.NOT_FOUND.toString(),
                "Manager with AccountBalance greater than or equal to: 20000,00 not found");
    }
    public static ErrorExtensionDto getProductNotFoundException() {
        return new ErrorExtensionDto(HttpStatus.NOT_FOUND.toString(),
                "Product with ID: 5009 not found");
    }
    public static ErrorExtensionDto getAnswerNegativeAmount() {
        return new ErrorExtensionDto(HttpStatus.BAD_REQUEST.toString(),
                "Invalid number entered. Validation failed amount: -50,00000 is incorrect");
    }
    public static ErrorExtensionDto getAnswerWrongAccountInTransaction() {
        return new ErrorExtensionDto(HttpStatus.NOT_FOUND.toString(),
                "Account with Name: BY86 AKBB 1010 0000 0029 6602 0099 not found");
    }
    public static ErrorExtensionDto getAnswerNotActiveAccountInTransaction() {
        return new ErrorExtensionDto(ErrorCode.PROBLEM_WITH_ACCOUNT,
                "Account with Name: LT60 1010 0123 4567 8911 is not active");
    }
    public static ErrorExtensionDto getAnswerInsufficientFoundTransaction() {
        return new ErrorExtensionDto(ErrorCode.PROBLEM_FUNDS,
                "There are not enough funds on the balance of account: LT60 1010 0123 4567 8901");
    }
    public static ErrorExtensionDto getAnswerAuthorizedClientNotHaveAccount() {
        return new ErrorExtensionDto(ErrorCode.PROBLEM_WITH_ACCOUNT,
                "Client with login: cherkasov@gmail.com does not have an account: LT60 1010 0123 4567 8901");
    }
    public static ErrorExtensionDto getAgreementNotFoundException() {
        return new ErrorExtensionDto(HttpStatus.NOT_FOUND.toString(),
                "Agreements with Client Name: Nikolay Cherkasov and Manager login: ivanov@employees.bankapp.com not found");
    }
}
