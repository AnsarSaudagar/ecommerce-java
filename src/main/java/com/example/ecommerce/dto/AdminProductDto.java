package com.example.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class AdminProductDto {
    private Long id;
    private String name;
    private BigDecimal price;
    private String description;
    private String image;
}
