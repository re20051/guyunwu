package com.example.guyunwu.service;

import com.example.guyunwu.model.entity.Schedule;
import com.example.guyunwu.model.param.AddScheduleParam;
import com.example.guyunwu.model.param.UpdateScheduleParam;

public interface ScheduleService {
    void update(UpdateScheduleParam updateScheduleParam, Long userId);

    void add(AddScheduleParam addScheduleParam, Long userId);

    void switchSchedule(Long bookId, Long userId);

    Schedule reset(Long userId);

    Schedule getCurrentSchedule(Long userId);
}
