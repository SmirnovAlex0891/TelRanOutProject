package com.example.bankapp.mapper;

import com.example.bankapp.dto.AccountDto;
import com.example.bankapp.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;
import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    @Mapping(source = "agreement.id", target = "agreementId")
    @Mapping(source = "client.lastName", target = "clientLastName")
    AccountDto accountToAccountDto(Account account);

    List<AccountDto> toAccountDtoList (List<Account> accountList);
}
