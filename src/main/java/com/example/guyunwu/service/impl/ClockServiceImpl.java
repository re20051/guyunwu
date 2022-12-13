package com.example.guyunwu.service.impl;


import com.example.guyunwu.model.entity.ClockRecord;
import com.example.guyunwu.repository.BaseRepository;
import com.example.guyunwu.repository.ClockRepository;
import com.example.guyunwu.service.ClockService;
import com.example.guyunwu.service.base.AbstractCrudService;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ClockServiceImpl extends AbstractCrudService<ClockRecord, Long> implements ClockService {

    protected ClockServiceImpl(ClockRepository clockRepository) {
        super(clockRepository);
        this.clockRepository = clockRepository;
    }

    private final ClockRepository clockRepository;


    @Override
    public int getClockDays(Long userId) {
        return clockRepository.countByUserIdAndClocked(userId, true);
    }

    @Override
    public void clock(Long userId) {
        ClockRecord clockRecord = new ClockRecord();
        clockRecord.setUserId(userId);
        clockRecord.setDate(new Date());
        clockRecord.setClocked(true);
        clockRepository.save(clockRecord);
    }

    @Override
    public List<Date> getMonthRecord(Long userId, Integer year, Integer month) {
        return clockRepository.getMonthRecord(userId, year, month);
    }
}
