package com.example.ecommerce.service;

import com.example.ecommerce.dto.ProductDto;
import com.example.ecommerce.dto.CreateProductDto;

import java.util.List;

public interface ProductService {
    public List<ProductDto> getAllProductsForAdmin();
    public ProductDto getProduct(Long id);
    ProductDto createNewProduct(CreateProductDto createProductDto);
    boolean deleteProduct(Long id);
}
