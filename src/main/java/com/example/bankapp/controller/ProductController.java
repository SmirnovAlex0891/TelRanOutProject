package com.example.bankapp.controller;

import com.example.bankapp.dto.ProductDto;
import com.example.bankapp.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@Api(value = "Products", description = "Controller for product")
public class ProductController {
    private final ProductService productService;

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Update an Product by id", nickname = "updateProduct", response = ProductDto.class)
    public ProductDto updateProductById(@PathVariable("id") Long id, @RequestBody ProductDto productDto) {
        return productService.updateProduct(id, productDto);
    }

}
