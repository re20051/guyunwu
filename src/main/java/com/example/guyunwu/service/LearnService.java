package com.example.guyunwu.service;

import com.example.guyunwu.model.entity.Word;
import com.example.guyunwu.model.param.DateParam;

import java.util.Date;
import java.util.List;

public interface LearnService {

    /**
     * 获得某一天所学单词
     * @param userId
     * @param dateParam
     * @return
     */
    List<Long> getLearnRecord(Long userId, DateParam dateParam);

    /**
     * 获得一个用户至今所学实词总数
     * @param userId
     * @return
     */
    int getTotalLearned(Long userId);

    /**
     * 获得某个用户待复习实词数
     * @param scheduleId
     * @return
     */
    int getToBeReviewed(Long scheduleId);

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
    List<Long> getWords(Long scheduleId, int learn);

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
}
