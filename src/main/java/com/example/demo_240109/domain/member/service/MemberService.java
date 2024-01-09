package com.example.demo_240109.domain.member.service;

import com.example.demo_240109.domain.member.entity.Member;
import com.example.demo_240109.domain.member.repository.MemberRepository;
import com.example.demo_240109.domain.member.request.MemberRequest;
import com.example.demo_240109.global.rsData.RsData;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;


    // 로그인
    @Transactional
    public RsData<String> login(MemberRequest.loginMemberRequest loginForm) {

    }

    // 가입
    @Transactional
    public RsData<String> join(MemberRequest.createMemberRequest joinForm) {

        Member member = Member.builder()
                .loginId(joinForm.getLoginId())
                .password(passwordEncoder.encode(joinForm.getPassword()))
                .email(joinForm.getEmail())
                .nickname(joinForm.getNickname())
                .createDate(LocalDateTime.now())
                .modifyDate(LocalDateTime.now())
                .build();

        memberRepository.save(member);

        return RsData.of("200","회원가입 되었다", "null");
    }

    // 중복체크 검사
    @Transactional
    public RsData<String> duplicateCheckForJoin(String loginId, String email, String nickname) {

        // 로그인 아이디 중복체크
        Optional<Member> memberLoginId = memberRepository.findByLoginId(loginId);

        if(memberLoginId.isPresent()){
            return RsData.of("401","해당 아이디로 이미 가입 되어 있습니다.", null);
        }

        // 이메일 중복체크
        Optional<Member> memberEmail = memberRepository.findByEmail(email);
        if(memberEmail.isPresent()){
            return RsData.of("401","해당 이메일로 이미 가입 되어 있습니다.", null);
        }

        // 닉네임 중복체크
        Optional<Member> memberNickname = memberRepository.findByNickname(nickname);
        if(memberNickname.isPresent()){
            return RsData.of("401","중복된 닉네임 입니다.", null);
        }

        return RsData.of("200","중복체크 완료", "null");

    }

}
