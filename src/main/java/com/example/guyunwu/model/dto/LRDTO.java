package com.example.guyunwu.model.dto;

import com.example.guyunwu.model.entity.Book;
import com.example.guyunwu.model.entity.Word;
import lombok.Data;

import java.util.List;

@Data
public class LRDTO {

    private List<Word> words;

    private BookDTO book;
}
