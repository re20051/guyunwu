package com.example.guyunwu.controller;

import com.example.guyunwu.model.dto.UserDTO;
import com.example.guyunwu.model.entity.Article;
import com.example.guyunwu.model.param.AddArticleParam;
import com.example.guyunwu.model.param.ListArticleParam;
import com.example.guyunwu.model.dto.ArticleDTO;
import com.example.guyunwu.model.response.Result;
import com.example.guyunwu.repository.ArticleRepository;
import com.example.guyunwu.repository.UserRepository;
import com.example.guyunwu.utils.SecurityUtil;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/article")
@Slf4j
public class ArticleController {

    private final ArticleRepository articleRepository;

    private final UserRepository userRepository;

    private ArticleDTO toDto(Article article){
        ArticleDTO articleDTO = new ArticleDTO();
        BeanUtils.copyProperties(article, articleDTO);
        articleDTO.setLikes(articleRepository.countArticleLikesById(article.getId()));
        userRepository.findByUserId(article.getUserId()).ifPresent(user1 -> {
            UserDTO userDTO = new UserDTO();
            BeanUtils.copyProperties(user1, userDTO);
            articleDTO.setAuthor(userDTO);
        });
        return articleDTO;
    }

    @ApiOperation("查询文章详情")
    @GetMapping("/{id:\\d+}")
    public Result<ArticleDTO> getArticle(@PathVariable Long id){
        Article article = articleRepository.findById(id).orElse(null);
        if(article == null){
            return Result.error("文章不存在");
        }
        articleRepository.updateReadsById(id);
        return Result.ok("ok", toDto(article));
    }

    @ApiOperation("获取帖子")
    @GetMapping("/list")
    public Result<List<ArticleDTO>> listArticle(ListArticleParam param){
        long l1 = System.currentTimeMillis();
        Specification<Article> specification = (root, criteriaQuery, criteriaBuilder) -> {
            return criteriaBuilder.and(
                    criteriaBuilder.equal(root.get("category"), param.getCategory())
            );
        };
        PageRequest pageRequest = PageRequest.of(param.getPage(), param.getSize());

        List<Article> articles = articleRepository.findAll(specification, pageRequest).getContent();
        long l2 = System.currentTimeMillis();
        System.out.println("time1" + (l2 - l1));
        List<ArticleDTO> res = articles.stream().map(this::toDto).collect(Collectors.toList());
        long l3 = System.currentTimeMillis();
        System.out.println("time2" + (l3 - l2));
        return Result.ok("ok", res);
    }

    @ApiOperation("发帖子")
    @PostMapping
    public Result postArticle(@RequestBody @Validated AddArticleParam addArticleParam) {
        Article article = new Article();
        article.setCoverImage(addArticleParam.getCoverImage());
        article.setTitle(addArticleParam.getTitle());
        article.setSummary(addArticleParam.getSummary());
        article.setContent(addArticleParam.getContent());
        article.setPublishDate(new Date());
        article.setCategory(Article.CATEGORY_EXPLORE);
        article.setReads(1L);

        article.setUserId(SecurityUtil.getCurrentUserId());
        articleRepository.save(article);

        return Result.ok();
    }

    @ApiOperation("获取是否赞过帖子")
    @GetMapping("/like/{id:\\d+}")
    public Result<Boolean> getLikeArticle(@PathVariable Long id) {
        Long userId = SecurityUtil.getCurrentUserId();
        int res = articleRepository.getArticleLike(userId, id);
        return Result.ok("ok", res > 0);
    }

    @ApiOperation("点赞帖子")
    @PostMapping("/like/{id:\\d+}")
    public Result<Boolean> doLikeArticle(@PathVariable Long id) {
        Long userId = SecurityUtil.getCurrentUserId();
        int res = articleRepository.getArticleLike(userId, id);
        if(res > 0){
            articleRepository.deleteArticleLike(userId, id);
        }else{
            articleRepository.insertArticleLike(userId, id);
        }
        return Result.ok("ok", res <= 0);
    }
}
