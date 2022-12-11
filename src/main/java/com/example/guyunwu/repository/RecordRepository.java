package com.example.guyunwu.repository;

import com.example.guyunwu.model.entity.LearnRecord;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordRepository extends BaseRepository<LearnRecord, Long>, JpaSpecificationExecutor<LearnRecord> {

    @Query(value = "select count(distinct word_id) " +
                    "from learn_record join " +
                    "(select * from schedule where user_id = ?1) as schedules " +
                    "on learn_record.schedule_id = schedules.schedule_id",
                    nativeQuery = true)
    int countTotalLearned(Long userId);

    @Query(value = "select count(distinct word_id)" +
                    "from learn_record " +
                    "where schedule_id = ?1 and learn_date = ?2",
                    nativeQuery = true)
    int countTodayLearned(Long scheduleId, String date);

//    @Query()
//    List<Word> findAllByUserIdAndDate(Long userId, Date date);
}
