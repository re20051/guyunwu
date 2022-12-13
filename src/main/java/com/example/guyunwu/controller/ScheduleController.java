package com.example.guyunwu.controller;

import com.example.guyunwu.model.dto.BookDTO;
import com.example.guyunwu.model.dto.ProgressDTO;
import com.example.guyunwu.model.dto.ScheduleDTO;
import com.example.guyunwu.model.dto.WordDTO;
import com.example.guyunwu.model.entity.Book;
import com.example.guyunwu.model.entity.Schedule;
import com.example.guyunwu.model.entity.Word;
import com.example.guyunwu.model.param.AddScheduleParam;
import com.example.guyunwu.model.param.UpdateScheduleParam;
import com.example.guyunwu.model.response.Result;
import com.example.guyunwu.service.CollectionService;
import com.example.guyunwu.service.ScheduleService;
import com.example.guyunwu.utils.SecurityUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/schedule")
@Slf4j
public class ScheduleController {

    private final ScheduleService scheduleService;

    private final CollectionService collectionService;

    @ApiOperation("修改计划，只允许更新当前使用中的计划")
    @PutMapping(value = "/update")
    public Result<Object> updateSchedule(@RequestBody @Validated UpdateScheduleParam updateScheduleParam) {
        Long userId = SecurityUtil.getCurrentUserId();
        scheduleService.update(updateScheduleParam, userId);
        return Result.ok();
    }

    @ApiOperation(value = "添加计划")
    @PostMapping(value = "/add")
    @ApiResponses(@ApiResponse(code = 200, message = "ok"))
    public Result<Object> addSchedule(@RequestBody @Validated AddScheduleParam addScheduleParam) {
        Long userId = SecurityUtil.getCurrentUserId();
        // 保存计划
        Schedule schedule = scheduleService.add(addScheduleParam, userId);
        // 获得题库
        List<Word> words = collectionService.getWordsByBookId(addScheduleParam.getBookId());
        // 新建一个学习计划进度表
        scheduleService.addScheduleRecord(words, schedule.getId());

        return Result.ok();
    }

    @ApiOperation(value = "切换计划")
    @PostMapping("/switch")
    @ApiResponses(@ApiResponse(code = 200, message = "ok"))
    public Result<ScheduleDTO> switchSchedule(@RequestBody @Validated AddScheduleParam addScheduleParam) {
        Long userId = SecurityUtil.getCurrentUserId();
        Schedule schedule = scheduleService.switchSchedule(addScheduleParam.getBookId(), userId);

        ScheduleDTO scheduleDTO = new ScheduleDTO();
        BeanUtils.copyProperties(schedule, scheduleDTO);
        return Result.ok("ok", scheduleDTO);
    }

    @ApiOperation("重置计划")
    @PutMapping(value = "/reset")
    @ApiResponses(@ApiResponse(code = 200, message = "ok"))
    public Result<ScheduleDTO> resetSchedule() {
        Long userId = SecurityUtil.getCurrentUserId();
        Schedule newSchedule = scheduleService.reset(userId);
        // 获得题库
        List<Word> words = collectionService.getWordsByBookId(newSchedule.getBookId());
        // 新建一个学习计划进度表
        scheduleService.addScheduleRecord(words, newSchedule.getId());
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        BeanUtils.copyProperties(newSchedule, scheduleDTO);

        return Result.ok("ok", scheduleDTO);
    }

    /**
     * 登陆时获得某个用户的所有计划信息以及计划的每个题目信息
     * @return
     */
    @ApiOperation("登陆时获得当前计划")
    @GetMapping(value = "/currentSchedule")
    @ApiResponses(@ApiResponse(code = 200, message = "ok"))
    public Result<ScheduleDTO> getCurrentSchedule() {
        Long userId = SecurityUtil.getCurrentUserId();
        // 获得当前计划
        Schedule schedule = scheduleService.getCurrentSchedule(userId);
        if(schedule == null) {
            return Result.ok("ok", null);
        }
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        BeanUtils.copyProperties(schedule, scheduleDTO);

        return Result.ok("ok", scheduleDTO);
    }

    @ApiOperation("获得计划完成情况")
    @GetMapping(value = "/progress/{scheduleId:\\d+}")
    @ApiResponses(@ApiResponse(code = 200, message = "ok"))
    public Result<ProgressDTO> getScheduleProgress(@PathVariable Long scheduleId) {
        Long userId = SecurityUtil.getCurrentUserId();
        // 获得该计划
        Schedule schedule = scheduleService.getScheduleById(scheduleId, userId);

        // 获得书本
        Book book = collectionService.getBookById(1L);
        BookDTO bookDTO = new BookDTO();
        BeanUtils.copyProperties(book, bookDTO);
        // 获得已学词汇数量
        Integer learned = scheduleService.getHasLearned(schedule.getId());
        // 获得总词汇数量
        Integer all = scheduleService.getAll(schedule.getId());

        ProgressDTO progressDTO = new ProgressDTO(bookDTO, learned, all);
        return Result.ok("ok", progressDTO);
    }
}
