package com.spring.shop.member;

import jakarta.persistence.*;
import lombok.Getter;


@Getter
@Entity
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(unique = true)
    private String displayName;
    @Column
    private String password;
    public Member(Long id, String username, String displayName, String password) {
    }
    public Member() {}

    public void setId(Long id) throws Exception{
        if(id != id){
            throw new Exception();
        } else if(id < 0){
            throw new Exception();
        } else {
            this.id = id;
        }
    }
    public void setUsername(String username) throws Exception{
        if (username.isEmpty()){
            throw new Exception();
        } else {
            this.username = username;
        }
    }
    public void setDisplayName(String displayName) throws Exception{
        if(displayName.isEmpty()){
            throw new Exception();
        } else {
            this.displayName = displayName;
        }
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
