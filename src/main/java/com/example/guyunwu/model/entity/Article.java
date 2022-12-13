package com.example.guyunwu.model.entity;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "article")
public class Article implements Serializable {

    public static final String CATEGORY_EXPLORE = "explore";

    public static final String CATEGORY_LECTURE = "lecture";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    private Long id;

    @Column(name = "cover_image")
    private String coverImage;

    @Column(name = "title")
    private String title;

    @Column(name = "user_id")
    private Long userId;

    @Lob
    @Column(name = "content")
    private String content;

    @Column(name = "summary")
    private String summary;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "publish_date")
    private Date publishDate;

    @Column(name = "`reads`")
    private Long reads;

    @Column(name = "category")
    private String category;

    // 暂时没有tag
    // private List<String> tags;
}
