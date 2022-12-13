package com.example.guyunwu.service.impl;

import com.example.guyunwu.model.entity.Book;
import com.example.guyunwu.model.entity.Word;
import com.example.guyunwu.repository.BookRepository;
import com.example.guyunwu.repository.ScheduleRepository;
import com.example.guyunwu.repository.WordRepository;
import com.example.guyunwu.service.CollectionService;
import com.example.guyunwu.service.base.AbstractCrudService;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectionServiceImpl extends AbstractCrudService<Word, Long> implements CollectionService {

    protected CollectionServiceImpl(WordRepository wordRepository, BookRepository bookRepository) {
        super(wordRepository);
        this.wordRepository = wordRepository;
        this.bookRepository = bookRepository;
    }

    private final WordRepository wordRepository;

    private final BookRepository bookRepository;

    @Override
    public void collectWord(Long wordId, Long userId) {
        try {
            wordRepository.collectWord(wordId, userId);
        } catch (Exception e) {
            throw new RuntimeException("单词不存在或已收藏");
        }
    }

    @Override
    public void cancelWord(Long wordId, Long userId) {
        try {
            wordRepository.cancelWord(wordId, userId);
        } catch (Exception e) {
            throw new RuntimeException("单词不存在");
        }
    }

    @Override
    public List<Long> getMyWords(Long userId) {
        return null;
    }

    @Override
    public List<Book> getMyBooks(Long userId) {
        return bookRepository.getMyBooks(userId);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public List<Word> getWordsByBookId(Long bookId) {
        return wordRepository.getAllByBookId(bookId);
    }

    @Override
    public Book getBookById(Long bookId) {
        return bookRepository.getByBookId(bookId);
    }

    /**
     * 通过id查询到实词
     * @param id
     * @return
     */
    @Override
    public Word getWordById(Long id) {
        return wordRepository.getById(id);
    }
}
