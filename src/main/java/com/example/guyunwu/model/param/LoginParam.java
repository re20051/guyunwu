package com.example.guyunwu.model.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class LoginParam {

    @NotNull
    private String password;

    @Pattern(regexp = "^[1]([3-9])[0-9]{9}$", message = "手机号不合法")
    @NotBlank(message = "手机号不能为空")
    private String phoneNumber;
}
