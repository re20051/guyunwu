package com.example.guyunwu.model.dto;

import lombok.Data;

@Data
public class WordDTO {

    private Long wordId;

    private BookDTO book;

    private String content;

    private String answerA;

    private String answerB;

    private String answerC;

    private String answerD;

    private Integer correctAnswer;

    private String keyWord;

    private Integer keyIndex;

    private String translate;

    private Integer status;
}
