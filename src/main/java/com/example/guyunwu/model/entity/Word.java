package com.example.guyunwu.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "word")
public class Word {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "word_id")
    private Long wordId;

    @Column(name = "book_id", nullable = false)
    private Long bookId;

    @Column(name = "chapter_id", nullable = false)
    private Long chapterId;

    @Column(name = "content",columnDefinition = "varchar(511)", nullable = false)
    private String content;

    @Column(name = "answer_a", nullable = false)
    private String answerA;

    @Column(name = "answer_b", nullable = false)
    private String answerB;

    @Column(name = "answer_c", nullable = false)
    private String answerC;

    @Column(name = "answer_d", nullable = false)
    private String answerD;

    @Column(name = "correct_answer", nullable = false)
    private Integer correctAnswer;

    @Column(name = "key_word", columnDefinition = "varchar(63)", nullable = false)
    private String keyWord;

    @Column(name = "key_index", nullable = false)
    private Integer keyIndex;

    @Column(name = "translate", columnDefinition = "varchar(1011)", nullable = false)
    private String translate;
}
