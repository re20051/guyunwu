package com.example.guyunwu.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "schedule_record")
public class ScheduleRecord extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "record_id")
    private Long id;

    @Column(name = "schedule_id")
    private Long scheduleId;

    @Column(name = "word_id")
    private Long wordId;

    @Column(name = "date")
    private Date date;

    @Column(name = "status")
    private Integer status;
}
