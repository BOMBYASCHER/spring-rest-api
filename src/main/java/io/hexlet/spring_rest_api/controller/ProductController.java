package io.hexlet.spring_rest_api.controller;

import io.hexlet.spring_rest_api.dto.ProductCUDTO;
import io.hexlet.spring_rest_api.dto.ProductDTO;
import io.hexlet.spring_rest_api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    List<ProductDTO> read() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    ProductDTO read(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ProductDTO create(@RequestBody ProductCUDTO productCreateDTO) {
        return productService.createProduct(productCreateDTO);
    }

    @PutMapping("/{id}")
    ProductDTO update(@PathVariable Long id, @RequestBody ProductCUDTO productUpdateDTO) {
        return productService.updateProduct(id, productUpdateDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}
