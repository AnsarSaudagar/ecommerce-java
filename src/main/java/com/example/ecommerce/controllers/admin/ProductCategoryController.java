package com.example.ecommerce.controllers.admin;

import com.example.ecommerce.dto.ProductCategoryDto;
import com.example.ecommerce.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/product-categories")
public class ProductCategoryController {

    @Autowired
    ProductCategoryService productCategoryService;

    @GetMapping()
    public ResponseEntity<List<ProductCategoryDto>> getAllCategories(){
        return ResponseEntity.ok(productCategoryService.getAllCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductCategoryDto> getCategory(@PathVariable Long id){
        return ResponseEntity.ok(productCategoryService.getProductCategory(id));
    }
}
