package com.example.demo_240109.domain.article.request;

import lombok.Getter;
import org.antlr.v4.runtime.misc.NotNull;

@Getter
public class ArticleRequest {
    @Getter
    public static class createArticleRequest{
        @NotNull
        private String title;

        @NotNull
        private String content;
    }
}
