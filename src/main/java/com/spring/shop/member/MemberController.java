package com.spring.shop.member;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class MemberController {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final MemberService memberService;
    @PostMapping("/api/register")
    public ResponseEntity<?> registerMember(@RequestBody MemberDto memberDto) throws Exception {
        var result = memberService.registerMemberSerivce(memberDto);
        return ResponseEntity.status(201).body(result);
    }
    @GetMapping("/api/mypage")
    public ResponseEntity<?> getUserInfo(Authentication auth){
        System.out.println(auth);
        return ResponseEntity.status(200).body(auth);
    }
}
