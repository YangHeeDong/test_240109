package com.example.demo_240109.domain.article.controller;

import com.example.demo_240109.domain.article.entity.Article;
import com.example.demo_240109.domain.article.request.ArticleRequest;
import com.example.demo_240109.domain.article.response.ArticleResponse;
import com.example.demo_240109.domain.article.service.ArticleService;
import com.example.demo_240109.global.rsData.RsData;
import com.example.demo_240109.global.security.SecurityUser;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/articles")
@RequiredArgsConstructor
public class ApiV1ArticleController {

    private final ArticleService articleService;

    // 삭제
    @DeleteMapping("{id}")
    @ResponseBody
    private RsData<String> deleteArticle(@PathVariable long id){

        SecurityUser su = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        RsData<String> rsData = articleService.deleteArticle(id,su.getId());

        return  RsData.of(rsData.getResultCode(), rsData.getMsg(), null);
    }

    // 수정
    @PutMapping("")
    @ResponseBody
    private RsData<ArticleResponse.createArticleResponse> modifyArticle(@Valid @RequestBody ArticleRequest.modifyArticleRequest articleRequest){

        SecurityUser su = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        RsData<Article> rsData = articleService.modifyArticle(articleRequest.getId(),articleRequest.getTitle(),articleRequest.getContent(),su.getId());

        return  RsData.of(rsData.getResultCode(), rsData.getMsg(), new ArticleResponse.createArticleResponse(rsData.getData()));
    }

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

        SecurityUser su = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        RsData<Article> rsData = articleService.createArticle(articleRequest.getTitle(),articleRequest.getContent(),su.getId());

        return  RsData.of(rsData.getResultCode(), rsData.getMsg(), new ArticleResponse.createArticleResponse(rsData.getData()));
    }


}
