package com.example.guyunwu.model.param;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UpdateScheduleParam {

    @NotNull(message = "不能为null")
    private Long id;

    @NotNull(message = "每天学习实词数为null")
    private Integer wordsPerDay;
}
