package com.example.guyunwu.service;

import com.example.guyunwu.model.entity.Book;
import com.example.guyunwu.model.entity.Word;

import java.util.List;

public interface CollectionService {
    void collectWord(Long wordId, Long userId);

    void cancelWord(Long wordId, Long userId);

    /**
     * 获得一个人收藏的实词
     * @param userId
     * @return
     */
    List<Long> getMyWords(Long userId);

    /**
     * 获得一个用户收藏的书本
     * @param userId
     * @return
     */
    List<Book> getMyBooks(Long userId);

    /**
     * 获得图书馆
     * @return
     */
    List<Book> getAllBooks();

    /**
     * 获得一本书的所有实词
     * @param bookId
     * @return
     */
    List<Word> getWordsByBookId(Long bookId);

    /**
     * 通过id获得book
     * @param bookId
     * @return
     */
    Book getBookById(Long bookId);

    /**
     * 通过id查询到实词
     * @param id
     * @return
     */
    Word getWordById(Long id);
}
