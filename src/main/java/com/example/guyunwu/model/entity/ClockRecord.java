package com.example.guyunwu.model.entity;

import com.example.guyunwu.model.PK.ClockRecordPK;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@IdClass(ClockRecordPK.class)
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "clock_record")
public class ClockRecord {

    @Id
    @Column(name = "user_id")
    private Long userId;

    @Id
    @Column(name = "date")
    private Date date;

    @Column(name = "clocked")
    private boolean clocked;
}
