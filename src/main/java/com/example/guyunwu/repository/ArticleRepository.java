package com.example.guyunwu.repository;

import com.example.guyunwu.model.entity.Article;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ArticleRepository extends BaseRepository<Article, Long>, JpaSpecificationExecutor<Article> {

    @Transactional
    @Query("update article a set a.reads = a.reads + 1 where a.id = ?1")
    void updateReadsById(Long id);

}
