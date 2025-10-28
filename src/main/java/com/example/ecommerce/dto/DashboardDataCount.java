package com.example.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DashboardDataCount {
    private Long products;
    private Long users;
    private Long categories;
    private Long reviews;
}
