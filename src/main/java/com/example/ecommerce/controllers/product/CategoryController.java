package com.example.ecommerce.controllers.product;

import com.example.ecommerce.dto.ProductCategoryDto;
import com.example.ecommerce.entity.ProductCategory;
import com.example.ecommerce.repository.ProductCategoryRepository;
import com.example.ecommerce.service.impl.ProductCategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product/category")
public class CategoryController {

    @Autowired
    ProductCategoryServiceImpl productCategoryService;

    @GetMapping()
    public ResponseEntity<List<ProductCategoryDto>> getCategories(){
        return ResponseEntity.ok(productCategoryService.getAllCategories());
    }
}
