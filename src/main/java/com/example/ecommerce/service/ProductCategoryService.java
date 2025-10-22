package com.example.ecommerce.service;

import com.example.ecommerce.dto.ProductCategoryDto;

import java.util.List;
import java.util.Optional;

public interface ProductCategoryService {
    List<ProductCategoryDto> getAllCategories();
    ProductCategoryDto getProductCategory(Long id);
}
