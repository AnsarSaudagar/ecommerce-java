package com.example.ecommerce.dto;

import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

public class CreateProductDto {
    String name;
    BigDecimal price;
    int category;
    String description;
    MultipartFile img;
}
