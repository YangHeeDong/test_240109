package com.example.demo_240109.domain.article.entity;

import com.example.demo_240109.domain.member.entity.Member;
import com.example.demo_240109.global.baseEntity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Article extends BaseEntity {

    private String title;
    private String content;

    @ManyToOne
    private Member member;
}
