package com.example.guyunwu.model.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CommentDTO {

    private Long id;

    private UserDTO author;

    private String content;

    private Date publishDate;

    private Boolean liked;
}
