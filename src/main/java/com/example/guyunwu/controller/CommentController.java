package com.example.guyunwu.controller;

import com.example.guyunwu.model.param.AddCommentParam;
import com.example.guyunwu.model.response.Result;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    @ApiOperation("评论")
    @PostMapping
    public Result comment(@RequestBody @Validated AddCommentParam addCommentParam) {

        return Result.ok();
    }

    @ApiOperation("点赞评论")
    @PostMapping("/like")
    public Result doLikeComment() {

        return Result.ok();
    }
}
