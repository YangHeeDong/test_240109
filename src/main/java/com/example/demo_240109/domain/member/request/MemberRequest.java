package com.example.demo_240109.domain.member.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import org.antlr.v4.runtime.misc.NotNull;

@Getter
public class MemberRequest {

    @Getter
    public static class createMemberRequest{
        @NotBlank
        private String loginId;

        @NotBlank
        private String password;

        @NotBlank
        private String passwordConfirm;

        @NotBlank
        private String email;

        @NotBlank
        private String nickname;
    }

    @Getter
    public static class loginMemberRequest {
        @NotBlank
        private String loginId;

        @NotBlank
        private String password;
    }
}
