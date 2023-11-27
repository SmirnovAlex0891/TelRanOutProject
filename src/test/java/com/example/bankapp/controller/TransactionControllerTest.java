package com.example.bankapp.controller;

import com.example.bankapp.dto.ErrorExtensionDto;
import com.example.bankapp.dto.TransactionDto;
import com.example.bankapp.util.DtoCreator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;

@SpringBootTest
@AutoConfigureMockMvc
@Sql("/schema.sql")
@Sql("/insert_test_data.sql")
public class TransactionControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldGetTransactionDto() throws Exception {
        //given
        String incomingTransactionDto = objectMapper.writeValueAsString(DtoCreator.getCreateTransactionDto());
        TransactionDto expectedTransactionDto = DtoCreator.getTransactionDto();
        //when
        MvcResult transactionControllerGetResult = mockMvc.perform(
                MockMvcRequestBuilders.post("/transaction/new")
                        .with(httpBasic("rojkov@gmail.com", "100"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(incomingTransactionDto))
                .andReturn();
        TransactionDto actualTransactionDto = objectMapper.readValue(
                transactionControllerGetResult.getResponse().getContentAsString(), TransactionDto.class);
        //then
        Assertions.assertEquals(201, transactionControllerGetResult.getResponse().getStatus());
        Assertions.assertEquals(expectedTransactionDto, actualTransactionDto);
    }
    @Test
    public void shouldGetMethodNotValidException() throws Exception {
        //given
        String incomingWrongTransactionDto = objectMapper.writeValueAsString(DtoCreator.getDtoWithNegativeAmount());
        ErrorExtensionDto expectedResult = DtoCreator.getAnswerNegativeAmount();
        //when
        MvcResult transactionControllerGetResult = mockMvc.perform(
                MockMvcRequestBuilders.post("/transaction/new")
                        .with(httpBasic("rojkov@gmail.com", "100"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(incomingWrongTransactionDto))
                .andReturn();
        ErrorExtensionDto actualResult = objectMapper.readValue(
                transactionControllerGetResult.getResponse().getContentAsString(), ErrorExtensionDto.class);
        //then
        Assertions.assertEquals(400, transactionControllerGetResult.getResponse().getStatus());
        Assertions.assertEquals(expectedResult, actualResult);
    }
    @Test
    public void shouldGetExceptionWrongAccount() throws Exception {
        //given
        String incomingWrongTransactionDto = objectMapper.writeValueAsString(DtoCreator.getDtoWithWrongAccount());
        ErrorExtensionDto expectedResult = DtoCreator.getAnswerWrongAccountInTransaction();
        //when
        MvcResult transactionControllerGetResult = mockMvc.perform(
                MockMvcRequestBuilders.post("/transaction/new")
                        .with(httpBasic("rojkov@gmail.com", "100"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(incomingWrongTransactionDto))
                .andReturn();
        ErrorExtensionDto actualResult = objectMapper.readValue(
                transactionControllerGetResult.getResponse().getContentAsString(), ErrorExtensionDto.class);
        //then
        Assertions.assertEquals(404, transactionControllerGetResult.getResponse().getStatus());
        Assertions.assertEquals(expectedResult, actualResult);
    }
    @Test
    public void shouldGetExceptionNotActiveAccount() throws Exception {
        //given
        String incomingWrongTransactionDto = objectMapper.writeValueAsString(DtoCreator.getDtoWithNotActiveAccount());
        ErrorExtensionDto expectedResult = DtoCreator.getAnswerNotActiveAccountInTransaction();
        //when
        MvcResult transactionControllerGetResult = mockMvc.perform(
                MockMvcRequestBuilders.post("/transaction/new")
                        .with(httpBasic("rojkov@gmail.com", "100"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(incomingWrongTransactionDto))
                .andReturn();
        ErrorExtensionDto actualResult = objectMapper.readValue(
                transactionControllerGetResult.getResponse().getContentAsString(), ErrorExtensionDto.class);
        //then
        Assertions.assertEquals(400, transactionControllerGetResult.getResponse().getStatus());
        Assertions.assertEquals(expectedResult, actualResult);
    }
    @Test
    public void shouldGetExceptionInsufficientFundsAccount() throws Exception {
        //given
        String incomingWrongTransactionDto = objectMapper.writeValueAsString(DtoCreator.getDtoWithInsufficientFoundAccount());
        ErrorExtensionDto expectedResult = DtoCreator.getAnswerInsufficientFoundTransaction();
        //when
        MvcResult transactionControllerGetResult = mockMvc.perform(
                MockMvcRequestBuilders.post("/transaction/new")
                        .with(httpBasic("rojkov@gmail.com", "100"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(incomingWrongTransactionDto))
                .andReturn();
        ErrorExtensionDto actualResult = objectMapper.readValue(
                transactionControllerGetResult.getResponse().getContentAsString(), ErrorExtensionDto.class);
        //then
        Assertions.assertEquals(400, transactionControllerGetResult.getResponse().getStatus());
        Assertions.assertEquals(expectedResult, actualResult);
    }
    @Test
    public void shouldGetExceptionClientNotHaveAccount() throws Exception {
        //given
        String incomingTransactionDto = objectMapper.writeValueAsString(DtoCreator.getCreateTransactionDto());
        ErrorExtensionDto expectedResult = DtoCreator.getAnswerAuthorizedClientNotHaveAccount();
        //when
        MvcResult transactionControllerGetResult = mockMvc.perform(
                MockMvcRequestBuilders.post("/transaction/new")
                        .with(httpBasic("cherkasov@gmail.com", "100"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(incomingTransactionDto))
                .andReturn();
        ErrorExtensionDto actualResult = objectMapper.readValue(
                transactionControllerGetResult.getResponse().getContentAsString(), ErrorExtensionDto.class);
        //then
        Assertions.assertEquals(400, transactionControllerGetResult.getResponse().getStatus());
        Assertions.assertEquals(expectedResult, actualResult);
    }
    @Test
    public void shouldGetUsernameNotFoundException() throws Exception {
        //given
        String incomingTransactionDto = objectMapper.writeValueAsString(DtoCreator.getCreateTransactionDto());
        //when
        MvcResult transactionControllerGetResult = mockMvc.perform(
                        MockMvcRequestBuilders.post("/transaction/new")
                                .with(httpBasic("user@gmail.com", "100"))
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(incomingTransactionDto))
                .andReturn();
        //then
        Assertions.assertEquals(401, transactionControllerGetResult.getResponse().getStatus());
    }
}
