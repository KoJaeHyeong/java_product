package com.project.spring_search.repository;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
@Entity(name = "product")
public class Product extends Timestamped{

    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    private UUID id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private String link;

    @Column(nullable = false)
    private int lprice;

    @Column(nullable = false)
    private int myPrice;

    public Product(ProductRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.image = requestDto.getImage();
        this.link = requestDto.getLink();
        this.lprice = requestDto.getLprice();
        this.myPrice = 0;
    }

    public void update(ProductMypriceRequestDto requestDto) {
        this.myPrice = requestDto.getMyPrice();
    }

    public void updateBySchedule(ItemDto itemDto) {
        this.title = itemDto.getTitle();
        this.image = itemDto.getImage();
        this.link = itemDto.getLink();
        this.lprice = itemDto.getLprice();
    }
}
