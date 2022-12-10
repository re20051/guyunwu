package com.example.guyunwu.controller;

import com.example.guyunwu.model.response.Result;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

//    @ApiOperation("发帖子")
//    @PostMapping(value = "/lz")
//    public Result login(@RequestBody LoginParam loginParam) {
//
//        return Result.ok();
//    }
//
//    @ApiOperation("评论")
//    @PostMapping(value = "/comment")
//    public Result comment(@RequestBody LoginParam loginParam) {
//
//        return Result.ok();
//    }
}
