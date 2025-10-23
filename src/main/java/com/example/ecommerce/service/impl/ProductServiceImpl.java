package com.example.ecommerce.service.impl;

import com.example.ecommerce.dto.AdminProductDto;
import com.example.ecommerce.dto.ProductCategoryDto;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.entity.ProductCategory;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<AdminProductDto> getAllProductsForAdmin() {
        return productRepository.findAll().stream()
                .map(p -> new AdminProductDto(
                        p.getId(),
                        p.getName(),
                        p.getPrice(),
                        p.getDescription(),
                        p.getImage(),
                        p.getProductCategory().getName(),
                        p.getProductCategory().getId()
                ))
                .toList();
    }

    @Override
    public AdminProductDto getProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product category not found with id: " + id));

        return new AdminProductDto(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getDescription(),
                product.getImage(),
                product.getProductCategory().getName(),
                product.getProductCategory().getId()
        );
    }


}
