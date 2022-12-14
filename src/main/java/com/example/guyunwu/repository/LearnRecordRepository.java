package com.example.guyunwu.repository;

import com.example.guyunwu.model.entity.LearnRecord;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LearnRecordRepository extends BaseRepository<LearnRecord, Long>, JpaSpecificationExecutor<LearnRecord> {

    @Query(value = "select count(distinct word_id) from learn_record where user_id = ?1", nativeQuery = true)
    int totalLearned(Long userId);
}
