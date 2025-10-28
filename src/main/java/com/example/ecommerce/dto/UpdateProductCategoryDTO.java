package com.example.ecommerce.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UpdateProductCategoryDTO {
    private String name;
    private MultipartFile image;
}
