package com.example.ecommerce.service;

import com.example.ecommerce.dto.AdminProductDto;

import java.util.List;

public interface ProductService {

    public List<AdminProductDto> getAllProductsForAdmin();
    public AdminProductDto getProduct(Long id);
}
