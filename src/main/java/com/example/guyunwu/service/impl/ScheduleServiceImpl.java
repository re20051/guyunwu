package com.example.guyunwu.service.impl;

import com.example.guyunwu.exception.BadRequestException;
import com.example.guyunwu.model.entity.Book;
import com.example.guyunwu.model.entity.Schedule;
import com.example.guyunwu.model.entity.ScheduleRecord;
import com.example.guyunwu.model.entity.Word;
import com.example.guyunwu.model.param.AddScheduleParam;
import com.example.guyunwu.model.param.UpdateScheduleParam;
import com.example.guyunwu.repository.BookRepository;
import com.example.guyunwu.repository.ScheduleRecordRepository;
import com.example.guyunwu.repository.ScheduleRepository;
import com.example.guyunwu.service.ScheduleService;
import com.example.guyunwu.service.base.AbstractCrudService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class ScheduleServiceImpl extends AbstractCrudService<Schedule, Long>  implements ScheduleService {

    protected ScheduleServiceImpl(ScheduleRepository scheduleRepository,
                                  BookRepository bookRepository,
                                  ScheduleRecordRepository scheduleRecordRepository) {
        super(scheduleRepository);
        this.scheduleRepository = scheduleRepository;
        this.bookRepository = bookRepository;
        this.scheduleRecordRepository = scheduleRecordRepository;
    }

    private final ScheduleRepository scheduleRepository;

    private final BookRepository bookRepository;

    private final ScheduleRecordRepository scheduleRecordRepository;

    @Override
    public void update(UpdateScheduleParam updateScheduleParam, Long userId) {
        Schedule schedule = scheduleRepository.findByIdAndUserId(updateScheduleParam.getId(), userId).orElse(null);
        if(schedule == null || schedule.isRemoved()) {
            throw new RuntimeException("计划不存在");
        }
        if(!schedule.isCurrent()) {
            throw new BadRequestException("只能修改当前在学计划");
        }
        schedule.setWordsPerDay(updateScheduleParam.getWordsPerDay());
        // 正式修改计划
        scheduleRepository.save(schedule);
    }

    @Override
    public Schedule add(AddScheduleParam addScheduleParam, Long userId) {
        // 检查书本是否存在
        Book book = bookRepository.findById(addScheduleParam.getBookId()).orElse(null);
        if(book == null) {
            throw new RuntimeException("书本不存在");
        }
        Schedule newSchedule = new Schedule();
        // 判断是否是第一本书
        Schedule oldSchedule = scheduleRepository.findByUserIdAndCurrent(userId, true);
        if(oldSchedule == null) {
            newSchedule.setCurrent(true);
        }
        BeanUtils.copyProperties(addScheduleParam, newSchedule);
        newSchedule.setUserId(userId);
        newSchedule.setWordsPerDay(10);
        scheduleRepository.save(newSchedule);

        return scheduleRepository.findByUserIdAndBookIdAndRemoved(userId, book.getId(), false);
    }

    @Override
    @Transactional
    public Schedule switchSchedule(Long bookId, Long userId) {
        // 原先计划设置为不在学习中
        Schedule oldSchedule = scheduleRepository.findByUserIdAndCurrent(userId, true);
        if(oldSchedule == null) {
            throw new RuntimeException("原计划为空，不可切换");
        }
        oldSchedule.setCurrent(false);
        scheduleRepository.save(oldSchedule);

        Schedule newSchedule = scheduleRepository.findByBookIdAndUserIdAndRemoved(bookId, userId, false);
        if(newSchedule == null) {
            throw new BadRequestException("用户不存在该图书");
        }
        newSchedule.setCurrent(true);
        return scheduleRepository.save(newSchedule);
    }


    @Override
    @Transactional
    public Schedule reset(Long userId) {
        // 删除原来计划
        Schedule oldSchedule = scheduleRepository.findByUserIdAndCurrent(userId, true);
        if(oldSchedule == null) {
            throw new RuntimeException("没有正在学习的计划，无法重置");
        }
        oldSchedule.setRemoved(true);
        oldSchedule.setCurrent(false);
        scheduleRepository.save(oldSchedule);

        // 删除原计划题库
        scheduleRecordRepository.deleteAllByScheduleId(oldSchedule.getId());

        Schedule newSchedule = new Schedule();
        newSchedule.setBookId(oldSchedule.getBookId());
        newSchedule.setWordsPerDay(oldSchedule.getWordsPerDay());
        newSchedule.setUserId(userId);
        newSchedule.setCurrent(true);
        scheduleRepository.save(newSchedule);
        return newSchedule;
    }

    @Override
    public Schedule getCurrentSchedule(Long userId) {
        return scheduleRepository.findByUserIdAndCurrent(userId, true);
    }

    @Override
    public boolean existsSchedule(Long bookId, Long userId) {
        Schedule schedule = scheduleRepository.findByUserIdAndBookIdAndRemoved(userId, bookId, false);
        return schedule != null;
    }

    /**
     * 新建一个计划完成记录表
     * @param words
     * @param scheduleId
     */
    @Override
    public void addScheduleRecord(List<Word> words, Long scheduleId) {
        words.forEach(word -> {
            ScheduleRecord scheduleRecord = new ScheduleRecord();
            scheduleRecord.setStatus(0);
            scheduleRecord.setWordId(word.getId());
            scheduleRecord.setScheduleId(scheduleId);
            scheduleRecordRepository.save(scheduleRecord);
        });
    }

    @Override
    public int getWordStatus(Long scheduleId, Long wordId) {
        ScheduleRecord scheduleRecord = scheduleRecordRepository.findByScheduleIdAndWordId(scheduleId, wordId);
        return scheduleRecord.getStatus();
    }

    @Override
    public List<Schedule> getAllSchedules(Long userId) {
        return scheduleRepository.findAllByUserIdAndRemoved(userId, false);
    }

    /**
     * 通过计划id获得计划
     * @param scheduleId
     * @param userId
     * @return
     */
    @Override
    public Schedule getScheduleById(Long scheduleId, Long userId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElse(null);
        if(schedule == null || schedule.isRemoved()) {
            throw new RuntimeException("该计划不存在");
        }

        if(!Objects.equals(schedule.getUserId(), userId)) {
            throw new RuntimeException("这是其他用户的计划");
        }
        return schedule;
    }

    /**
     * 获得已学词汇数量
     * @param scheduleId
     * @return
     */
    @Override
    public Integer getHasLearned(Long scheduleId) {
        return scheduleRecordRepository.countByScheduleIdAndStatus(scheduleId);
    }

    /**
     * 获得计划总词汇数量
     * @param scheduleId
     * @return
     */
    @Override
    public Integer getAll(Long scheduleId) {
        return scheduleRecordRepository.countByScheduleId(scheduleId);
    }
}
