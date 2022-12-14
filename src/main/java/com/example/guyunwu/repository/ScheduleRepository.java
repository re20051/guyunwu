package com.example.guyunwu.repository;

import com.example.guyunwu.model.entity.Schedule;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScheduleRepository extends BaseRepository<Schedule, Long>, JpaSpecificationExecutor<Schedule> {
    Schedule findById(Schedule schedule);

    Optional<Schedule> findByIdAndUserId(Long id, Long userId);

    @Query(value = "select * from schedule where user_id = ?1 and current = ?2", nativeQuery = true)
    Schedule findByUserIdAndCurrent(Long userId, int current);

    Schedule findByBookIdAndUserIdAndRemoved(Long bookId, Long userId, boolean b);

    Schedule findByUserIdAndBookIdAndRemoved(Long userId, Long bookId, boolean b);

    List<Schedule> findAllByUserIdAndRemoved(Long userId, boolean b);

    @Query(value = "select * from schedule where schedule_id = ?1", nativeQuery = true)
    Schedule findByScheduleId(Long scheduleId);
}
