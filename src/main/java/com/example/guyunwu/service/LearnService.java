package com.example.guyunwu.service;

import com.example.guyunwu.model.entity.Word;
import com.example.guyunwu.model.param.DateParam;

import java.util.Date;
import java.util.List;

public interface LearnService {


    /**
     * 获得一个用户至今所学实词总数
     * @param userId
     * @return
     */
    Integer getTotalLearned(Long userId);

    /**
     * 获得今日已经学习实词数
     * @param scheduleId
     * @return
     */
    int getTodayLearned(Long scheduleId);


    /**
     * 获得计划内若干个实词
     * @param scheduleId
     * @param learn
     * @return
     */
    List<Word> getNewWords(Long scheduleId, int learn);

    /**
     * 获得所需复习实词
     * @param scheduleId
     * @return
     */
    List<Word> getReviewWords(Long scheduleId);

    /**
     * 获得所需复习实词个数
     * @param scheduleId
     * @return
     */
    Integer getReviewWordCount(Long scheduleId);

    /**
     * 获得某一天所学单词
     * @param userId
     * @param date
     * @return
     */
    List<Word> getLearnedWordsByDay(Long userId, Date date);

    /**
     * 更新计划中实词的状态，可能是新学也可能是复习也可能不存在
     * @param userId
     * @param wordId
     * @param scheduleId
     */
    void updateStatus(Long userId, Long wordId, Long scheduleId);
}
