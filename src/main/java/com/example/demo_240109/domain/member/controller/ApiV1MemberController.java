package com.example.demo_240109.domain.member.controller;

import com.example.demo_240109.domain.member.entity.Member;
import com.example.demo_240109.domain.member.request.MemberRequest;
import com.example.demo_240109.domain.member.response.MemberResponse;
import com.example.demo_240109.domain.member.service.MemberService;
import com.example.demo_240109.global.rq.Rq;
import com.example.demo_240109.global.rsData.RsData;
import com.example.demo_240109.global.security.SecurityUser;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/member")
@RequiredArgsConstructor
public class ApiV1MemberController {

    private final MemberService memberService;
    private final Rq rq;

    // 로그아웃
    @GetMapping("/logout")
    @ResponseBody
    public RsData<String> logout() {

        SecurityContextHolder.getContext().setAuthentication(null);

        rq.removeCrossDomainCookie("AccessToken");

        return RsData.of("200","로그아웃",null);
    }

    // Frontend 에서 로그인 여부 확인
    @GetMapping("/me")
    @ResponseBody
    public RsData<MemberResponse.me> me() {

        if(SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")){
            return RsData.of("401","안로그인",null);
        }
        SecurityUser securityUser = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return RsData.of("200","로그인", new MemberResponse.me(securityUser.getId(), securityUser.getUsername()));
    }

    // 로그인
    @PostMapping("/login")
    @ResponseBody
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
