package com.example.guyunwu.model.param;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Data
public class RegisterParam {

    @Pattern(regexp = "^[0-9A-Za-z]{2,12}$",
            message = "用户名长度须在2~12个字符之间且只能由字母和数字组成")
    private String username;

    @Pattern(regexp = "^[0-9A-Za-z@#$%^&*()_+!]{5,20}$",
            message = "密码长度须在5~20个字符之间且只能包含大小写字母，数字与@#$%^&*()_+!")
    private String password;

    private String url;

    @Range(min = 0, max = 2, message = "只有0，1，2三种性别")
    @NotNull(message = "性别不能为空")
    private Integer gender;

    @NotNull(message = "生日不能为空")
    private Date birthDay;
}
