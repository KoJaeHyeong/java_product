package com.project.spring_search.service;

import com.project.spring_search.repository.*;
import com.project.spring_search.util.NaverShopSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final NaverShopSearch naverShopSearch;

    public Product create(ProductRequestDto requestDto) {
        Product product = new Product(requestDto);

        return this.productRepository.save(product);
    }


    public List<Product> findAllProducts() {
        List<Product> result = this.productRepository.findAll();

        return result;
    }

    public List<ItemDto> getAllItems(String item) {
        String resultStr = naverShopSearch.search(item);

        return naverShopSearch.fromJSONtoItems(resultStr);
    }

    @Transactional
    public UUID update(UUID id, ProductMypriceRequestDto requestDto) {
        Product product = this.productRepository.findById(id).orElseThrow(() -> new NullPointerException("해당 아이디가 존재하지 않습니다."));

        product.update(requestDto);

        return id;
    }

    @Transactional
    public UUID updateBySchedule(UUID id, ItemDto itemDto) {
        Product product = this.productRepository.findById(id).orElseThrow(() -> new NullPointerException("해당 아이디가 존재하지 않습니다."));
        ProductMypriceRequestDto requestDto = new ProductMypriceRequestDto();

        product.updateBySchedule(itemDto);

        return id;
    }
}
