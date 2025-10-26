package com.example.ecommerce.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class CreateProductCategoryDTO {
    private String name;
    private MultipartFile image;
}
