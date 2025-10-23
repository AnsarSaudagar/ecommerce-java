package com.example.ecommerce.controllers.admin;

import com.example.ecommerce.dto.AdminProductDto;
import com.example.ecommerce.service.ProductService;
import com.example.ecommerce.service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    S3Service s3Service;

    @GetMapping()
    public ResponseEntity<List<AdminProductDto>> getAllProducts(){
        return ResponseEntity.ok(productService.getAllProductsForAdmin());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdminProductDto> getCategory(@PathVariable Long id){
        return ResponseEntity.ok(productService.getProduct(id));
    }

    @GetMapping("/download")
    public ResponseEntity<byte[]> downloadFile(@RequestParam String path) {
        return s3Service.downloadFile(path);
    }
}
