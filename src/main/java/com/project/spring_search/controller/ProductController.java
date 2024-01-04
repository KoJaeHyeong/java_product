package com.project.spring_search.controller;

import com.project.spring_search.repository.*;
import com.project.spring_search.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class ProductController {
    private final ProductService productService;

    @PostMapping("/api/products")
    public Product createProduct(@RequestBody ProductRequestDto requestDto) {

        return  this.productService.create(requestDto);
    }


    @GetMapping("/api/products")
    public List<Product> getProducts() {
        return this.productService.findAllProducts();
    }

    @PutMapping("/api/products/{id}")
    public UUID updateProducts(@PathVariable UUID id, @RequestBody ProductMypriceRequestDto requestDto) {
        return this.productService.update(id, requestDto);
    }

    @GetMapping("/api/search")
    public List<ItemDto> getItems(@RequestParam String item) {
        return this.productService.getAllItems(item);
    }
}
