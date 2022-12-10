package com.example.guyunwu.controller;

import com.example.guyunwu.model.response.Result;
import com.example.guyunwu.service.ScheduleService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @ApiOperation("修改计划")
    @PutMapping(value = "update")
    public Result updateSchedule() {

        return Result.ok();
    }

    @ApiOperation("添加计划")
    @PostMapping(value = "/add")
    public Result addSchedule() {

        return Result.ok();
    }

    @ApiOperation("切换计划")
    @GetMapping(value = "/switch")
    public Result switchSchedule(@PathVariable Integer scheduleId) {

        return Result.ok();
    }

    @ApiOperation("删除计划")
    @DeleteMapping(value = "/delete")
    public Result deleteSchedule(@PathVariable Integer scheduleId) {

        return Result.ok();
    }

    @ApiOperation("重置计划")
    @GetMapping(value = "/reset")
    public Result resetSchedule() {

        return Result.ok();
    }

}
