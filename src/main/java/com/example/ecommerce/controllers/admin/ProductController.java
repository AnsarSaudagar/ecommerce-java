package com.example.ecommerce.controllers.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.dto.CreateProductDto;
import com.example.ecommerce.dto.ProductDto;
import com.example.ecommerce.dto.UpdateProductDto;
import com.example.ecommerce.service.ProductService;
import com.example.ecommerce.service.S3Service;

@RestController
@RequestMapping("/admin/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    S3Service s3Service;

    @GetMapping()
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        return ResponseEntity.ok(productService.getAllProductsForAdmin());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Long id){
        return ResponseEntity.ok(productService.getProduct(id));
    }

    @PostMapping()
    public ResponseEntity<ProductDto> createProduct(@ModelAttribute CreateProductDto createProductDto){
        return ResponseEntity.ok(productService.createNewProduct(createProductDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> deleteProduct(@PathVariable Long id){
        return ResponseEntity.ok(productService.deleteProduct(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long id, @ModelAttribute UpdateProductDto updateProductDto){
        return ResponseEntity.ok(productService.updateProduct(id, updateProductDto));
    }

    @GetMapping("/download")
    public ResponseEntity<byte[]> downloadFile(@RequestParam String path) {
        return s3Service.downloadFile(path);
    }


}
