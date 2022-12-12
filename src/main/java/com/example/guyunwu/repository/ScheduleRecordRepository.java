package com.example.guyunwu.repository;

import com.example.guyunwu.model.entity.Schedule;
import com.example.guyunwu.model.entity.ScheduleRecord;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRecordRepository extends BaseRepository<ScheduleRecord, Long>, JpaSpecificationExecutor<ScheduleRecord> {
    ScheduleRecord findByScheduleIdAndWordId(Long scheduleId, Long wordId);

    @Query(value = "select count(*) from schedule_record where schedule_id = ?1 and status <> 0", nativeQuery = true)
    Integer countByScheduleIdAndStatus(Long id);

    Integer countByScheduleId(Long scheduleId);

    void deleteAllByScheduleId(Long id);
}
