package com.example.guyunwu.service;

import com.example.guyunwu.model.entity.Schedule;
import com.example.guyunwu.model.entity.Word;
import com.example.guyunwu.model.param.AddScheduleParam;
import com.example.guyunwu.model.param.UpdateScheduleParam;

import java.util.List;

public interface ScheduleService {

    /**
     * 更新计划
     * @param updateScheduleParam
     * @param userId
     */
    void update(UpdateScheduleParam updateScheduleParam, Long userId);

    /**
     * 添加计划
     * @param addScheduleParam
     * @param userId
     * @return
     */
    Schedule add(AddScheduleParam addScheduleParam, Long userId);

    /**
     * 切换书本(计划)
     * @param bookId
     * @param userId
     * @return
     */
    Schedule switchSchedule(Long bookId, Long userId);

    /**
     * 重置计划
     * @param userId
     * @return
     */
    Schedule reset(Long userId);

    /**
     * 获得用户当前计划
     * @param userId
     * @return
     */
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
     *获得实词状态
     * @param scheduleId
     * @param wordId
     * @return
     */
    int getWordStatus(Long scheduleId, Long wordId);

    /**
     * 获得一个用户所有计划
     * @param userId
     * @return
     */
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
     * @param scheduleId
     * @return
     */
    Integer getHasLearned(Long scheduleId);

    /**
     * 获得计划总词汇数量
     * @param scheduleId
     * @return
     */
    Integer getAll(Long scheduleId);
}
