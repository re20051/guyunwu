package com.example.guyunwu.service.impl;


import com.example.guyunwu.model.entity.ClockRecord;
import com.example.guyunwu.model.param.DateParam;
import com.example.guyunwu.repository.BaseRepository;
import com.example.guyunwu.repository.ClockRepository;
import com.example.guyunwu.service.ClockService;
import com.example.guyunwu.service.base.AbstractCrudService;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
        return clockRepository.countByUserId(userId);
    }

    @Override
    public void clock(Long userId) {
        ClockRecord clockRecord = new ClockRecord();
        clockRecord.setUserId(userId);
        clockRecord.setDate(new Date());
        clockRecord.setClocked(true);
        try {
            clockRepository.save(clockRecord);
        } catch (Exception e) {
            throw new RuntimeException("今日已经打卡过了");
        }

    }

    @Override
    public List<Integer> getMonthRecord(Long userId, DateParam dateParam) {
        return clockRepository.getMonthRecord(userId, dateParam.getYear(), dateParam.getMonth());
    }

    @Override
    public Boolean isClocked(Long userId, Date date) {
        DateFormat dateInstance = new SimpleDateFormat("yyyy-MM-dd");
        Integer result = clockRepository.isClocked(userId, dateInstance.format(date));
        return result == 1;
    }
}
