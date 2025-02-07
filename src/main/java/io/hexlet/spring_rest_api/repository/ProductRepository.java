package io.hexlet.spring_rest_api.repository;

import io.hexlet.spring_rest_api.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsByTitle(String title);
}
