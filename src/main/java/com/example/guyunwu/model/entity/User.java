package com.example.guyunwu.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "username", columnDefinition = "varchar(63)", unique = true, nullable = false)
    private String username;

    @Column(name = "avatar", columnDefinition = "varchar(255)", nullable = false)
    private String avatar;

    @Column(name = "gender", columnDefinition = "int(11) default 0")
    private Integer gender;

    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "password", columnDefinition = "varchar(127)", nullable = false)
    private String password;

    @Column(name = "phone_number", columnDefinition = "varchar(31)", unique = true, nullable = false)
    private String phoneNumber;


    @Override
    protected void prePersist() {
        super.prePersist();
        if(gender == null){
            gender = 2;
        }
    }
}
