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

    /**
     * 获得某个用户待复习实词数
     * @param scheduleId
     * @return
     */
    @Override
    public int getToBeReviewed(Long scheduleId) {
        DateFormat dateInstance = DateFormat.getDateInstance();
        Date date = new Date(); //取时间
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -1);//把日期往后增加一天.整数往后推,负数往前移动
        date = calendar.getTime(); //这个时间就是日期往后推一天的结果
        return scheduleRecordRepository.countByScheduleIdAndStatusAndDate(scheduleId, 1, dateInstance.format(date));
    }

    @Override
    public int getTodayLearned(Long scheduleId) {
        DateFormat dateInstance = DateFormat.getDateInstance();
        return scheduleRecordRepository.countByScheduleIdAndStatusAndDate(scheduleId, 1, dateInstance.format(new Date()));
    }



    @Override
    public List<Long> getWords(Long scheduleId, int learn) {
        return scheduleRecordRepository.getWords(scheduleId, learn);
    }

    @Override
    public List<Word> getReviewWords(Long scheduleId) {
        return scheduleRecordRepository.getReviewWords(scheduleId);
    }

    @Override
    public Integer getReviewWordCount(Long scheduleId) {
        return scheduleRecordRepository.getReviewWordCount(scheduleId);
    }
}
