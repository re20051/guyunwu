package com.example.guyunwu.model.param;
import com.example.guyunwu.model.entity.User;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class AddCommentParam {

    @NotNull(message = "文章ID不能为空")
    private Long articleId;

    @Length(max = 1023, message = "内容不能超过1023个字符")
    @NotBlank(message = "评论内容不能为空")
    private String content;
}
