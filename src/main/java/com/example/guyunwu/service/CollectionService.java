package com.example.guyunwu.service;

import com.example.guyunwu.model.entity.Book;
import com.example.guyunwu.model.entity.Word;

import java.util.List;

public interface CollectionService {
    void collectWord(Long wordId, Long userId);

    void cancelWord(Long wordId, Long userId);

    List<Word> getMyWords(Long userId);


    List<Book> getMyBooks(Long userId);

    List<Book> getAllBooks();

    List<Word> getWordsByBookId(Long bookId);

    /**
     * 通过id获得book
     * @param bookId
     * @return
     */
    Book getBookById(Long bookId);
}
