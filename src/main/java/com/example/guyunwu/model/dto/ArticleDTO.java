package com.example.guyunwu.model.dto;


import java.io.Serializable;
import java.util.Date;

import com.example.guyunwu.model.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDTO implements Serializable {

    private Long id;

    private String coverImage;

    private String title;

    private UserDTO author;

    private String content;

    private String summary;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date publishDate;

    private Long reads;

    private Long likes;

    private String category;

}
