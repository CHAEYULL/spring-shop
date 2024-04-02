package com.spring.shop.item;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@Entity
public class Item {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private Integer price;

    private String imageUrl;

    private String writer;

    public void setId(Long id) throws Exception{
        if(id != id){
            throw new Exception();
        } else if(id < 0){
            throw new Exception();
        } else {
            this.id = id;
        }
    }
    public void setTitle(String title) throws Exception{
        if(title.isEmpty()){
            throw new Exception();
        } else {
            this.title = title;
        }
    }
    public void setPrice(Integer price) throws Exception{
        if(price < 0){
            throw new Exception();
        } else {
            this.price = price;
        }
    }
    public void setImageUrl(String imageUrl) throws Exception{
        if (imageUrl.isEmpty()){
            throw new Exception();
        } else {
            this.imageUrl =imageUrl;
        }
    }
    public void setWriter(String writer) throws Exception {
        if (writer.isEmpty()){
            throw new Exception();
        } else {
            this.writer = writer;
        }
    }
    public Item(Long id, String title, Integer price, String imageUrl, String writer){
        this.id = id;
        this.title = title;
        this.price = price;
        this.imageUrl = imageUrl;
        this.writer = writer;
    }

    public Item() {

    }
}
