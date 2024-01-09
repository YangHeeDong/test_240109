package com.example.demo_240109.domain.article.repository;

import com.example.demo_240109.domain.article.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    List<Article> findAllByOrderByIdDesc();
}
