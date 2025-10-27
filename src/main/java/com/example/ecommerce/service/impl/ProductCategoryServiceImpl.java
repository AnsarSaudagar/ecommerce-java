package com.example.ecommerce.service.impl;

import com.example.ecommerce.dto.CreateProductCategoryDTO;
import com.example.ecommerce.dto.ProductCategoryDto;
import com.example.ecommerce.entity.ProductCategory;
import com.example.ecommerce.repository.ProductCategoryRepository;
import com.example.ecommerce.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    private final String BUCKET_PATH = "category-images/";
    @Autowired
    ProductCategoryRepository productCategoryRepository;

    @Autowired
    S3ServiceImpl s3Service;

    @Override
//    @Cacheable("product-categories")
    public List<ProductCategoryDto> getAllCategories() {
        return productCategoryRepository.findAll().stream()
                .map(pc -> new ProductCategoryDto(
                        pc.getId(),
                        pc.getName(),
                        pc.getImage()
                )).toList();
    }

    @Override
    public ProductCategoryDto getProductCategory(Long id) {
        ProductCategory category = productCategoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product category not found with id: " + id));

        return new ProductCategoryDto(
                category.getId(),
                category.getName(),
                category.getImage()
        );
    }

    @Override
    public ProductCategoryDto createNewCategory(CreateProductCategoryDTO createProductCategoryDTO) {

        ProductCategory productCategory = new ProductCategory();
        MultipartFile file = createProductCategoryDTO.getImage();
        String fileName = file.getOriginalFilename();
        s3Service.uploadFile(file, fileName, BUCKET_PATH);

        productCategory.setName(createProductCategoryDTO.getName());
        productCategory.setImage(fileName);

        ProductCategory newProductCategory = productCategoryRepository.save(productCategory);

        return new ProductCategoryDto(
                newProductCategory.getId(),
                newProductCategory.getName(),
                newProductCategory.getImage()
        );
    }

    public boolean deleteCategory(Long id){

        ProductCategory productCategory = productCategoryRepository.getReferenceById(id);
        s3Service.deleteFile(BUCKET_PATH + productCategory.getImage());
        productCategoryRepository.delete(productCategory);
        return true;
    }
}
