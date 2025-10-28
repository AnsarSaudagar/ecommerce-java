package com.example.ecommerce.service.impl;

import com.example.ecommerce.dto.DashboardDataCount;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.service.GlobalService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GlobalServiceImpl implements GlobalService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public DashboardDataCount dashboardDataCounts() {

        String productQuery = "Select COUNT(id) from Product";
        String productCategoryQuery = "Select COUNT(id) from ProductCategory";

        Long productCount = entityManager.createQuery(productQuery, Long.class).getSingleResult();
        Long productCategoryCount = entityManager.createQuery(productCategoryQuery, Long.class).getSingleResult();

        return new DashboardDataCount(
                productCount,
                0L,
                productCategoryCount,
                0L
        );
    }
}
