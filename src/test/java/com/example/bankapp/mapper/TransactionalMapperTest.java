package com.example.bankapp.mapper;

import com.example.bankapp.dto.TransactionDto;
import com.example.bankapp.entity.Transaction;
import com.example.bankapp.repository.TransactionRepository;
import com.example.bankapp.service.TransactionService;
import com.example.bankapp.util.DtoCreator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@SpringBootTest
public class TransactionalMapperTest {
    @Autowired
    private TransactionMapper transactionMapper;
    @Test
    public void shouldGetTransactionDto() {
        //given
        Transaction transaction = DtoCreator.getTransactionWithNull();
        //when
        TransactionDto actualResult = transactionMapper.transactionToDto(transaction);
        TransactionDto resultWithClientsIsNull = transactionMapper.transactionToDto(DtoCreator.getTransactionWithClientsIsNull());
        TransactionDto resultWithAccountsIsNull = transactionMapper.transactionToDto(DtoCreator.getTransactionWithAccountsIsNull());
        //then
        Assertions.assertEquals(actualResult, DtoCreator.getTransactionDtoWithNull());
        Assertions.assertEquals(resultWithClientsIsNull, DtoCreator.getTransactionDtoWithNull());
        Assertions.assertEquals(resultWithAccountsIsNull, DtoCreator.getTransactionDtoWithNull());
    }
    @Test
    public void shouldTestTransactionMapperForNull() {
        Assertions.assertNull(transactionMapper.transactionToDto(null));
        Assertions.assertNull(transactionMapper.toTransaction(null));
    }

}
