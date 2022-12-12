package com.example.guyunwu.controller;

import com.example.guyunwu.model.dto.ScheduleDTO;
import com.example.guyunwu.model.entity.Schedule;
import com.example.guyunwu.model.entity.ScheduleRecord;
import com.example.guyunwu.model.response.Result;
import com.example.guyunwu.service.ClockService;
import com.example.guyunwu.service.LearnService;
import com.example.guyunwu.service.ScheduleService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/collection")
@Slf4j
public class LearnController {

    @Autowired
    private LearnService learnService;

    @Autowired
    private ClockService clockService;

    @Autowired
    private ScheduleService scheduleService;

    @ApiOperation("打卡")
    @PutMapping(value = "/cancelIn")
    public Result cancelIn() {
        Long userId = 4L;
        // 获得当前学习计划
        Schedule schedule = scheduleService.getCurrentSchedule(userId);
        // 获得今日计划完成情况
        int todayLearned = learnService.getTodayLeanred(schedule.getId());

        if(todayLearned >= schedule.getWordsPerDay()) {
            clockService.clock(userId);
            return Result.ok();
        }
        return Result.error("还需要学习");
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
        Long userId = 4L;
        int result = clockService.getClockDays(userId);
        return Result.ok("ok", result);
    }

    @ApiOperation("学习实词总数")
    @GetMapping(value = "/totalLearned")
    public Result getTotalLearned() {
        Long userId = 4L;
        int result = learnService.getTotalLearned(userId);
        return Result.ok("ok", result);
    }
}
