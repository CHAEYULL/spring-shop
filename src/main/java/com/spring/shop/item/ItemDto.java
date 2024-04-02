package com.spring.shop.item;

import lombok.Getter;
import lombok.ToString;

@ToString
public class ItemDto {
    private Long id;
    private String title;
    private Integer price;

    public ItemDto(Long id, String title, Integer price){
        this.id = id;
        this.title = title;
        this.price = price;
    }
    public Item toEntity(){
        return new Item(id, title, price);
    }
    public Long getId(){
        return id;
    }
    public String getTitle(){
        return title;
    }
    public Integer getPrice(){
        return price;
    }

}
