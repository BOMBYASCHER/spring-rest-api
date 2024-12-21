package io.hexlet.spring_rest_api.service;

import io.hexlet.spring_rest_api.dto.ProductCUDTO;
import io.hexlet.spring_rest_api.dto.ProductDTO;
import io.hexlet.spring_rest_api.mapper.ProductMapper;
import io.hexlet.spring_rest_api.model.Product;
import io.hexlet.spring_rest_api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(ProductMapper::toDTO)
                .toList();
    }

    public ProductDTO getProductById(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found."));
        return ProductMapper.toDTO(product);
    }

    public ProductDTO createProduct(ProductCUDTO productCUDTO) {
        Product product = ProductMapper.toEntity(productCUDTO);
        productRepository.save(product);
        return ProductMapper.toDTO(product);
    }

    public ProductDTO updateProduct(Long productId, ProductCUDTO productCUDTO) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found."));
        ProductMapper.update(product, productCUDTO);
        productRepository.save(product);
        return ProductMapper.toDTO(product);
    }

    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }
}
