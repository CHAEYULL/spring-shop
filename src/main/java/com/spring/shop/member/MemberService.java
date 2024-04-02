package com.spring.shop.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    public Member registerMemberSerivce(MemberDto memberDto) throws Exception {
        Member member = new Member();
        var hash = passwordEncoder.encode(memberDto.getPassword());
        member.setUsername(memberDto.getUsername());
        member.setDisplayName(memberDto.getDisplayName());
        member.setPassword(hash);
        memberRepository.save(member);
        return member;
    }

}
