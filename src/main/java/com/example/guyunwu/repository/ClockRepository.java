package com.example.guyunwu.repository;

import com.example.guyunwu.model.entity.ClockRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ClockRepository extends BaseRepository<ClockRecord, Long>, JpaSpecificationExecutor<ClockRecord>  {

    int countByUserIdAndClocked(Long userId, boolean b);
}
