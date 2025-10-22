package com.example.ecommerce.service;

import com.example.ecommerce.dto.AdminProductDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductService {

    public List<AdminProductDto> getAllProductsForAdmin();
}
