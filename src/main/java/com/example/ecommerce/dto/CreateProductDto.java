package com.example.ecommerce.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import java.math.BigDecimal;

@Data
public class CreateProductDto {
    String name;
    BigDecimal price;
    Long category_id;
    String description;
    MultipartFile image;
}
