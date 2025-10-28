package com.example.ecommerce.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@Data
public class UpdateProductDto {
    String name;
    BigDecimal price;
    Integer category_id;
    String description;
    MultipartFile image;
}
