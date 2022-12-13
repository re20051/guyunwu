package com.example.guyunwu.model.entity;

import com.example.guyunwu.model.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @Column(name = "article_id")
    private Long articleId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "content")
    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "publish_date")
    private Date publishDate;
}
