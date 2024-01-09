package com.example.demo_240109.domain.member.service;

import com.example.demo_240109.domain.member.entity.Member;
import com.example.demo_240109.global.app.AppConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.SecureDigestAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthTokenService {

    public String genToken(Member member, long expireSeconds){

        // 토큰에 넣을 데이터
        Claims claims = Jwts.claims()
                .add("id",member.getId())
                .add("nickname",member.getNickname())
                .add("authorities",member.getAuthoritiesAsStringList())
                .build();

        // 현재 날짜
        Date now = new Date();

        // 만료날짜
        Date validity = new Date(now.getTime() + 1000 * expireSeconds);

        // 토큰 만들어서 리턴
        return Jwts.builder()
                .claims(claims)
                .issuedAt(now)
                .expiration(validity)
                .signWith(SignatureAlgorithm.HS256, AppConfig.getJwtSecretKey())
                .compact();
    }

    // 토큰 유효성 검사
    public boolean validationToken(String token){
        try{
            Jwts.parser().setSigningKey(AppConfig.getJwtSecretKey()).build().parseClaimsJws(token);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }


    public Claims decode(String accessToken) {
        return Jwts
                .parser()
                .setSigningKey(AppConfig.getJwtSecretKey())
                .build()
                .parseClaimsJws(accessToken)
                .getPayload();
    }
}
