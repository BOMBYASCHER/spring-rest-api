package io.hexlet.spring_rest_api.mapper;

import io.hexlet.spring_rest_api.dto.ProductCUDTO;
import io.hexlet.spring_rest_api.dto.ProductDTO;
import io.hexlet.spring_rest_api.model.Product;

public class ProductMapper {
    public static ProductDTO toDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setTitle(product.getTitle());
        productDTO.setPrice(product.getPrice());
        productDTO.setDescription(product.getDescription());
        productDTO.setImage(product.getImage());
        productDTO.setAvailability(product.getAvailability());
        return productDTO;
    }

    public static Product toEntity(ProductCUDTO productDTO) {
        Product product = new Product();
        product.setTitle(productDTO.getTitle());
        product.setPrice(productDTO.getPrice());
        product.setDescription(productDTO.getDescription());
        product.setImage(productDTO.getImage());
        product.setAvailability(productDTO.getAvailability());
        return product;
    }
    public static void update(Product product, ProductCUDTO productUpdate) {
        product.setTitle(productUpdate.getTitle());
        product.setPrice(productUpdate.getPrice());
        product.setDescription(productUpdate.getDescription());
        product.setImage(productUpdate.getImage());
        product.setAvailability(productUpdate.getAvailability());
    }
}
