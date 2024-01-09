package com.example.demo_240109.domain.article.controller;

import com.example.demo_240109.domain.article.entity.Article;
import com.example.demo_240109.domain.article.request.ArticleRequest;
import com.example.demo_240109.domain.article.response.ArticleResponse;
import com.example.demo_240109.domain.article.service.ArticleService;
import com.example.demo_240109.global.rsData.RsData;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/articles")
@RequiredArgsConstructor
public class ApiV1ArticleController {

    private final ArticleService articleService;

    // 단건조회
    @GetMapping("{id}")
    @ResponseBody
    private RsData<ArticleResponse.getArticleResponse> getArticleById (@PathVariable long id){

        RsData<Article>rsData = articleService.getArticleById(id);

        return  RsData.of(rsData.getResultCode(), rsData.getMsg(),new ArticleResponse.getArticleResponse(rsData.getData()));
    }

    // 다건조회
    @GetMapping("")
    @ResponseBody
    private RsData<ArticleResponse.getArticlesResponse> getArticles (){

        List<Article> articleList = articleService.getArticles();

        return  RsData.of("200", "다건조회",new ArticleResponse.getArticlesResponse(articleList));
    }

    // 등록
    @PostMapping("")
    @ResponseBody
    private RsData<ArticleResponse.createArticleResponse> createArticle (@Valid @RequestBody ArticleRequest.createArticleRequest articleRequest){

        RsData<Article> rsData = articleService.createArticle(articleRequest.getTitle(),articleRequest.getContent());

        return  RsData.of(rsData.getResultCode(), rsData.getMsg(), new ArticleResponse.createArticleResponse(rsData.getData()));
    }


}
