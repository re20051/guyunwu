package com.example.guyunwu.repository;

import com.example.guyunwu.model.entity.ClockRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ClockRepository extends BaseRepository<ClockRecord, Long>, JpaSpecificationExecutor<ClockRecord>  {

    int countByUserIdAndClocked(Long userId, boolean b);

    @Query(value = "select learn_date from clock_record where user_id = ?1 and year(learn_date) = ?2 and month(learn_date) = ?3", nativeQuery = true)
    List<Date> getMonthRecord(Long userId, Integer year, Integer month);
}
