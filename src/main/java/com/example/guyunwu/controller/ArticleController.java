package com.example.guyunwu.controller;

import com.example.guyunwu.model.dto.UserDTO;
import com.example.guyunwu.model.entity.Article;
import com.example.guyunwu.model.entity.User;
import com.example.guyunwu.model.param.AddArticleParam;
import com.example.guyunwu.model.param.ListArticleParam;
import com.example.guyunwu.model.dto.ArticleDTO;
import com.example.guyunwu.model.response.Result;
import com.example.guyunwu.repository.ArticleRepository;
import com.example.guyunwu.repository.UserRepository;
import com.example.guyunwu.utils.SecurityUtil;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
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
public class ArticleController {

    private final ArticleRepository articleRepository;

    private final UserRepository userRepository;

    private ArticleDTO toDto(Article article){
        ArticleDTO articleDTO = new ArticleDTO();
        BeanUtils.copyProperties(article, articleDTO);
        userRepository.findById(article.getUserId()).ifPresent(user1 -> {
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
        return Result.ok("ok", toDto(article));
    }

    @ApiOperation("获取帖子")
    @GetMapping("/list")
    public Result<List<ArticleDTO>> listArticle(ListArticleParam param){
        Specification<Article> specification = (root, criteriaQuery, criteriaBuilder) -> {
            return criteriaBuilder.and(
                    criteriaBuilder.equal(root.get("category"), param.getCategory())
            );
        };
        PageRequest pageRequest = PageRequest.of(param.getPage(), param.getSize());

        List<Article> articles = articleRepository.findAll(specification, pageRequest).getContent();
        List<ArticleDTO> res = articles.stream().map(this::toDto).collect(Collectors.toList());

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
    public Result getLikeArticle(@PathVariable Long id) {

        return Result.ok();
    }

    @ApiOperation("点赞帖子")
    @PostMapping("/like/{id:\\d+}")
    public Result doLikeArticle(@PathVariable Long id) {

        return Result.ok();
    }
}
