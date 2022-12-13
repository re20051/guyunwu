package com.example.guyunwu.service.impl;

import com.example.guyunwu.model.entity.ScheduleRecord;
import com.example.guyunwu.model.entity.Word;
import com.example.guyunwu.model.param.DateParam;
import com.example.guyunwu.repository.LearnRecordRepository;
import com.example.guyunwu.repository.ScheduleRecordRepository;
import com.example.guyunwu.service.LearnService;
import com.example.guyunwu.service.base.AbstractCrudService;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Service
public class LearnServiceImpl extends AbstractCrudService<ScheduleRecord, Long> implements LearnService {

    protected LearnServiceImpl(ScheduleRecordRepository scheduleRecordRepository,
                               LearnRecordRepository learnRecordRepository) {
        super(scheduleRecordRepository);
        this.scheduleRecordRepository = scheduleRecordRepository;
        this.learnRecordRepository = learnRecordRepository;
    }


    private final ScheduleRecordRepository scheduleRecordRepository;

    private final LearnRecordRepository learnRecordRepository;

    /**
     * 获得某一天所学单词
     * @param userId
     * @param dateParam
     * @return
     */
    @Override
    public List<Long> getLearnRecord(Long userId, DateParam dateParam) {
        List<Long> wordIds = learnRecordRepository.getLearnRecord(userId, dateParam.getYear(), dateParam.getMonth(), dateParam.getDay());
        return null;
    }

    /**
     * 获得一个用户至今所学实词总数
     * @param userId
     * @return
     */
    @Override
    public int getTotalLearned(Long userId) {
        return learnRecordRepository.totalLearned(userId);
    }

    @Override
    public int getTodayLearned(Long scheduleId) {
        DateFormat dateInstance = new SimpleDateFormat("yyyy-MM-dd");
        return scheduleRecordRepository.countByScheduleIdAndStatusAndDate(scheduleId, 1, dateInstance.format(new Date()));
    }

    @Override
    public List<Word> getNewWords(Long scheduleId, int learn) {
        System.out.println(scheduleRecordRepository.getNewWords(scheduleId, learn).toString());
        return null;
    }

    @Override
    public List<Word> getReviewWords(Long scheduleId) {
        System.out.println(scheduleRecordRepository.getReviewWords(scheduleId).toString());
        return null;
    }

    @Override
    public Integer getReviewWordCount(Long scheduleId) {
        return scheduleRecordRepository.getReviewWordCount(scheduleId);
    }
}
