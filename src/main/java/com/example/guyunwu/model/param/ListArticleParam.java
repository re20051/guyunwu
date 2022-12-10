package com.example.guyunwu.model.param;

import lombok.Data;

@Data
public class ListArticleParam {

    private String category;

    private int page = 0;

    private int size = 10;
}
