package com.example.demo_240109.domain.member.request;

import lombok.Getter;
import org.antlr.v4.runtime.misc.NotNull;

@Getter
public class MemberRequest {

    @Getter
    public static class createMemberRequest{
        @NotNull
        private String loginId;

        @NotNull
        private String password;

        @NotNull
        private String passwordConfirm;

        @NotNull
        private String email;

        @NotNull
        private String nickname;
    }

    public static class loginMemberRequest {
        @NotNull
        private String loginId;

        @NotNull
        private String password;
    }
}
