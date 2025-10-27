package com.example.ecommerce.service;

import com.example.ecommerce.dto.ProductDto;
import com.example.ecommerce.dto.CreateProductDto;

import java.util.List;

public interface ProductService {

    public List<ProductDto> getAllProductsForAdmin();
    public ProductDto getProduct(Long id);
    ProductDto createNewCategory(CreateProductDto createProductDto);
    boolean deleteCategory(Long id);
}
