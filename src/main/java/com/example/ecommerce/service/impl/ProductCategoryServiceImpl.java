package com.example.ecommerce.service.impl;

import com.example.ecommerce.dto.CreateProductCategoryDTO;
import com.example.ecommerce.dto.ProductCategoryDto;
import com.example.ecommerce.dto.UpdateProductCategoryDTO;
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
                .map(this::convertToDto).toList();
    }

    @Override
    public ProductCategoryDto getProductCategory(Long id) {
        ProductCategory category = productCategoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product category not found with id: " + id));

        return convertToDto(category);
    }

    @Override
    public ProductCategoryDto createNewCategory(CreateProductCategoryDTO createProductCategoryDTO) {

        ProductCategory productCategory = new ProductCategory();
        MultipartFile file = createProductCategoryDTO.getImage();

        if(file != null){
            String fileName = file.getOriginalFilename();
            s3Service.uploadFile(file, fileName, BUCKET_PATH);
            productCategory.setImage(fileName);
        }

        productCategory.setName(createProductCategoryDTO.getName());

        ProductCategory newProductCategory = productCategoryRepository.save(productCategory);

        return convertToDto(newProductCategory);
    }

    public boolean deleteCategory(Long id){

        ProductCategory productCategory = productCategoryRepository.getReferenceById(id);

        if(productCategory.getImage() != null){
            s3Service.deleteFile(BUCKET_PATH + productCategory.getImage());
        }

        productCategoryRepository.delete(productCategory);
        return true;
    }

    @Override
    public ProductCategoryDto updateCategory(Long id, UpdateProductCategoryDTO updateProductCategoryDTO) {

        ProductCategory productCategory = productCategoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category Not found"));

        if(updateProductCategoryDTO.getName() != null) productCategory.setName(updateProductCategoryDTO.getName());

        MultipartFile file = updateProductCategoryDTO.getImage();;

        if(file != null){
            String fileName = file.getOriginalFilename();
            productCategory.setImage(fileName);
            s3Service.uploadFile(file, fileName, BUCKET_PATH);
        }

        productCategoryRepository.save(productCategory);

        return convertToDto(productCategory);
    }

    private ProductCategoryDto convertToDto(ProductCategory productCategory){
        return new ProductCategoryDto(
            productCategory.getId(),
            productCategory.getName(),
            productCategory.getImage()
        );
    }
}
