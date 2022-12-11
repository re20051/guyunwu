package com.example.guyunwu.controller;

import com.example.guyunwu.model.entity.Book;
import com.example.guyunwu.model.entity.DailySentence;
import com.example.guyunwu.model.entity.Word;
import com.example.guyunwu.model.response.Result;
import com.example.guyunwu.repository.DailySentenceRepository;
import com.example.guyunwu.service.CollectionService;
import com.example.guyunwu.utils.SecurityUtil;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/collection")
@RequiredArgsConstructor
public class CollectionController {

    private final CollectionService collectionService;

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
    public Result getMyBooks() {
        Long userId = SecurityUtil.getCurrentUserId();
        List<Book> myBooks = collectionService.getMyBooks(userId);
        return Result.ok("ok", myBooks);
    }

    @ApiOperation("所有图书")
    @GetMapping(value = "/book/all")
    public Result getAllBooks() {
        List<Book> myBooks = collectionService.getAllBooks();
        return Result.ok("ok", myBooks);
    }

    ////////////////////////////////////////////////////////////////////////////////////////

    @ApiOperation("我的收藏实词")
    @GetMapping(value = "/word/my")
    public Result getMyWords() {
        Long userId = 4L;
        List<Word> words = collectionService.getMyWords(userId);

        return Result.ok();
    }

    @ApiOperation("收藏实词")
    @PostMapping(value = "/word/{wordId:\\d+}")
    public Result collectWord(@PathVariable Long wordId) {
//        Long userId = SecurityUtil.getCurrentUserId();
        Long userId = 4L;
        collectionService.collectWord(wordId, userId);
        return Result.ok();
    }

    @ApiOperation("取消收藏")
    @DeleteMapping(value = "/word/cancel/{wordId:\\d+}")
    public Result cancelWord(@PathVariable Long wordId) {
        //        Long userId = SecurityUtil.getCurrentUserId();
        Long userId = 4L;
        collectionService.cancelWord(wordId, userId);
        return Result.ok();
    }
}
