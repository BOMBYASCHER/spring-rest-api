package io.hexlet.spring_rest_api.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {
    @GetMapping
    String index() {
        return "products";
    }

    @GetMapping("/{id}")
    String show(@PathVariable Long id) {
        return "product: " + id;
    }

    @PostMapping
    String create(@RequestBody String body) {
        return "created: " + body;
    }

    @PutMapping("/{id}")
    String update(@PathVariable Long id, @RequestBody String body) {
        return "updated " + id + " to " + body;
    }

    @DeleteMapping("/{id}")
    String delete(@PathVariable Long id) {
        return "deleted: " + id;
    }
}
