package com.example.guyunwu.service;

import com.example.guyunwu.model.entity.Word;

import java.util.Date;
import java.util.List;

public interface LearnService {

    List<Word> getRecord(Long userId, Date date);
}
