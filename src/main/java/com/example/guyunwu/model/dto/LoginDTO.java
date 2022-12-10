package com.example.guyunwu.model.dto;
import lombok.Data;

import java.util.Date;

@Data
public class LoginDTO {

    private String username;

    private Integer gender;

    private String avatar;

    private Date birthDate;

    private String token;
}
