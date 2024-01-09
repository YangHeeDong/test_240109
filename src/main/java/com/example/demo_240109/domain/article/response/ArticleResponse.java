package com.example.demo_240109.domain.article.response;

import com.example.demo_240109.domain.article.entity.Article;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Optional;

@Getter
public class ArticleResponse {

    @Getter
    public static class createArticleResponse{
        private long id;

        public createArticleResponse(Article article){
            this.id = article.getId();
        }
    }

    @Getter
    public static class getArticlesResponse {
        List<Article> articleList;

        public getArticlesResponse(List<Article> articleList){
            this.articleList = articleList;
        }
    }

    @Getter
    public static class getArticleResponse {
        Article article;

        public getArticleResponse(Article article){
            this.article = article;
        }

    }
}
