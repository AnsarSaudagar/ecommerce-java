package com.example.ecommerce.service.impl;

import com.example.ecommerce.dto.AdminProductDto;
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
    public List<AdminProductDto> getAllProductsForAdmin() {
        return productRepository.findAll().stream()
                .map(p -> new AdminProductDto(
                        p.getId(),
                        p.getName(),
                        p.getPrice(),
                        p.getDescription(),
                        p.getImage(),
                        p.getProductCategory().getName()
                ))
                .toList();
    }
}
