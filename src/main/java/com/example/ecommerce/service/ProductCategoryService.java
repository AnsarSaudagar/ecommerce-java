package com.example.ecommerce.service;

import com.example.ecommerce.dto.CreateProductCategoryDTO;
import com.example.ecommerce.dto.ProductCategoryDto;
import com.example.ecommerce.dto.UpdateProductCategoryDTO;

import java.util.List;

public interface ProductCategoryService {
    List<ProductCategoryDto> getAllCategories();
    ProductCategoryDto getProductCategory(Long id);
    ProductCategoryDto createNewCategory(CreateProductCategoryDTO createProductCategoryDTO);
    boolean deleteCategory(Long id);
    ProductCategoryDto updateCategory(Long id, UpdateProductCategoryDTO updateProductCategoryDTO);
}
