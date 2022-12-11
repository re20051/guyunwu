package com.example.guyunwu.model.param;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ListCommentParam {

    @NotNull(message = "文章ID不能为空")
    private Long articleId;

    private int page = 0;

    private int size = 10;
}
