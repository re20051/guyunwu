package com.example.guyunwu.model.param;

import org.hibernate.validator.constraints.Range;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

public class UpdateUserParam {

    @Pattern(regexp = "^[0-9A-Za-z]{2,12}$",
            message = "用户名长度须在2~12个字符之间且只能由字母和数字组成")
    private String username;

    private String avatar;

    @Range(min = 0, max = 2, message = "只有0，1，2三种性别")
    @NotNull(message = "性别不能为空")
    private Integer gender;

    @NotNull(message = "生日不能为空")
    private Date birthDay;
}