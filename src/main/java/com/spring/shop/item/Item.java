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
    public Item(Long id, String title, Integer price){
        this.id = id;
        this.title = title;
        this.price = price;
    }

    public Item() {

    }
}
