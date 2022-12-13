package com.example.guyunwu.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "schedule")
public class Schedule extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "book_id")
    private Long bookId;

    @Column(name = "words_per_day")
    private Integer wordsPerDay;

    @Column(name = "current")
    private boolean current;

    @Column(name = "removed")
    private boolean removed;
}
