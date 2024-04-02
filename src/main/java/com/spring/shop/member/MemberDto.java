package com.spring.shop.member;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class MemberDto {
    private Long id;
    private String username;
    private String displayName;
    private String password;

    public Member toEntity(){
        return new Member(id, username,displayName,password);
    }
    public MemberDto(Long id, String username, String displayName, String password){
        this.id = id;
        this.username = username;
        this.displayName = displayName;
        this.password = password;
    }
}
