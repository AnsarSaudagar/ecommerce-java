package com.example.ecommerce.service;

import com.example.ecommerce.dto.CreateProductCategoryDTO;
import com.example.ecommerce.dto.ProductCategoryDto;

import java.util.List;
import java.util.Optional;

public interface ProductCategoryService {
    List<ProductCategoryDto> getAllCategories();
    ProductCategoryDto getProductCategory(Long id);
    Object createNewCategory(CreateProductCategoryDTO createProductCategoryDTO);
}
