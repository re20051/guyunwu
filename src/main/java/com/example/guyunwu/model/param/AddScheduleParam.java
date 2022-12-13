package com.example.guyunwu.model.param;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AddScheduleParam {

    @NotNull
    private Long bookId;
}
