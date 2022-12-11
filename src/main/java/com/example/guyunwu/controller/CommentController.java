package com.example.guyunwu.controller;

import com.example.guyunwu.model.dto.CommentDTO;
import com.example.guyunwu.model.dto.UserDTO;
import com.example.guyunwu.model.entity.Comment;
import com.example.guyunwu.model.param.AddCommentParam;
import com.example.guyunwu.model.param.ListCommentParam;
import com.example.guyunwu.model.response.Result;
import com.example.guyunwu.repository.CommentRepository;
import com.example.guyunwu.repository.UserRepository;
import com.example.guyunwu.utils.SecurityUtil;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private final CommentRepository commentRepository;

    private final UserRepository userRepository;

    private CommentDTO toDto(Comment comment){
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(comment.getId());
        commentDTO.setContent(comment.getContent());
        commentDTO.setPublishDate(comment.getPublishDate());
        userRepository.findById(comment.getUserId()).ifPresent(user1 -> {
            UserDTO userDTO = new UserDTO();
            BeanUtils.copyProperties(user1, userDTO);
            commentDTO.setAuthor(userDTO);
        });
        return commentDTO;
    }

    @ApiOperation("获取文章下评论")
    @GetMapping("/list")
    public Result<List<CommentDTO>> getCommentsByArticleId(ListCommentParam param) {
        Specification<Comment> specification = (root, criteriaQuery, criteriaBuilder) -> {
            return criteriaBuilder.and(
                    criteriaBuilder.equal(root.get("articleId"), param.getArticleId())
            );
        };

        PageRequest pageRequest = PageRequest.of(
                param.getPage(),
                param.getSize(),
                Sort.by(Sort.Direction.DESC, "publishDate"));

        List<Comment> comments = commentRepository.findAll(specification, pageRequest).getContent();
        List<CommentDTO> res = comments.stream().map(this::toDto).collect(Collectors.toList());

        return Result.ok("ok", res);
    }

    @ApiOperation("评论")
    @PostMapping
    public Result<CommentDTO> comment(@RequestBody @Validated AddCommentParam addCommentParam) {
        Comment comment = new Comment();
        comment.setArticleId(addCommentParam.getArticleId());
        comment.setContent(addCommentParam.getContent());
        comment.setUserId(SecurityUtil.getCurrentUserId());
        comment.setPublishDate(new Date());
        commentRepository.save(comment);

        return Result.ok("ok", toDto(comment));
    }

    @ApiOperation("点赞评论")
    @PostMapping("/like/{id:\\d+}")
    public Result<?> doLikeComment(@PathVariable Long id) {

        return Result.ok();
    }
}
