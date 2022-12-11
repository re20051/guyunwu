package com.example.guyunwu.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "clock_record")
public class ClockRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clock_id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "date")
    private Date date;

    @Column(name = "clocked")
    private boolean clocked;
}
