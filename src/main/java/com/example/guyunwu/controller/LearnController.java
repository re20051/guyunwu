package com.example.guyunwu.controller;

import com.example.guyunwu.model.dto.*;
import com.example.guyunwu.model.entity.Author;
import com.example.guyunwu.model.entity.Book;
import com.example.guyunwu.model.entity.Schedule;
import com.example.guyunwu.model.entity.Word;
import com.example.guyunwu.model.param.DateParam;
import com.example.guyunwu.model.response.Result;
import com.example.guyunwu.service.ClockService;
import com.example.guyunwu.service.CollectionService;
import com.example.guyunwu.service.LearnService;
import com.example.guyunwu.service.ScheduleService;
import com.example.guyunwu.utils.SecurityUtil;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/learn")
@Slf4j
public class LearnController {

    private final LearnService learnService;

    private final ClockService clockService;

    private final ScheduleService scheduleService;

    private final CollectionService collectionService;

    @ApiOperation("打卡")
    @PutMapping(value = "/clockIn")
    public Result<Object> clockIn() {
        Long userId = SecurityUtil.getCurrentUserId();
        // 获得当前学习计划
        Schedule schedule = scheduleService.getCurrentSchedule(userId);
        // 获得今日计划完成情况
        int todayLearned = learnService.getTodayLearned(schedule.getId());
        int scheduleRemained = scheduleService.getAll(schedule.getId()) - scheduleService.getHasLearned(schedule.getId()); // 计划剩余实词数

        if(todayLearned >= schedule.getWordsPerDay() || scheduleRemained == 0) {
            clockService.clock(userId);
            return Result.ok();
        }
        return Result.error("还需要学习");
    }

    @ApiOperation("打卡天数")
    @GetMapping(value = "/clockDays")
    public Result<Integer> clockDays() {
        Long userId = SecurityUtil.getCurrentUserId();
        int result = clockService.getClockDays(userId);
        return Result.ok("ok", result);
    }

    @ApiOperation("获得某月的打卡记录")
    @PostMapping(value = "/monthRecord")
    public Result<List<Integer>> getMonthRecord(@RequestBody @Validated DateParam dateParam) {
        Long userId = SecurityUtil.getCurrentUserId();
        List<Integer> dates = clockService.getMonthRecord(userId, dateParam);
        return Result.ok("ok", dates);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////

    @ApiOperation("获得学习总实词数")
    @GetMapping("/totalLearnedWords")
    public Result<Integer> totalLearnedWords() {
        Long userId = SecurityUtil.getCurrentUserId();
        Integer total = learnService.getTotalLearned(userId);
        return Result.ok("ok", total);
    }

    @ApiOperation("获得某一天的学习记录")
    @PostMapping(value = "/learnRecord")
    public Result<DayRecordDTO> getLearnRecord(@RequestBody @Validated Date date) {
        Long userId = SecurityUtil.getCurrentUserId();
        DayRecordDTO dayRecordDTO = new DayRecordDTO();

        List<Word> words = learnService.getLearnedWordsByDay(userId, date);
        List<WordDTO> wordDTOS = new ArrayList<>();
        words.forEach(word -> {
            WordDTO wordDTO = new WordDTO();
            BeanUtils.copyProperties(word, wordDTO);
            Book book = collectionService.getBookById(word.getBookId());

            BookDTO bookDTO = new BookDTO();
            BeanUtils.copyProperties(book, bookDTO);
            wordDTO.setBook(bookDTO);
            wordDTOS.add(wordDTO);
        });
        dayRecordDTO.setWords(wordDTOS);

        // 判断是否打卡成功
        Boolean isClocked = clockService.isClocked(userId, date);
        dayRecordDTO.setIsClocked(isClocked);
        return Result.ok("ok", dayRecordDTO);
    }

    @ApiOperation("更新学习记录")
    @PostMapping(value = "/learn")
    public Result<Object> updateLearnRecord(@RequestBody @Validated Long wordId) {
        Long userId = SecurityUtil.getCurrentUserId();
        // 获得用户当前计划
        Schedule schedule = scheduleService.getCurrentSchedule(userId);
        learnService.updateStatus(userId, wordId, schedule.getId());
        return Result.ok();
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////

    @ApiOperation("获得今日计划")
    @GetMapping(value = "/todaySchedule")
    public Result<TodayDTO> getTodaySchedule() {
        Long userId = SecurityUtil.getCurrentUserId();
        // 获得用户当前计划
        Schedule schedule = scheduleService.getCurrentSchedule(userId);
        TodayDTO todayDTO = new TodayDTO();
        // 今日复习数
        todayDTO.setReview(learnService.getReviewWordCount(schedule.getId()));
        // 今日已学实词个数
        int hasLearned = learnService.getTodayLearned(schedule.getId());
        int tobeLearned = schedule.getWordsPerDay() >= hasLearned ? schedule.getWordsPerDay() - hasLearned : 0; // 每日计划剩余实词数
        int scheduleRemained = scheduleService.getAll(schedule.getId()) - scheduleService.getHasLearned(schedule.getId()); // 计划剩余实词数
        int learn = Math.min(tobeLearned, scheduleRemained);
        todayDTO.setHasLearned(hasLearned);
        todayDTO.setLearn(learn);

        return Result.ok("ok", todayDTO);
    }

    @ApiOperation("获得今日需要新学和复习的实词")
    @GetMapping(value = "/todayWords")
    public Result<LRDTO> getTodayWords() {
        Long userId = SecurityUtil.getCurrentUserId();
        LRDTO lrdto = new LRDTO();
        // 获得用户当前计划
        Schedule schedule = scheduleService.getCurrentSchedule(userId);
        List<Word> words = new ArrayList<>();

        // 获得用户今日所需复习实词
        List<Word> reviewWords = learnService.getReviewWords(schedule.getId());

        int hasLearned = learnService.getTodayLearned(schedule.getId());
        int tobeLearned = schedule.getWordsPerDay() >= hasLearned ? schedule.getWordsPerDay() - hasLearned : 0; // 每日计划剩余实词数
        int scheduleRemained = scheduleService.getAll(schedule.getId()) - scheduleService.getHasLearned(schedule.getId()); // 计划剩余实词数
        int learn = Math.min(tobeLearned, scheduleRemained);

        // 获得用户今日所需新学实词
        List<Word> newWords = learnService.getNewWords(schedule.getId(), learn);
        reviewWords.addAll(newWords);
        lrdto.setWords(reviewWords);

        Book book = collectionService.getBookById(schedule.getBookId());
        BookDTO bookDTO = new BookDTO();
        BeanUtils.copyProperties(book, bookDTO);
        lrdto.setBook(bookDTO);

        return Result.ok("ok", lrdto);
    }
}
