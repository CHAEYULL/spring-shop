package com.spring.shop.item;

import lombok.Getter;
import lombok.ToString;

@ToString
public class ItemDto {
    private Long id;
    private String title;
    private Integer price;
    private String imageUrl;
    private String writer;
    public ItemDto(Long id, String title, Integer price, String imageUrl ,String writer){
        this.id = id;
        this.title = title;
        this.price = price;
        this.imageUrl = imageUrl;
        this.writer = writer;
    }
    public Item toEntity(){
        return new Item(id, title, price, imageUrl ,writer);
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
    public String getImageUrl(){return imageUrl;}
    public String getWriter() {return writer;}

}
