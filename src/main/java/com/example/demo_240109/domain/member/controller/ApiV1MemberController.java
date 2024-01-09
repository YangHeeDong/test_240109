package com.example.demo_240109.domain.member.controller;

import com.example.demo_240109.domain.member.entity.Member;
import com.example.demo_240109.domain.member.request.MemberRequest;
import com.example.demo_240109.domain.member.service.MemberService;
import com.example.demo_240109.global.rsData.RsData;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/member")
@RequiredArgsConstructor
public class ApiV1MemberController {

    private final MemberService memberService;

    // 로그인
    @PostMapping("/login")
    public RsData<String> login(@Valid @RequestBody MemberRequest.loginMemberRequest loginForm) {

        return memberService.login(loginForm);
    }

    // 회원가입
    @PostMapping("/join")
    @ResponseBody
    public RsData<String> join(@Valid @RequestBody MemberRequest.createMemberRequest joinForm){

        // 데이터 유효성 검사
        if(!joinForm.getPassword().trim().equals(joinForm.getPasswordConfirm().trim())){
            return RsData.of("401","비밀번호와 비밀번호 확인이 일치하지 않아요", null);
        }

        // 중복체크 메서드
        RsData<String> check =  memberService.duplicateCheckForJoin(joinForm.getLoginId(), joinForm.getEmail(), joinForm.getNickname());
                
        if(check.isFail()){
            return RsData.of(check.getResultCode(),check.getMsg(),null);
        }


        return memberService.join(joinForm);
    }

}
