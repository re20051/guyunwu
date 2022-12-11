package com.example.guyunwu.service.impl;

import com.example.guyunwu.exception.BadRequestException;
import com.example.guyunwu.model.entity.Schedule;
import com.example.guyunwu.model.param.AddScheduleParam;
import com.example.guyunwu.model.param.UpdateScheduleParam;
import com.example.guyunwu.repository.ScheudleRepository;
import com.example.guyunwu.service.ScheduleService;
import com.example.guyunwu.service.base.AbstractCrudService;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;


@Service
public class ScheduleServiceImpl extends AbstractCrudService<Schedule, Long>  implements ScheduleService {

    protected ScheduleServiceImpl(ScheudleRepository scheudleRepository) {
        super(scheudleRepository);
        this.scheudleRepository = scheudleRepository;
    }

    private final ScheudleRepository scheudleRepository;


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
        scheudleRepository.save(newSchedule);
    }

    @Override
    @Transactional
    public void switchSchedule(Long scheduleId, Long userId) {
        // 原先计划设置为不再学习中
        Schedule oldSchedule = scheudleRepository.findByUserIdAndCurrent(userId, true);
        if(oldSchedule == null) {
            throw new RuntimeException("原计划为空，不可切换");
        }
        oldSchedule.setCurrent(false);
        scheudleRepository.save(oldSchedule);

        Schedule newSchedule = scheudleRepository.findById(scheduleId).orElse(null);
        if(newSchedule == null || newSchedule.isRemoved()) {
            throw new BadRequestException("计划不存在");
        }
        newSchedule.setCurrent(true);
        scheudleRepository.save(newSchedule);
    }

    @Override
    public void delete(Long scheduleId, Long userId) {
        Schedule schedule = scheudleRepository.findByIdAndUserId(scheduleId, userId).orElse(null);
        if(schedule == null || schedule.isRemoved()) {
            throw new RuntimeException("计划不存在");
        }
        if(schedule.isCurrent()) {
            throw new RuntimeException("不可删除正在学习中的计划");
        }
        scheudleRepository.deleteById(scheduleId);
    }

    @Override
    @Transactional
    public void reset(Long userId) {
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
    }
}
