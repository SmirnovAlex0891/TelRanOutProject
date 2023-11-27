package com.example.bankapp.controller;

import com.example.bankapp.dto.AgreementDto;
import com.example.bankapp.dto.ClientFirstNameLastNameDto;
import com.example.bankapp.dto.ErrorExtensionDto;
import com.example.bankapp.util.DtoCreator;
import com.fasterxml.jackson.core.type.TypeReference;
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

import java.util.Collection;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;

@SpringBootTest
@AutoConfigureMockMvc
@Sql("/schema.sql")
@Sql("/insert_test_data.sql")
public class AgreementControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldGetCollectionAgreementDto() throws Exception {
        //given
        ClientFirstNameLastNameDto incomingNamedDto = DtoCreator.getClientFirstNameLastNameDto();
        Collection<AgreementDto> expectedAgreementDto = DtoCreator.getAgreementDto();
        //when
        MvcResult agreementControllerGetResult = mockMvc.perform(
                        MockMvcRequestBuilders.post("/agreement/getAgreements")
                                .with(httpBasic("sorokin@employees.bankapp.com", "100"))
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(incomingNamedDto)))
                .andReturn();
        String agreementGetResultStringJson = agreementControllerGetResult.getResponse().getContentAsString();
        Collection<AgreementDto> resultAgreementDto = objectMapper.readValue(agreementGetResultStringJson, new TypeReference<Collection<AgreementDto>>() {
        });
        //then
        Assertions.assertEquals(200, agreementControllerGetResult.getResponse().getStatus());
        Assertions.assertEquals(expectedAgreementDto, resultAgreementDto);
    }
    @Test
    public void shouldGetAgreementNotFoundException() throws Exception {
        //given
        ClientFirstNameLastNameDto incomingNamedDto = DtoCreator.getClientFirstNameLastNameDto();
        ErrorExtensionDto expectedResult = DtoCreator.getAgreementNotFoundException();
        //when
        MvcResult agreementControllerGetResult = mockMvc.perform(
                        MockMvcRequestBuilders.post("/agreement/getAgreements")
                                .with(httpBasic("ivanov@employees.bankapp.com", "100"))
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(incomingNamedDto)))
                .andReturn();
        String agreementControllerResultString = agreementControllerGetResult.getResponse().getContentAsString();
        ErrorExtensionDto actualResult = objectMapper.readValue(agreementControllerResultString, ErrorExtensionDto.class);
        //then
        Assertions.assertEquals(404, agreementControllerGetResult.getResponse().getStatus());
        Assertions.assertEquals(expectedResult, actualResult);
    }
}
