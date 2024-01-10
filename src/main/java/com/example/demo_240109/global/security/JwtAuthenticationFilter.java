package com.example.demo_240109.global.security;

import com.example.demo_240109.domain.member.service.MemberService;
import com.example.demo_240109.global.rq.Rq;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final Rq rq;
    private final MemberService memberService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String accessToken = rq.getCookieValue("AccessToken",null);

        // 토큰이 있다면
        if(accessToken != null){

            if(memberService.validationToken(accessToken)){
                //토큰 기반으로 User 생성
                SecurityUser user = memberService.getUserByAccessToken(accessToken);
                rq.setLogin(user);

            }else{
                rq.removeCrossDomainCookie("AccessToken");
                rq.setLogout();
            }
        }else{
            rq.setLogout();
        }

        filterChain.doFilter(request,response);
    }


}
