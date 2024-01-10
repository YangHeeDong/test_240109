package com.example.demo_240109.domain.article.response;

import com.example.demo_240109.domain.article.dto.ArticleDto;
import com.example.demo_240109.domain.article.entity.Article;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
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
        List<ArticleDto> articleList = new ArrayList<>();

        public getArticlesResponse(List<Article> articleList){
            for(Article article : articleList){
                ArticleDto articleDto = ArticleDto.builder()
                        .id(article.getId())
                        .title(article.getTitle())
                        .content(article.getContent())
                        .createDate(article.getCreateDate())
                        .modifyDate(article.getModifyDate())
                        .memberId(article.getMember().getId())
                        .nickname(article.getMember().getNickname()).build();

                this.articleList.add(articleDto);
            }

        }
    }

    @Getter
    public static class getArticleResponse {
        ArticleDto article;

        public getArticleResponse(Article article){
            this.article = ArticleDto.builder()
                    .id(article.getId())
                    .title(article.getTitle())
                    .content(article.getContent())
                    .createDate(article.getCreateDate())
                    .modifyDate(article.getModifyDate())
                    .memberId(article.getMember().getId())
                    .nickname(article.getMember().getNickname()).build();
        }

    }
}
