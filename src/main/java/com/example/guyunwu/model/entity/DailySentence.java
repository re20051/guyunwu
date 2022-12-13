package com.example.guyunwu.model.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "daily_sentence")
public class DailySentence extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "content")
    private String content;

    @Column(name = "source")
    private String source;

    @Column(name = "author")
    private String author;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "time")
    private LocalDateTime time;

    @Column(name = "image_url", columnDefinition = "varchar(1023) default ''")
    private String imageUrl;

}
