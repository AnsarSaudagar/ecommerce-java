package com.example.ecommerce.repository;

import com.example.ecommerce.dto.ProductCategoryDto;
import com.example.ecommerce.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
    Optional<ProductCategoryDto> getProductCategoryById(Long id);
}
