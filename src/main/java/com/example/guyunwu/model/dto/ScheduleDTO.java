package com.example.guyunwu.model.dto;

import lombok.Data;
import java.util.List;

@Data
public class ScheduleDTO {

    private Long id;

    private Long bookId;

    private Integer wordsPerDay;

    private List<WordDTO> words;
}
