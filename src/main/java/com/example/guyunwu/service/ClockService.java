package com.example.guyunwu.service;

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
     * @param year
     * @param month
     * @return
     */
    List<Date> getMonthRecord(Long userId, Integer year, Integer month);
}
