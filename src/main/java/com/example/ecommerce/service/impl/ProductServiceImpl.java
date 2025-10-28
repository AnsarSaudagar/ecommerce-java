package com.example.ecommerce.service.impl;

import com.example.ecommerce.dto.CreateProductDto;
import com.example.ecommerce.dto.ProductDto;
import com.example.ecommerce.dto.UpdateProductDto;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.entity.ProductCategory;
import com.example.ecommerce.repository.ProductCategoryRepository;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.service.ProductService;
import com.example.ecommerce.service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final String BUCKET_PATH = "products/";
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Autowired
    S3Service s3Service;

    @Override
    public List<ProductDto> getAllProductsForAdmin() {
        return productRepository.findAll().stream()
                .map(p -> new ProductDto(
                        p.getId(),
                        p.getName(),
                        p.getPrice(),
                        p.getDescription(),
                        p.getImage(),
                        p.getProductCategory().getName(),
                        p.getProductCategory().getId()
                ))
                .toList();
    }

    @Override
    public ProductDto getProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product category not found with id: " + id));

        return convertToDto(product);
    }

    @Override
    public ProductDto createNewProduct(CreateProductDto createProductDto) {

        Product product = new Product();
        product.setName(createProductDto.getName());
        product.setPrice(createProductDto.getPrice());
        product.setDescription(createProductDto.getDescription());

        MultipartFile file = createProductDto.getImage();

        if(file != null){
            String fileName = file.getOriginalFilename();
            product.setImage(fileName);
        } else {
            product.setImage(null);
        }

        ProductCategory productCategory = productCategoryRepository.getReferenceById(createProductDto.getCategory_id());
        product.setProductCategory(productCategory);

        productRepository.save(product);

        if(file != null){
            String fileName = file.getOriginalFilename();
            s3Service.uploadFile(file,"product_" + product.getId() + "/" + fileName, BUCKET_PATH);
        }

        return convertToDto(product);
    }

    @Override
    public boolean deleteProduct(Long id) {

        Product product = productRepository.getReferenceById(id);

        System.out.println(product.getImage());
        if(product.getImage() != null){
            s3Service.deleteFile(BUCKET_PATH + "product_" + product.getId() + "/" + product.getImage());
        }

        productRepository.delete(product);

        return true;
    }

    @Override
    public ProductDto updateProduct(Long id, UpdateProductDto updateProductDto) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product Not found"));

        if(updateProductDto.getName() != null) product.setName(updateProductDto.getName());
        if(updateProductDto.getPrice() != null) product.setPrice(updateProductDto.getPrice());
        if(updateProductDto.getDescription() != null) product.setDescription(updateProductDto.getDescription());

        if(updateProductDto.getCategory_id() != null) {
            ProductCategory productCategory = productCategoryRepository.getReferenceById((long) updateProductDto.getCategory_id());
            product.setProductCategory(productCategory);
        }

        MultipartFile file = updateProductDto.getImage();

        if(file != null){
            String fileName = file.getOriginalFilename();
            product.setImage(fileName);
            s3Service.uploadFile(file,"product_" + product.getId() + "/" + fileName, BUCKET_PATH);
        }

        productRepository.save(product);

        return convertToDto(product);
    }

    /**
     * @param product  Product Entity Object
     * @return productDto
     * <p>
    * Helper function for converting Entity to DTO
    * */
    private ProductDto convertToDto(Product product){
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getDescription(),
                product.getImage(),
                product.getProductCategory().getName(),
                product.getProductCategory().getId()
        );
    }


}
