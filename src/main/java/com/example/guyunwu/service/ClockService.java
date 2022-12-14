package com.example.guyunwu.service;

import com.example.guyunwu.model.param.DateParam;

import java.util.Date;
import java.util.List;

public interface ClockService {

    /**
     * 获得打卡天数
     * @param userId
     * @return
     */
    int getClockDays(Long userId);

    /**
     * 今日打卡
     * @param userId
     */
    void clock(Long userId);

    /**
     * 获得月打卡记录
     * @param userId
     * @param dateParam
     * @return
     */
    List<Integer> getMonthRecord(Long userId, DateParam dateParam);

    /**
     * 判断用户某天是否已打卡
     * @param userId
     * @param date
     * @return
     */
    Boolean isClocked(Long userId, Date date);
}
