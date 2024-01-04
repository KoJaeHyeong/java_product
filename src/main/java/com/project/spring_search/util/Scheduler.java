package com.project.spring_search.util;

import com.project.spring_search.repository.ItemDto;
import com.project.spring_search.repository.Product;
import com.project.spring_search.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Component
public class Scheduler {
    private final ProductService productService;
    private final NaverShopSearch naverShopSearch;

    @Scheduled(cron = "0 0/1 * * * *")
    public void updatePrice() throws InterruptedException {
        System.out.println("CRON 가격 업데이트 실행!!");

        List<Product> productList = productService.findAllProducts();

        for (int i = 0; i < productList.size(); i++) {
            TimeUnit.SECONDS.sleep(1);

            Product product = productList.get(i);

            String title = product.getTitle();
            String itemStr = naverShopSearch.search(title);

            List<ItemDto> itemDtoList = naverShopSearch.fromJSONtoItems(itemStr);

            ItemDto itemDto = itemDtoList.get(0);

            UUID id = product.getId();

            productService.updateBySchedule(id, itemDto);
        }
    }

}
