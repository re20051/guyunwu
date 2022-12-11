package com.example.guyunwu.service;

import com.example.guyunwu.model.entity.Word;

import java.util.Date;
import java.util.List;

public interface LearnService {

    List<Word> getRecord(Long userId, Date date);

    int getTotalLearned(Long userId);

    /**
     * 获得当天学习计划新学实词数量
     * @param scheduleId
     * @return
     */
    int getTodayLeanred(Long scheduleId);
}
