package com.example.guyunwu.service.impl;


import com.example.guyunwu.model.entity.ClockRecord;
import com.example.guyunwu.repository.BaseRepository;
import com.example.guyunwu.repository.ClockRepository;
import com.example.guyunwu.service.ClockService;
import com.example.guyunwu.service.base.AbstractCrudService;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.util.Date;

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
        DateFormat dateInstance = DateFormat.getDateInstance();
        ClockRecord clockRecord = new ClockRecord();
        clockRecord.setUserId(userId);
        clockRecord.setDate(new Date());
        clockRecord.setClocked(true);
        clockRepository.save(clockRecord);
    }
}
