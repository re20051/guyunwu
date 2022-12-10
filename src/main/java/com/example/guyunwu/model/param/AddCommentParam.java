package com.example.guyunwu.model.param;
import com.example.guyunwu.model.entity.User;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class AddCommentParam {

    private Long articleId;

    private Long userId;

    @Length(max = 1023, message = "标题不能超过127个字符")
    @NotBlank(message = "标题不能为空")
    private String title;

    @Length(max = 1023, message = "内容不能超过1023个字符")
    @NotBlank(message = "帖子内容不能为空")
    private String content;
}
