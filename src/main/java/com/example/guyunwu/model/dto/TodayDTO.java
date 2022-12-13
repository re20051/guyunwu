package com.example.guyunwu.model.dto;

import com.example.guyunwu.model.entity.Word;
import lombok.Data;

import java.util.List;

@Data
public class TodayDTO {

    private Integer review;

    private Integer learn;

    private Integer hasLearned;
}
