package com.example.guyunwu.repository;

import com.example.guyunwu.model.entity.ClockRecord;
import io.swagger.models.auth.In;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ClockRepository extends BaseRepository<ClockRecord, Long>, JpaSpecificationExecutor<ClockRecord>  {

    @Query(value = "select day(date) from clock_record where user_id = ?1 and year(date) = ?2 and month(date) = ?3", nativeQuery = true)
    List<Integer> getMonthRecord(Long userId, Integer year, Integer month);

    int countByUserId(Long userId);

    @Query(value = "select count(*) from clock_record where user_id = ?1 and date = ?2", nativeQuery = true)
    Integer isClocked(Long userId, String data);
}
