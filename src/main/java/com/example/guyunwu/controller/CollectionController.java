package com.example.guyunwu.controller;

import com.example.guyunwu.model.entity.DailySentence;
import com.example.guyunwu.model.response.Result;
import com.example.guyunwu.repository.DailySentenceRepository;
import com.example.guyunwu.service.CollectionService;
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

        return Result.ok();
    }

    @ApiOperation("添加书籍")
    @GetMapping(value = "/book/collect")
    public Result collectBook(@PathVariable Integer bookId) {

        return Result.ok();
    }

    @ApiOperation("取消收藏")
    @DeleteMapping(value = "/book/cancel")
    public Result cancelBook(@PathVariable Integer bookId) {

        return Result.ok();
    }

    ////////////////////////////////////////////////////////////////////////////////////////

    @ApiOperation("我的收藏实词")
    @GetMapping(value = "/word/my")
    public Result getMyWords() {

        return Result.ok();
    }

    @ApiOperation("收藏实词")
    @GetMapping(value = "/word/collect")
    public Result collectWord(@PathVariable Integer wordId) {

        return Result.ok();
    }

    @ApiOperation("取消收藏")
    @DeleteMapping(value = "/word/cancel")
    public Result cancelWord(@PathVariable Integer wordId) {

        return Result.ok();
    }

}
