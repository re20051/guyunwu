package com.example.guyunwu.model.param;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

@Data
public class DateParam {

    @Range(min = 2021, max = 2025, message = "年份必须21-25年之间")
    @NotNull(message = "年份不能为空")
    private Integer year;

    @Range(min = 1, max = 12, message = "月份必须合法")
    @NotNull(message = "月份不能为空")
    private Integer month;

    private Integer day;
}
