package com.example.guyunwu.model.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UserDTO {

    private String username;

    private Integer gender;

    private String avatar;

    private Date birthDate;
}
