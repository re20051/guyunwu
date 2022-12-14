package com.example.guyunwu.controller;

import com.example.guyunwu.model.dto.ArticleDTO;
import com.example.guyunwu.model.dto.AuthorDTO;
import com.example.guyunwu.model.dto.BookDTO;
import com.example.guyunwu.model.dto.WordDTO;
import com.example.guyunwu.model.entity.*;
import com.example.guyunwu.model.response.Result;
import com.example.guyunwu.repository.DailySentenceRepository;
import com.example.guyunwu.service.AuthorService;
import com.example.guyunwu.service.CollectionService;
import com.example.guyunwu.service.ScheduleService;
import com.example.guyunwu.utils.SecurityUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/collection")
@RequiredArgsConstructor
@Slf4j
public class CollectionController {

    private final CollectionService collectionService;

    private final AuthorService authorService;

    private final ScheduleService scheduleService;

    private final DailySentenceRepository dailySentenceRepository;

    @ApiOperation("每日一句")
    @GetMapping(value = "/dailySentence")
    public Result<List<DailySentence>> dailySentence(Pageable pageable) {
        PageRequest pageRequest = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                Sort.by(Sort.Direction.DESC, "time"));

        Specification<DailySentence> specification = (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.lessThanOrEqualTo(root.get("time"), LocalDateTime.now());
        Page<DailySentence> p = dailySentenceRepository.findAll(specification, pageRequest);
        return Result.ok("ok", p.toList());
    }

    ////////////////////////////////////////////////////////////////////////////////////////

    @ApiOperation("我的图书")
    @GetMapping(value = "/book/my")
    public Result<List<BookDTO>> getMyBooks() {
        Long userId = SecurityUtil.getCurrentUserId();
        List<Book> myBooks = collectionService.getMyBooks(userId);
        List<BookDTO> res = myBooks.stream().map(this::toBookDto).collect(Collectors.toList());
        return Result.ok("ok", res);
    }

    @ApiOperation("所有图书")
    @GetMapping(value = "/book/all")
    public Result<List<BookDTO>> getAllBooks() {
        List<Book> myBooks = collectionService.getAllBooks();
        List<BookDTO> res = myBooks.stream().map(this::toBookDto).collect(Collectors.toList());
        return Result.ok("ok", res);
    }

    @ApiOperation("是否已添加书本")
    @GetMapping(value = "/hasBook/{bookId:\\d+}")
    public Result<Boolean> getHasBook(@PathVariable Long bookId) {
        Long userId = SecurityUtil.getCurrentUserId();
        boolean has = scheduleService.existsSchedule(bookId, userId);
        return Result.ok("ok", has);
    }

    ////////////////////////////////////////////////////////////////////////////////////////

    @ApiOperation("我的收藏实词")
    @GetMapping(value = "/word/my")
    public Result<List<WordDTO>> getMyWords() {
        Long userId = SecurityUtil.getCurrentUserId();
        List<WordDTO> wordDTOS = new ArrayList<>();
        List<Word> words = collectionService.getMyWords(userId);

        words.forEach(word -> {
            WordDTO wordDTO = new WordDTO();
            BookDTO bookDTO = toBookDto(collectionService.getBookById(word.getBookId()));
            BeanUtils.copyProperties(word, wordDTO);
            wordDTO.setBook(bookDTO);
            wordDTOS.add(wordDTO);
        });

        return Result.ok("ok", wordDTOS);
    }

    @ApiOperation("收藏实词")
    @PostMapping(value = "/word/{wordId:\\d+}")
    public Result<Object> collectWord(@PathVariable Long wordId) {
        Long userId = SecurityUtil.getCurrentUserId();
        collectionService.collectWord(wordId, userId);
        return Result.ok();
    }

    @ApiOperation("取消收藏")
    @PutMapping(value = "/word/cancel/{wordId:\\d+}")
    public Result<Object> cancelWord(@PathVariable Long wordId) {
        Long userId = SecurityUtil.getCurrentUserId();
        collectionService.cancelWord(wordId, userId);
        return Result.ok();
    }

    @ApiOperation("判断一个实词是否已经被收藏")
    @PutMapping(value = "/word/isCollected/{wordId:\\d+}")
    public Result<Boolean> isCollected(@PathVariable Long wordId) {
        Long userId = SecurityUtil.getCurrentUserId();
        Boolean result = collectionService.isCollected(wordId, userId);
        return Result.ok("ok", result);
    }

    public BookDTO toBookDto(Book book) {
        BookDTO bookDTO = new BookDTO();
        BeanUtils.copyProperties(book, bookDTO);
        Author author = authorService.getAuthor(book.getAuthorId());
        AuthorDTO authorDTO = new AuthorDTO();
        BeanUtils.copyProperties(author, authorDTO);
        bookDTO.setAuthor(authorDTO);
        return bookDTO;
    }
}
