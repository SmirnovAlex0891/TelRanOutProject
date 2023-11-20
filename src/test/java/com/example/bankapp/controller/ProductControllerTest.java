package com.example.bankapp.controller;

import com.example.bankapp.dto.ErrorExtensionDto;
import com.example.bankapp.dto.ProductDto;
import com.example.bankapp.util.DtoCreator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import liquibase.pro.packaged.S;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldGetProductDtoAfterUpdateProduct() throws Exception {
        //given
        String productId = "5002";
        String incomingProductDto = objectMapper.writeValueAsString(DtoCreator.getIncomingProductDto());
        //when
        MvcResult productControllerGetResult = mockMvc.perform(
                MockMvcRequestBuilders.put("/product/update/" + productId)
                        .with(httpBasic("admin@bankapp.com", "100"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(incomingProductDto))
                .andReturn();
        ProductDto expectedProductDto = DtoCreator.getOutputProductDto();
        String productControllerGetResultString = productControllerGetResult.getResponse().getContentAsString();
        ProductDto actualProductDto = objectMapper.readValue(productControllerGetResultString, ProductDto.class);
        //then
        Assertions.assertEquals(200, productControllerGetResult.getResponse().getStatus());
        Assertions.assertEquals(expectedProductDto, actualProductDto);
    }
    @Test
    public void shouldGetProductNotFoundException() throws Exception {
        //given
        String wrongProductId = "5009";
        String incomingProductDto = objectMapper.writeValueAsString(DtoCreator.getIncomingProductDto());
        ErrorExtensionDto expectedResult = DtoCreator.getProductNotFoundException();
        //when
        MvcResult productControllerGetResult = mockMvc.perform(
                MockMvcRequestBuilders.put("/product/update/" + wrongProductId)
                        .with(httpBasic("admin@bankapp.com", "100"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(incomingProductDto))
                .andReturn();
        String productControllerGetResultString = productControllerGetResult.getResponse().getContentAsString();
        ErrorExtensionDto actualResult = objectMapper.readValue(productControllerGetResultString, ErrorExtensionDto.class);
        //then
        Assertions.assertEquals(404, productControllerGetResult.getResponse().getStatus());
        Assertions.assertEquals(expectedResult, actualResult);
    }
}
