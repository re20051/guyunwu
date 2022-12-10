package com.example.guyunwu.controller;

import com.example.guyunwu.model.response.Result;
import com.example.guyunwu.service.LearnService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/learn")
public class LearnController {

    @Autowired
    private LearnService learnService;

    @ApiOperation("每日一句")
    @GetMapping(value = "/dailySentence")
    public Result dailySentence() {

        return Result.ok();
    }

    @ApiOperation("我的图书")
    @GetMapping(value = "/mybook")
    public Result getMyBook() {

        return Result.ok();
    }

    @ApiOperation("添加书籍")
    @GetMapping(value = "/addBook")
    public Result addBook(@PathVariable Integer bookId) {

        return Result.ok();
    }


}
