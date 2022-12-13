package com.example.guyunwu.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long id;

    @Column(name = "author_id", nullable = false)
    private Long authorId;

    @Column(name = "name", columnDefinition = "varchar(255)", nullable = false)
    private String name;

    @Column(name = "cover_image", columnDefinition = "varchar(255)", nullable = false)
    private String coverImage;

    @Column(name = "introduce", columnDefinition = "varchar(511)", nullable = false)
    private String introduce;

    @Column(name = "reads", nullable = false)
    private Integer reads;

    @Column(name = "likes", nullable = false)
    private Integer likes;

    @Column(name = "press", nullable = false)
    private String press;

    @Column(name = "category", nullable = false)
    private String category;
}
