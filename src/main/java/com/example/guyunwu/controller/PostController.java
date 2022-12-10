package com.example.guyunwu.controller;

import com.example.guyunwu.model.param.AddArticleParam;
import com.example.guyunwu.model.param.AddCommentParam;
import com.example.guyunwu.model.response.Result;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    @ApiOperation("发帖子")
    @PostMapping(value = "/article")
    public Result login(@RequestBody @Validated AddArticleParam addArticleParam) {

        return Result.ok();
    }

    @ApiOperation("评论")
    @PostMapping(value = "/comment")
    public Result comment(@RequestBody @Validated AddCommentParam addCommentParam) {

        return Result.ok();
    }

    @ApiOperation("点赞评论")
    @GetMapping(value = "/doLikeComment")
    public Result doLikeComment() {

        return Result.ok();
    }

    @ApiOperation("点赞帖子")
    @GetMapping(value = "/doLikeArticle")
    public Result doLikeArticle() {

        return Result.ok();
    }
}
