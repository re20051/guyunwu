package com.example.guyunwu.repository;

import com.example.guyunwu.model.entity.LearnRecord;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LearnRecordRepository extends BaseRepository<LearnRecord, Long>, JpaSpecificationExecutor<LearnRecord> {

    @Query(value = "select count(distinct word_id) from learn_record where user_id = ?1", nativeQuery = true)
    int totalLearned(Long userId);

    @Query(value = "select * from learn_record " +
            "where user_id = ?1 and year(learn_date) = ?2 and month(learn_date) = ?3 and day(learn_date) = ?4",
            nativeQuery = true)
    List<Long> getLearnRecord(Long userId, Integer year, Integer month, Integer day);
}
