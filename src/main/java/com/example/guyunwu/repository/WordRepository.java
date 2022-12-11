package com.example.guyunwu.repository;
import com.example.guyunwu.model.entity.Word;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface WordRepository extends BaseRepository<Word, Long>, JpaSpecificationExecutor<Word> {

    @Transactional
    @Modifying
    @Query(value = "insert into word_collection(word_id, user_id) values(?1,?2)", nativeQuery = true)
    int collectWord(Long wordId, Long userId);

    @Transactional
    @Modifying
    @Query(value = "delete from word_collection where word_id = ?1 and user_id = ?2", nativeQuery = true)
    void cancelWord(Long wordId, Long userId);
}
