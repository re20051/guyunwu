package com.example.guyunwu.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum QuestionStatus {

    /**
     * 需新学
     */
    LEARN("需新学"),
    /**
     * 需复习
     */
    REVIEW("需复习"),
    /**
     * 学习完成
     */
    FINISH("学习完成");

    private final String description;
}
