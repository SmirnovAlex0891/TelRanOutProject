package com.example.bankapp.service.impl;

import com.example.bankapp.dto.ProductDto;
import com.example.bankapp.entity.Product;
import com.example.bankapp.entity.enums.CurrencyType;
import com.example.bankapp.entity.enums.ProductStatus;
import com.example.bankapp.mapper.ProductMapper;
import com.example.bankapp.repository.ProductRepository;
import com.example.bankapp.service.ProductService;
import com.example.bankapp.service.exception.ErrorMessage;
import com.example.bankapp.service.exception.ProductNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    @Override
    public ProductDto updateProduct(Long id, ProductDto productDto) {
        Product product = productRepository.findById(id).orElseThrow(() ->
                new ProductNotFoundException(String.format(ErrorMessage.PRODUCT_NOT_FOUND, id)));

            product.setName(productDto.getName());
            product.setStatus(ProductStatus.valueOf(productDto.getStatus()));
            product.setCurrencyType(CurrencyType.valueOf(productDto.getCurrencyType()));
            product.setInterestRate(Double.parseDouble(productDto.getInterestRate()));
            product.setLimit(Integer.parseInt(productDto.getLimit()));
            product.setUpdatedAt(LocalDate.now());
            productRepository.save(product);
            return productMapper.productToProductDto(product);
    }
}
