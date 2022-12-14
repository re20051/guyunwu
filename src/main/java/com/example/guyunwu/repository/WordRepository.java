package com.example.guyunwu.repository;
import com.example.guyunwu.model.entity.Word;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface WordRepository extends BaseRepository<Word, Long>, JpaSpecificationExecutor<Word> {

    List<Word> getAllByBookId(Long bookId);

    @Query(value = "select * from (select word_id from " +
            "schedule_record where schedule_id = ?1 and status = 0 order by word_id limit ?2) as ids " +
            "natural join word", nativeQuery = true)
    List<Word> getNewWords(Long scheduleId, int learn);

    @Query(value = "select * from " +
            "(select word_id from schedule_record where schedule_id = ?1 and status = 1 and date <> ?2 order by word_id) as ids " +
            "natural join word", nativeQuery = true)
    List<Word> getReviewWords(Long scheduleId, String today);

    @Query(value = "select * from " +
            "(select word_id from learn_record where user_id = ?1 and learn_date = ?2) as ids " +
            "natural join word", nativeQuery = true)
    List<Word> getLearnedWordsOfOneDay(Long userId, String date);

    @Query(value = "select * from (select word_id from word_collection where user_id = ?1) as ids " +
            "natural join word", nativeQuery = true)
    List<Word> getMyWords(Long userId);

    ///////////////////////////////////////////////////////////////////////////////////////////
    @Query(value = "select count(*) from word_collection where word_id = ?1 and user_id = ?2", nativeQuery = true)
    Integer isWordCollected(Long wordId, Long userId);

    @Transactional
    @Modifying
    @Query(value = "insert into word_collection(word_id, user_id) values(?1,?2)", nativeQuery = true)
    int collectWord(Long wordId, Long userId);

    @Transactional
    @Modifying
    @Query(value = "delete from word_collection where word_id = ?1 and user_id = ?2", nativeQuery = true)
    void cancelWord(Long wordId, Long userId);



}
