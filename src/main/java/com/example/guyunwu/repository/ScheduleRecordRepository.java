package com.example.guyunwu.repository;

import com.example.guyunwu.model.entity.ScheduleRecord;
import com.example.guyunwu.model.entity.Word;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ScheduleRecordRepository extends BaseRepository<ScheduleRecord, Long>, JpaSpecificationExecutor<ScheduleRecord> {
    ScheduleRecord findByScheduleIdAndWordId(Long scheduleId, Long wordId);

    @Query(value = "select count(1) from schedule_record where schedule_id = ?1 and status <> 0", nativeQuery = true)
    Integer countByScheduleIdAndStatus(Long id);

    @Query(value = "select count(1) from schedule_record where schedule_id = ?1", nativeQuery = true)
    Integer countByScheduleId(Long scheduleId);

    void deleteAllByScheduleId(Long scheduleId);

    @Query(value = "select count(1) from schedule_record where schedule_id = ?1 and status = ?2 and date = ?3", nativeQuery = true)
    int countByScheduleIdAndStatusAndDate(Long scheduleId, int status, String date);

    @Query(value = "select count(word_id) from schedule_record where schedule_id = ?1 and status = 1 and date <> ?2", nativeQuery = true)
    Integer getReviewWordCount(Long scheduleId, String today);

    @Query(value = "select * from schedule_record where word_id = ?1 and schedule_id = ?2", nativeQuery = true)
    ScheduleRecord findByWordIdAndScheduleId(Long wordId, Long scheduleId);

    @Transactional
    @Query(value = "update schedule_record set status = ?2 where record_id = ?1", nativeQuery = true)
    @Modifying
    void updateStatus(Long id, Integer status);
}
