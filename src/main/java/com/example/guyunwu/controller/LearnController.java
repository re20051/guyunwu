package com.example.guyunwu.controller;

import com.example.guyunwu.model.dto.BookDTO;
import com.example.guyunwu.model.dto.TodayDTO;
import com.example.guyunwu.model.dto.WordDTO;
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
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private LearnService learnService;

    @Autowired
    private ClockService clockService;

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private CollectionService collectionService;

    @ApiOperation("打卡")
    @PutMapping(value = "/cancelIn")
    public Result<Object> cancelIn() {
        Long userId = 4L;
        // 获得当前学习计划
        Schedule schedule = scheduleService.getCurrentSchedule(userId);
        // 获得今日计划完成情况
        int todayLearned = learnService.getTodayLearned(schedule.getId());

        if(todayLearned >= schedule.getWordsPerDay()) {
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
    public Result<List<Date>> getMonthRecord(@RequestBody @Validated DateParam dateParam) {
        Long userId = SecurityUtil.getCurrentUserId();
        List<Date> dates = clockService.getMonthRecord(userId, dateParam.getYear(), dateParam.getMonth());
        return Result.ok();
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////

    @ApiOperation("获得某一天的学习记录")
    @PostMapping(value = "/learnRecord")
    public Result<List<WordDTO>> getLearnRecord(@RequestBody @Validated DateParam dateParam) {
        Long userId = SecurityUtil.getCurrentUserId();
        List<Long> wordIds = learnService.getLearnRecord(userId, dateParam);
        List<WordDTO> words = new ArrayList<>();

        wordIds.forEach(id -> {
            Word word = collectionService.getWordById(id);
            WordDTO wordDTO = new WordDTO();
            BeanUtils.copyProperties(word, wordDTO);

            // 设置书本
            Book book = collectionService.getBookById(word.getBookId());
            BookDTO bookDTO = new BookDTO();
            BeanUtils.copyProperties(book, bookDTO);
            wordDTO.setBook(bookDTO);
            words.add(wordDTO);
        });
        return Result.ok("ok", words);
    }

    @ApiOperation("更新学习记录")
    @PostMapping(value = "/update")
    public Result<Object> updateLearnRecord() {

        return Result.ok();
    }

    @ApiOperation("学习实词总数")
    @GetMapping(value = "/totalLearned")
    public Result getTotalLearned() {
        Long userId = SecurityUtil.getCurrentUserId();
        int result = learnService.getTotalLearned(userId);
        return Result.ok("ok", result);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////

//    @ApiOperation("获得今日计划")
//    @GetMapping(value = "/todaySchedule")
//    public Result<TodayDTO> getTodaySchedule() {
//        Long userId = SecurityUtil.getCurrentUserId();
//        // 获得用户当前计划
//        Schedule schedule = scheduleService.getCurrentSchedule(userId);
//        TodayDTO todayDTO = new TodayDTO();
//        List<WordDTO> words = new ArrayList<>();
//
//        // 获得用户今日所需复习实词
//        List<Long> reviewWordIds = learnService.getReviewWordIds(schedule.getId());
//        reviewWordIds.forEach(id -> {
//            // 从题库中查询单词
//            Word word = collectionService.getWordById(id);
//            WordDTO wordDTO = new WordDTO();
//            BeanUtils.copyProperties(word, wordDTO);
//            wordDTO.setStatus(1);
//            words.add(wordDTO);
//        });
//        List<Word> reviews = learnService.getReviewWords(schedule.getId());
//        words.addAll(reviews);
//        todayDTO.setReview(words.size());
//
//        int hasLearned = learnService.getTodayLearned(schedule.getId()); // 今日已学实词个数
//        int tobeLearned = schedule.getWordsPerDay() - hasLearned; // 每日计划剩余实词数
//        int scheduleRemained = scheduleService.getAll(schedule.getId()) - scheduleService.getHasLearned(schedule.getId()); // 计划剩余实词数
//        int learn = Math.min(tobeLearned, scheduleRemained);
//        todayDTO.setLearn(learn);
//
//        List<Long> learnWordIds = learnService.getWords(schedule.getId(), learn);
//        learnWordIds.forEach(id -> {
//            // 从题库中查询单词
//            Word word = collectionService.getWordById(id);
//            WordDTO wordDTO = new WordDTO();
//            BeanUtils.copyProperties(word, wordDTO);
//            wordDTO.setStatus(1);
//            words.add(wordDTO);
//        });
//        todayDTO.setWords(words);
//
//        // 今日已学单词数量
//        todayDTO.setHasLearned(learnService.getTodayLearned(schedule.getId()));
//
//        Book book = collectionService.getBookById(schedule.getBookId());
//        BookDTO bookDTO = new BookDTO();
//        BeanUtils.copyProperties(book, bookDTO);
//        todayDTO.setBook(bookDTO);
//
//        return Result.ok("ok", todayDTO);
//    }

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
        int tobeLearned = schedule.getWordsPerDay() - hasLearned; // 每日计划剩余实词数
        int scheduleRemained = scheduleService.getAll(schedule.getId()) - scheduleService.getHasLearned(schedule.getId()); // 计划剩余实词数
        int learn = Math.min(tobeLearned, scheduleRemained);
        todayDTO.setHasLearned(hasLearned);
        todayDTO.setLearn(learn);

        return Result.ok("ok", todayDTO);
    }
}
