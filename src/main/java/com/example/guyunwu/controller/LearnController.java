package com.example.guyunwu.controller;

import com.example.guyunwu.model.entity.Word;
import com.example.guyunwu.model.response.Result;
import com.example.guyunwu.service.LearnService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/learn")
public class LearnController {

//    @Autowired
//    private LearnService learnService;

    @ApiOperation("打卡")
    @PutMapping(value = "/cancelIn")
    public Result cancelIn() {

        return Result.ok();
    }

    @ApiOperation("获得某一天的学习记录")
    @GetMapping(value = "/learnRecord")
    public Result getLearnRecord(@PathVariable Date date) {
        Long userId = 4L;
//        List<Word> words = learnService.getRecord(userId, date);
        return Result.ok();
    }

    @ApiOperation("更新学习记录")
    @PostMapping(value = "/update")
    public Result updateLearnRecord() {

        return Result.ok();
    }

    @ApiOperation("打卡天数")
    @GetMapping(value = "/clockDays")
    public Result clockDays() {

        return Result.ok();
    }

    @ApiOperation("学习实词总数")
    @GetMapping(value = "/totalLearned")
    public Result getTotalLearned() {

        return Result.ok();
    }
}
