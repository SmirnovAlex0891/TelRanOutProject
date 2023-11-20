package com.example.bankapp.controller;

import com.example.bankapp.dto.ErrorExtensionDto;
import com.example.bankapp.dto.ManagerDto;
import com.example.bankapp.util.DtoCreator;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;

@SpringBootTest
@AutoConfigureMockMvc
public class ManagerControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldGetManagersByAccountBalance() throws Exception {
        //given
        String balance = "10000";
        List<ManagerDto> expectedResult = DtoCreator.getListManagersDto();
        //when
        MvcResult managerControllerGetResult = mockMvc.perform(
                MockMvcRequestBuilders.get("/manager/all/" + balance)
                        .with(httpBasic("admin@bankapp.com", "100")))
                .andReturn();
        String managerControllerGetResultJson = managerControllerGetResult.getResponse().getContentAsString();
        List<ManagerDto> actualResult = objectMapper.readValue(managerControllerGetResultJson, new TypeReference<>() {
        });
        //then
        Assertions.assertEquals(200, managerControllerGetResult.getResponse().getStatus());
        Assertions.assertEquals(expectedResult, actualResult);
    }
    @Test
    public void shouldGetManagerNotFoundException() throws Exception {
        //given
        String wrongBalance = "20000";
        ErrorExtensionDto expectedResult = DtoCreator.getManagerNotFoundExceptionDto();
        //when
        MvcResult managerControllerGetResult = mockMvc.perform(
                MockMvcRequestBuilders.get("/manager/all/" + wrongBalance)
                        .with(httpBasic("admin@bankapp.com", "100")))
                .andReturn();
        String managerControllerGetResultJson = managerControllerGetResult.getResponse().getContentAsString();
        ErrorExtensionDto actualResult = objectMapper.readValue(managerControllerGetResultJson, ErrorExtensionDto.class);
        //then
        Assertions.assertEquals(404, managerControllerGetResult.getResponse().getStatus());
        Assertions.assertEquals(expectedResult, actualResult);
    }
}
