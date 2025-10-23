package com.example.ecommerce.service.impl;

import com.example.ecommerce.dto.ProductCategoryDto;
import com.example.ecommerce.entity.ProductCategory;
import com.example.ecommerce.repository.ProductCategoryRepository;
import com.example.ecommerce.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    ProductCategoryRepository productCategoryRepository;

    @Override
    @Cacheable("product-categories")
    public List<ProductCategoryDto> getAllCategories() {
        return productCategoryRepository.findAll().stream()
                .map(pc -> new ProductCategoryDto(
                        pc.getId(),
                        pc.getName(),
                        pc.getImage()
                )).toList();
    }

    @Override
    public ProductCategoryDto getProductCategory(Long id) {
        ProductCategory category = productCategoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product category not found with id: " + id));

        return new ProductCategoryDto(
                category.getId(),
                category.getName(),
                category.getImage()
        );
    }
}
