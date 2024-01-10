package com.example.demo_240109.domain.article.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import org.antlr.v4.runtime.misc.NotNull;

@Getter
public class ArticleRequest {
    @Getter
    public static class createArticleRequest{
        @NotBlank
        private String title;

        @NotBlank
        private String content;
    }

    @Getter
    public static class modifyArticleRequest {

        @NotBlank
        private long id;

        @NotBlank
        private String title;

        @NotBlank
        private String content;
    }
}
