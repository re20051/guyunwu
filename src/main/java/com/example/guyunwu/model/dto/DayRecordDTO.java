package com.example.guyunwu.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class DayRecordDTO {

    List<WordDTO> words;

    Boolean isClocked;
}
