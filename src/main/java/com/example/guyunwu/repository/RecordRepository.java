package com.example.guyunwu.repository;

import com.example.guyunwu.model.entity.Comment;
import com.example.guyunwu.model.entity.LearnRecord;
import com.example.guyunwu.model.entity.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface RecordRepository extends BaseRepository<LearnRecord, Long>, JpaSpecificationExecutor<LearnRecord> {
//    List<LearnRecord> findAllByUserIdAndDate(Long userId, Date date);
}
