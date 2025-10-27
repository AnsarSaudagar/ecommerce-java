package com.example.ecommerce.service.impl;

import com.example.ecommerce.dto.CreateProductDto;
import com.example.ecommerce.dto.ProductDto;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<ProductDto> getAllProductsForAdmin() {
        return productRepository.findAll().stream()
                .map(p -> new ProductDto(
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
    public ProductDto getProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product category not found with id: " + id));

        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getDescription(),
                product.getImage(),
                product.getProductCategory().getName(),
                product.getProductCategory().getId()
        );
    }

    @Override
    public ProductDto createNewCategory(CreateProductDto createProductDto) {
        return null;
    }

    @Override
    public boolean deleteCategory(Long id) {
        return false;
    }


}
