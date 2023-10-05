package com.example.bankapp.service;

import com.example.bankapp.dto.ProductDto;

public interface ProductService {
    ProductDto updateProduct(Long id, ProductDto productDto);
}
