package com.sedeeman.citzimbproductcatalogservice.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sedeeman.citzimbproductcatalogservice.exception.DataLoadingException;
import com.sedeeman.citzimbproductcatalogservice.exception.ProductNotFoundException;
import com.sedeeman.citzimbproductcatalogservice.model.Product;
import com.sedeeman.citzimbproductcatalogservice.repository.ProductRepository;
import com.sedeeman.citzimbproductcatalogservice.request.ProductCreateRequest;
import com.sedeeman.citzimbproductcatalogservice.request.ProductUpdateRequest;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostConstruct
    public void init() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            List<Product> products = mapper.readValue(this.getClass().getResourceAsStream("/data.json"), new TypeReference<>() {
            });
            productRepository.saveAll(products);
        } catch (IOException e) {
            throw new DataLoadingException("Error loading products from JSON file: " + e.getMessage());
        }
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long productId) {
        return productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException("Product not found with ID " + productId));
    }

    public Product createProduct(ProductCreateRequest productCreateRequest) {
        try {
            Product product = new Product();
            product.setProductName(productCreateRequest.getProductName());
            product.setProductOwnerName(productCreateRequest.getProductOwnerName());
            product.setDevelopers(productCreateRequest.getDevelopers());
            product.setScrumMasterName(productCreateRequest.getScrumMasterName());
            product.setStartDate(productCreateRequest.getStartDate());
            product.setMethodology(productCreateRequest.getMethodology());
            return productRepository.save(product);
        } catch (Exception e) {
            throw new RuntimeException("Unable to create product: " + e.getMessage());
        }
    }

    public Product updateProduct(Long productId, ProductUpdateRequest productUpdateRequest) {
        Product existingProduct = productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException("Product not found with ID: " + productId));

        if (Objects.nonNull(productUpdateRequest.getProductName()) && !productUpdateRequest.getProductName().isBlank()) {
            existingProduct.setProductName(productUpdateRequest.getProductName());
        }
        if (Objects.nonNull(productUpdateRequest.getProductOwnerName()) && !productUpdateRequest.getProductOwnerName().isBlank()) {
            existingProduct.setProductOwnerName(productUpdateRequest.getProductOwnerName());
        }
        if (!CollectionUtils.isEmpty(productUpdateRequest.getDevelopers())) {
            existingProduct.setDevelopers(productUpdateRequest.getDevelopers());
        }
        if (Objects.nonNull(productUpdateRequest.getScrumMasterName()) && !productUpdateRequest.getScrumMasterName().isBlank()) {
            existingProduct.setScrumMasterName(productUpdateRequest.getScrumMasterName());
        }
        if (Objects.nonNull(productUpdateRequest.getMethodology()) && !productUpdateRequest.getMethodology().isBlank()) {
            existingProduct.setMethodology(productUpdateRequest.getMethodology());
        }

        try {
            return productRepository.save(existingProduct);
        } catch (Exception e) {
            throw new RuntimeException("Unable to update product:" + e.getMessage());
        }

    }

    public void removeProduct(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException("Product not found with ID " + productId));

        try {
            productRepository.delete(product);
        } catch (Exception e) {
            throw new RuntimeException("Unable to delete product:" + e.getMessage());
        }

    }

}
