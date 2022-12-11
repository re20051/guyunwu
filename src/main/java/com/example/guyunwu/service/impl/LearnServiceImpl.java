package com.example.guyunwu.service.impl;

import com.example.guyunwu.model.entity.LearnRecord;
import com.example.guyunwu.model.entity.Word;
import com.example.guyunwu.repository.RecordRepository;
import com.example.guyunwu.service.LearnService;
import com.example.guyunwu.service.base.AbstractCrudService;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

@Service
public class LearnServiceImpl extends AbstractCrudService<LearnRecord, Long> implements LearnService {

    protected LearnServiceImpl(RecordRepository recordRepository) {
        super(recordRepository);
        this.recordRepository = recordRepository;
    }

    private final RecordRepository recordRepository;

    @Override
    public List<Word> getRecord(Long userId, Date date) {
//        List<Word> words = recordRepository.findAllByUserIdAndDate(userId, date);
        return null;
    }

    @Override
    public int getTotalLearned(Long userId) {
        return recordRepository.countTotalLearned(userId);
    }

    @Override
    public int getTodayLeanred(Long scheduleId) {
        DateFormat dateInstance = DateFormat.getDateInstance();
        return recordRepository.countTodayLearned(scheduleId, dateInstance.format(new Date()));
    }
}
