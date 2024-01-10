package com.example.demo_240109.domain.article.service;

import com.example.demo_240109.domain.article.entity.Article;
import com.example.demo_240109.domain.article.repository.ArticleRepository;
import com.example.demo_240109.domain.member.entity.Member;
import com.example.demo_240109.domain.member.service.MemberService;
import com.example.demo_240109.global.rsData.RsData;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final MemberService memberService;

    @Transactional
    public RsData<Article> createArticle(String title, String content,long memberId) {

        Member member = memberService.findById(memberId).get();

        Article article = Article.builder()
                .title(title)
                .content(content)
                .createDate(LocalDateTime.now())
                .modifyDate(LocalDateTime.now())
                .member(member)
                .build();

        articleRepository.save(article);

        return RsData.of("200","저장되었습니다.",article);
    }

    public List<Article> getArticles() {
        return articleRepository.findAllByOrderByIdDesc();
    }


    public RsData<Article> getArticleById(long id) {

        Optional<Article> article =articleRepository.findById(id);

        if(article.isEmpty()){
            return RsData.of("401","해당 아이디의 게시글이 없다요",null);
        }

        return RsData.of("200","단건조회",article.get());
    }

    @Transactional
    public RsData<Article> modifyArticle(long id, String title, String content, long memberId) {

        Optional<Article> article =articleRepository.findById(id);

        if(article.isEmpty()){
            return RsData.of("401","해당 아이디의 게시글이 없다요",null);
        }

        if(article.get().getMember().getId() != memberId){
            return RsData.of("401","권한이 없어요",null);
        }

        article.get().setTitle(title);
        article.get().setContent(content);
        article.get().setModifyDate(LocalDateTime.now());

        articleRepository.save(article.get());

        return RsData.of("200","수정완료",article.get());
    }


    @Transactional
    public RsData<String> deleteArticle(long articleId, long memberId) {
        Optional<Article> article =articleRepository.findById(articleId);

        if(article.isEmpty()){
            return RsData.of("401","해당 아이디의 게시글이 없다요",null);
        }

        if(article.get().getMember().getId() != memberId){
            return RsData.of("401","권한이 없어요",null);
        }
        
        articleRepository.delete(article.get());
        return RsData.of("200","삭제완료",null);
    }
}
