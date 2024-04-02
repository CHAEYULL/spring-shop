package com.spring.shop.member;

import com.spring.shop.item.Item;
import com.spring.shop.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;


@RequiredArgsConstructor
@RestController
public class MemberController {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final MemberService memberService;
    private final ItemRepository itemRepository;
    @GetMapping("/")
    public String getMainPage(){
        return "http://localhost:3000/";
    }
    @PostMapping("/api/register")
    public ResponseEntity<?> registerMember(@RequestBody MemberDto memberDto) throws Exception {
        var result = memberService.registerMemberSerivce(memberDto);
        return ResponseEntity.status(201).body(result);
    }
    @GetMapping("/api/mypage")
    public ResponseEntity<?> getMypage(Authentication auth){
        var result = ((CustomUser) auth.getPrincipal()).getUsername();
        List<Item> writerList = itemRepository.findAllByWriter(result);
        return ResponseEntity.status(200).body(writerList);
    }
    @GetMapping("/api/userdata")
    public ResponseEntity<?> getUserInfo(Authentication auth){
        var result = (CustomUser) auth.getPrincipal();
        return ResponseEntity.status(200).body(result);
    }

}
