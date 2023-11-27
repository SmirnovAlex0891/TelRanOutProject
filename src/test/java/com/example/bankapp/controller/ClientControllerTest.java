package com.example.bankapp.controller;

import com.example.bankapp.dto.ClientDto;
import com.example.bankapp.dto.ErrorExtensionDto;
import com.example.bankapp.repository.ClientRepository;
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
public class ClientControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ClientRepository clientRepository;

    @Test
    public void shouldGetClientById() throws Exception {
        //given
        String clientId = "2003";
        ClientDto expectedResult = DtoCreator.getClientDto();
        //when
        MvcResult clientControllerGetResult = mockMvc.perform(
                MockMvcRequestBuilders.get("/client/" + clientId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(httpBasic("admin@bankapp.com", "100")))
                .andReturn();
        String clientControllerGetResultJson = clientControllerGetResult.getResponse().getContentAsString();
        ClientDto actualResult = objectMapper.readValue(clientControllerGetResultJson, ClientDto.class);
        //then
        Assertions.assertEquals(200, clientControllerGetResult.getResponse().getStatus());
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void shouldGetClientNotFoundException() throws Exception {
        //given
        String wrongClientId = "200";
        ErrorExtensionDto expectedExceptionDto = DtoCreator.getClientNotFoundExceptionDto();
        //when
        MvcResult clientControllerGetResult = mockMvc.perform(
                MockMvcRequestBuilders.get("/client/" + wrongClientId)
                        .with(httpBasic("admin@bankapp.com", "100")))
                .andReturn();
        String clientControllerGetResultJson = clientControllerGetResult.getResponse().getContentAsString();
        ErrorExtensionDto extensionDto = objectMapper.readValue(clientControllerGetResultJson, ErrorExtensionDto.class);
        //then
        Assertions.assertEquals(404, clientControllerGetResult.getResponse().getStatus());
        Assertions.assertEquals(expectedExceptionDto, extensionDto);
    }
    @Test
    public void shouldDeleteClientById() throws Exception {
        //given
        String clientId = "2004";
        Long clientIdLong = 2004L;
        //when
        MvcResult clientControllerGetResult = mockMvc.perform(
                MockMvcRequestBuilders.delete("/client/delete/" + clientId)
                        .with(httpBasic("admin@bankapp.com", "100")))
                .andReturn();
        //then
        Assertions.assertFalse(clientRepository.findById(clientIdLong).isPresent());
        Assertions.assertEquals(200, clientControllerGetResult.getResponse().getStatus());
        Assertions.assertEquals("", clientControllerGetResult.getResponse().getContentAsString());
    }
    @Test
    public void shouldGetExceptionWhenDeleteClient() throws Exception {
        //given
        String wrongClientId = "200";
        ErrorExtensionDto expectedExceptionDto = DtoCreator.getClientNotFoundExceptionDto();
        //when
        MvcResult clientControllerGetResult = mockMvc.perform(
                MockMvcRequestBuilders.delete("/client/delete/" + wrongClientId)
                        .with(httpBasic("admin@bankapp.com", "100")))
                .andReturn();
        String clientControllerGetExceptionJson = clientControllerGetResult.getResponse().getContentAsString();
        ErrorExtensionDto exceptionDto = objectMapper.readValue(clientControllerGetExceptionJson, ErrorExtensionDto.class);
        //then
        Assertions.assertEquals(404, clientControllerGetResult.getResponse().getStatus());
        Assertions.assertEquals(expectedExceptionDto, exceptionDto);
    }
}
