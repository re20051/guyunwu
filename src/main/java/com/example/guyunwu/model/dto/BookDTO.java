package com.example.guyunwu.model.dto;

import com.example.guyunwu.model.entity.Author;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO implements Serializable {

    private Long id;

    private AuthorDTO author;

    private String name;

    private String coverImage;

    private String introduce;

    private Integer reads;

    private Integer likes;

    private String press;

    private String category;
}
