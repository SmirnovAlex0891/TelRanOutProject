package com.example.bankapp.controller;

import com.example.bankapp.dto.AccountDto;
import com.example.bankapp.dto.ErrorExtensionDto;
import com.example.bankapp.util.DtoCreator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;

@SpringBootTest
@AutoConfigureMockMvc
@Sql("/schema.sql")
@Sql("/insert_test_data.sql")
public class AccountControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldGetAccount() throws Exception {
        //given
        AccountDto expectedResult = DtoCreator.getAccountDto();
        String transactionAmount = "150";
        String clientId = "2001";
        //when
        MvcResult accountControllerGetResult = mockMvc.perform(
                MockMvcRequestBuilders.get("/account/" + transactionAmount + "/" + clientId)
                        .with(httpBasic("admin@bankapp.com", "100")))
                .andReturn();
        String accountGetResultStringJson = accountControllerGetResult.getResponse().getContentAsString();
        AccountDto actualResult = objectMapper.readValue(accountGetResultStringJson, AccountDto.class);
        //then
        Assertions.assertEquals(200, accountControllerGetResult.getResponse().getStatus());
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void shouldGetAccountNotFoundException() throws Exception {
        //given
        String wrongTransactionAmount = "888";
        String wrongClientId = "2009";
        ErrorExtensionDto expectedExceptionDto = DtoCreator.getAccountNotFoundExceptionDto();
        //when
        MvcResult accountControllerGetResult = mockMvc.perform(
                MockMvcRequestBuilders.get("/account/" + wrongTransactionAmount + "/" + wrongClientId)
                        .with(httpBasic("admin@bankapp.com", "100")))
                .andReturn();
        String accountGetResultStringJson = accountControllerGetResult.getResponse().getContentAsString();
        ErrorExtensionDto extensionDto = objectMapper.readValue(accountGetResultStringJson, ErrorExtensionDto.class);
        //then
        Assertions.assertEquals(404, accountControllerGetResult.getResponse().getStatus());
        Assertions.assertEquals(expectedExceptionDto, extensionDto);
    }


}
