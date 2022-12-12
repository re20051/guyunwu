package com.example.guyunwu.model.dto;

import lombok.Data;

@Data
public class WordDTO {

    private Long id;

    private Long bookId;

    private String content;

    private String answerA;

    private String answerB;

    private String answerC;

    private String answerD;

    private String correctAnswer;

    private String keyWord;

    private Integer keyIndex;

    private String translate;

    private Integer status;
}
