package com.example.guyunwu.repository;

import com.example.guyunwu.model.entity.Article;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.net.URLConnection;
import java.util.List;

@Repository
public interface ArticleRepository extends BaseRepository<Article, Long>, JpaSpecificationExecutor<Article> {

    @Transactional
    @Modifying
    @Query("update article a set a.reads = a.reads + 1 where a.id = ?1")
    void updateReadsById(Long id);


    @Query(value = "select count(*) from article_like WHERE user_id=?1 AND article_id=?2", nativeQuery = true)
    int getArticleLike(Long userId, Long articleId);

    @Query(value = "select count(*) from article_like WHERE article_id=?1", nativeQuery = true)
    long countArticleLikesById(Long articleId);

    @Transactional
    @Modifying
    @Query(value = "insert into article_like (user_id, article_id) values (?1, ?2)", nativeQuery = true)
    int insertArticleLike(Long userId, Long articleId);

    @Transactional
    @Modifying
    @Query(value = "delete from article_like where user_id=?1 and article_id=?2", nativeQuery = true)
    int deleteArticleLike(Long userId, Long articleId);
}
