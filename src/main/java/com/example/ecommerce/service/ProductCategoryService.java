package com.example.ecommerce.service;

import com.example.ecommerce.dto.ProductCategoryDto;

import java.util.List;

public interface ProductCategoryService {
    List<ProductCategoryDto> getAllCategories();
}
