package com.example.demo_240109.domain.member.response;

import com.example.demo_240109.domain.member.entity.Member;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Getter
public class MemberResponse {

    @Getter
    public static class me{
        long id;
        String nickname;

        public me (long id, String nickname){
            this.id = id;
            this.nickname =nickname;
        }
    }

}
