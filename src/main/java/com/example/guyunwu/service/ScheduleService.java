package com.example.guyunwu.service;

import com.example.guyunwu.model.entity.Schedule;
import com.example.guyunwu.model.entity.Word;
import com.example.guyunwu.model.param.AddScheduleParam;
import com.example.guyunwu.model.param.UpdateScheduleParam;

import java.util.List;

public interface ScheduleService {
    void update(UpdateScheduleParam updateScheduleParam, Long userId);

    Schedule add(AddScheduleParam addScheduleParam, Long userId);

    Schedule switchSchedule(Long bookId, Long userId);

    Schedule reset(Long userId);

    Schedule getCurrentSchedule(Long userId);

    /**
     * 某个用户是否收藏了某本书
     * @param bookId
     * @param userId
     * @return
     */
    boolean existsSchedule(Long bookId, Long userId);

    /**
     * 新建一个计划完成记录表
     * @param words
     * @param scheduleId
     */
    void addScheduleRecord(List<Word> words, Long scheduleId);

    /**
     *
     * @param scheduleId
     * @param wordId
     * @return
     */
    int getWordStatus(Long scheduleId, Long wordId);

    List<Schedule> getAllSchedules(Long userId);

    /**
     * 通过计划id获得计划
     * @param scheduleId
     * @param userId
     * @return
     */
    Schedule getScheduleById(Long scheduleId, Long userId);

    /**
     * 获得已学词汇数量
     * @param id
     * @return
     */
    Integer getHasLearned(Long id);

    /**
     * 获得计划总词汇数量
     * @param scheduleId
     * @return
     */
    Integer getAll(Long scheduleId);
}
