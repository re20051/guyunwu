package com.example.guyunwu.service.impl;

import com.example.guyunwu.exception.BadRequestException;
import com.example.guyunwu.model.entity.Schedule;
import com.example.guyunwu.model.param.AddScheduleParam;
import com.example.guyunwu.model.param.UpdateScheduleParam;
import com.example.guyunwu.repository.ScheduleRepository;
import com.example.guyunwu.service.ScheduleService;
import com.example.guyunwu.service.base.AbstractCrudService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;


@Service
public class ScheduleServiceImpl extends AbstractCrudService<Schedule, Long>  implements ScheduleService {

    protected ScheduleServiceImpl(ScheduleRepository scheudleRepository) {
        super(scheudleRepository);
        this.scheudleRepository = scheudleRepository;
    }

    private final ScheduleRepository scheudleRepository;


    @Override
    public void update(UpdateScheduleParam updateScheduleParam, Long userId) {
        Schedule schedule = scheudleRepository.findByIdAndUserId(updateScheduleParam.getId(), userId).orElse(null);
        if(schedule == null || schedule.isRemoved()) {
            throw new RuntimeException("计划不存在");
        }
        if(!schedule.isCurrent()) {
            throw new BadRequestException("只能修改当前在学计划");
        }
        schedule.setWordsPerDay(updateScheduleParam.getWordsPerDay());
        // 正式修改计划
        scheudleRepository.save(schedule);
    }

    @Override
    @Transactional
    public void add(AddScheduleParam addScheduleParam, Long userId) {
        // 原先计划设置为不再学习中
        Schedule oldSchedule = scheudleRepository.findByUserIdAndCurrent(userId, true);
        if(oldSchedule != null) {
            oldSchedule.setCurrent(false);
            scheudleRepository.save(oldSchedule);
        }

        Schedule newSchedule = new Schedule();
        BeanUtils.copyProperties(addScheduleParam, newSchedule);
        newSchedule.setCurrent(true);
        newSchedule.setUserId(userId);
        newSchedule.setWordsPerDay(10);
        scheudleRepository.save(newSchedule);
    }

    @Override
    @Transactional
    public void switchSchedule(Long bookId, Long userId) {
        // 原先计划设置为不在学习中
        Schedule oldSchedule = scheudleRepository.findByUserIdAndCurrent(userId, true);
        if(oldSchedule == null) {
            throw new RuntimeException("原计划为空，不可切换");
        }
        oldSchedule.setCurrent(false);
        scheudleRepository.save(oldSchedule);

        Schedule newSchedule = scheudleRepository.findByBookIdAndUserIdAndRemoved(bookId, userId, false);
        if(newSchedule == null) {
            throw new BadRequestException("用户不存在该图书");
        }
        newSchedule.setCurrent(true);
        scheudleRepository.save(newSchedule);
    }


    @Override
    @Transactional
    public Schedule reset(Long userId) {
        // 删除原来计划
        Schedule oldSchedule = scheudleRepository.findByUserIdAndCurrent(userId, true);
        if(oldSchedule == null) {
            throw new RuntimeException("没有正在学习的计划，无法重置");
        }
        oldSchedule.setRemoved(true);
        oldSchedule.setCurrent(false);
        scheudleRepository.save(oldSchedule);

        Schedule newSchedule = new Schedule();
        newSchedule.setBookId(oldSchedule.getBookId());
        newSchedule.setWordsPerDay(oldSchedule.getWordsPerDay());
        newSchedule.setUserId(userId);
        newSchedule.setCurrent(true);
        scheudleRepository.save(newSchedule);
        return newSchedule;
    }

    @Override
    public Schedule getCurrentSchedule(Long userId) {
        return scheudleRepository.findByUserIdAndCurrent(userId, true);
    }
}
