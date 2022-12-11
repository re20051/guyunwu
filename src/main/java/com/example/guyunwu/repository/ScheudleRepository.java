package com.example.guyunwu.repository;

import com.example.guyunwu.model.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ScheudleRepository extends BaseRepository<Schedule, Long>, JpaSpecificationExecutor<Schedule> {
    Schedule findById(Schedule schedule);

    Optional<Schedule> findByIdAndUserId(Long id, Long userId);

    Schedule findByUserIdAndCurrent(Long userId, boolean b);
}
