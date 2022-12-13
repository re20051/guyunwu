package com.example.guyunwu.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProgressDTO {

    private BookDTO book;

    private Integer learned;

    private Integer all;
}
