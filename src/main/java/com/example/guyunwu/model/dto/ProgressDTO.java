package com.example.guyunwu.model.dto;

import lombok.Data;

@Data
public class ProgressDTO {

    private BookDTO book;

    private Integer learned;

    private Integer all;
}
