package com.example.bankapp.mapper;

import com.example.bankapp.dto.ProductDto;
import com.example.bankapp.entity.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductMapperTest {
    @Autowired
    private ProductMapper productMapper;
    @Test
    public void shouldTestProductMapperForNull() {
        //given
        Product product = new Product(
                (Long) null,null, null, null, 0.01, 5000,
                null, null, null, null);
        //when
        ProductDto expectedResult = productMapper.productToProductDto(product);
        //then
        Assertions.assertNull(productMapper.productToProductDto(null));
        Assertions.assertEquals(expectedResult, new ProductDto(null,null, null, "0.01", "5000",
                null, null));
    }
}
