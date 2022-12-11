package com.example.guyunwu.controller;

import com.example.guyunwu.model.entity.Schedule;
import com.example.guyunwu.model.param.AddScheduleParam;
import com.example.guyunwu.model.param.UpdateScheduleParam;
import com.example.guyunwu.model.response.Result;
import com.example.guyunwu.service.ScheduleService;
import com.example.guyunwu.utils.SecurityUtil;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/schedule")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @ApiOperation("修改计划，只允许更新当前使用中的计划")
    @PutMapping(value = "/update")
    public Result updateSchedule(@RequestBody @Validated UpdateScheduleParam updateScheduleParam) {
        Long userId = SecurityUtil.getCurrentUserId();
        scheduleService.update(updateScheduleParam, userId);
        return Result.ok();
    }

    @ApiOperation("添加计划")
    @PostMapping(value = "/add")
    public Result addSchedule(@RequestBody @Validated AddScheduleParam addScheduleParam) {
        Long userId = SecurityUtil.getCurrentUserId();
        scheduleService.add(addScheduleParam, userId);
        return Result.ok();
    }

    @ApiOperation("切换计划")
    @PostMapping("/switch")
    public Result switchSchedule(@RequestBody @Validated AddScheduleParam addScheduleParam) {
        Long userId = SecurityUtil.getCurrentUserId();
        scheduleService.switchSchedule(addScheduleParam.getBookId(), userId);
        return Result.ok("ok");
    }

    @ApiOperation("重置计划")
    @PutMapping(value = "/reset")
    public Result resetSchedule() {
        Long userId = SecurityUtil.getCurrentUserId();
        Schedule newSchedule = scheduleService.reset(userId);
        return Result.ok("ok", newSchedule);
    }

}
