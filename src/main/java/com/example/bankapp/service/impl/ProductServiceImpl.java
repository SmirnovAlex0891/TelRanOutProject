package com.example.bankapp.service.impl;

import com.example.bankapp.dto.ProductDto;
import com.example.bankapp.entity.Product;
import com.example.bankapp.mapper.ProductMapper;
import com.example.bankapp.repository.ProductRepository;
import com.example.bankapp.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    @Override
    public ProductDto updateProduct(Long id, ProductDto productDto) {
        Product product = productRepository.findById(id).orElse(null);
        if (product != null) {
        product.setName(productDto.getName());
        product.setStatus(productDto.getStatus());
        product.setCurrencyCode(productDto.getCurrencyCode());
        product.setInterestRate(productDto.getInterestRate());
        product.setLimit(productDto.getLimit());
        product.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        productRepository.save(product);
        }

        return productMapper.productToProductDto(product);
    }
}
