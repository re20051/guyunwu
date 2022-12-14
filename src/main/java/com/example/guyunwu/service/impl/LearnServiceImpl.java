package com.example.guyunwu.service.impl;

import com.example.guyunwu.exception.BadRequestException;
import com.example.guyunwu.model.entity.LearnRecord;
import com.example.guyunwu.model.entity.ScheduleRecord;
import com.example.guyunwu.model.entity.Word;
import com.example.guyunwu.model.param.DateParam;
import com.example.guyunwu.repository.LearnRecordRepository;
import com.example.guyunwu.repository.ScheduleRecordRepository;
import com.example.guyunwu.repository.WordRepository;
import com.example.guyunwu.service.LearnService;
import com.example.guyunwu.service.base.AbstractCrudService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class LearnServiceImpl extends AbstractCrudService<ScheduleRecord, Long> implements LearnService {

    protected LearnServiceImpl(ScheduleRecordRepository scheduleRecordRepository,
                               LearnRecordRepository learnRecordRepository,
                               WordRepository wordRepository) {
        super(scheduleRecordRepository);
        this.scheduleRecordRepository = scheduleRecordRepository;
        this.learnRecordRepository = learnRecordRepository;
        this.wordRepository = wordRepository;
    }


    private final ScheduleRecordRepository scheduleRecordRepository;

    private final LearnRecordRepository learnRecordRepository;

    private final WordRepository wordRepository;

    @Override
    public Integer getTotalLearned(Long userId) {
        return learnRecordRepository.totalLearned(userId);
    }

    @Override
    public int getTodayLearned(Long scheduleId) {
        DateFormat dateInstance = new SimpleDateFormat("yyyy-MM-dd");
        return scheduleRecordRepository.countByScheduleIdAndStatusAndDate(scheduleId, 1, dateInstance.format(new Date()));
    }

    @Override
    public List<Word> getNewWords(Long scheduleId, int learn) {
        return wordRepository.getNewWords(scheduleId,learn);
    }

    @Override
    public List<Word> getReviewWords(Long scheduleId) {
        DateFormat dateInstance = new SimpleDateFormat("yyyy-MM-dd");
        return wordRepository.getReviewWords(scheduleId, dateInstance.format(new Date()));
    }

    @Override
    public Integer getReviewWordCount(Long scheduleId) {
        DateFormat dateInstance = new SimpleDateFormat("yyyy-MM-dd");
        return scheduleRecordRepository.getReviewWordCount(scheduleId, dateInstance.format(new Date()));
    }

    @Override
    public List<Word> getLearnedWordsByDay(Long userId, Date date) {
        DateFormat dateInstance = new SimpleDateFormat("yyyy-MM-dd");
        return wordRepository.getLearnedWordsOfOneDay(userId, dateInstance.format(date));
    }

    @Override
    @Transactional
    public void updateStatus(Long userId, Long wordId, Long scheduleId) {
        ScheduleRecord scheduleRecord = scheduleRecordRepository.findByWordIdAndScheduleId(wordId, scheduleId);
        DateFormat dateInstance = new SimpleDateFormat("yyyy-MM-dd");
        if(scheduleRecord == null) {
            throw new RuntimeException("该单词不在当前计划中");
        }
        Integer status = scheduleRecord.getStatus();
        // 复习或再学习
        if(status == 1 || status == 2) {
            scheduleRecordRepository.updateStatus(scheduleRecord.getId(), 2);
        }
        // 如果是新学
        else if(status == 0) {
            scheduleRecord.setStatus(1);
            scheduleRecord.setDate(dateInstance.format(new Date()));
            scheduleRecordRepository.save(scheduleRecord);
            // 插入一条学习记录
            LearnRecord learnRecord = new LearnRecord();
            learnRecord.setUserId(userId);
            learnRecord.setWordId(wordId);
            learnRecord.setDate(dateInstance.format(new Date()));
            learnRecordRepository.save(learnRecord);
        }
    }
}
