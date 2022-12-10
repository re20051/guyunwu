package com.example.guyunwu.model.param;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class AddArticleParam {

    @Length(max = 127, message = "标题不能超过127个字符")
    @NotBlank(message = "标题不能为空")
    private String title;

    // 格式为HTML，暂时不限制长度
    @NotBlank(message = "帖子内容不能为空")
    private String content;

    @NotBlank(message = "摘要不能为空")
    private String summary;

    private String coverImage;
}
