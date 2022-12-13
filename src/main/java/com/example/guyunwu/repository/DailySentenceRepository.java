package com.example.guyunwu.repository;

import com.example.guyunwu.model.entity.DailySentence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DailySentenceRepository
        extends JpaRepository<DailySentence, Integer>, JpaSpecificationExecutor<DailySentence> {
}
