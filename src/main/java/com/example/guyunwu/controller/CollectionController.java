package com.example.guyunwu.controller;

import com.example.guyunwu.model.response.Result;
import com.example.guyunwu.service.CollectionService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/collection")
public class CollectionController {

    @Autowired
    private CollectionService collectionService;

    @ApiOperation("每日一句")
    @GetMapping(value = "/dailySentence")
    public Result dailySentence() {

        return Result.ok();
    }

    ////////////////////////////////////////////////////////////////////////////////////////

    @ApiOperation("我的图书")
    @GetMapping(value = "/book/my")
    public Result getMyBooks() {

        return Result.ok();
    }

    @ApiOperation("添加书籍")
    @GetMapping(value = "/book/collect")
    public Result collectBook(@PathVariable Integer bookId) {

        return Result.ok();
    }

    @ApiOperation("取消收藏")
    @DeleteMapping(value = "/book/cancel")
    public Result cancelBook(@PathVariable Integer bookId) {

        return Result.ok();
    }

    ////////////////////////////////////////////////////////////////////////////////////////

    @ApiOperation("我的收藏实词")
    @GetMapping(value = "/word/my")
    public Result getMyWords() {

        return Result.ok();
    }

    @ApiOperation("收藏实词")
    @GetMapping(value = "/word/collect")
    public Result collectWord(@PathVariable Integer wordId) {

        return Result.ok();
    }

    @ApiOperation("取消收藏")
    @DeleteMapping(value = "/word/cancel")
    public Result cancelWord(@PathVariable Integer wordId) {

        return Result.ok();
    }

}
