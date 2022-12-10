package com.example.guyunwu.repository;

import com.example.guyunwu.model.entity.Article;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends BaseRepository<Article, Integer>, JpaSpecificationExecutor<Article> {

}
