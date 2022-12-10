package com.example.guyunwu.service;

import com.example.guyunwu.model.entity.User;
import com.example.guyunwu.model.param.LoginParam;
import com.example.guyunwu.model.param.RegisterParam;
import com.example.guyunwu.model.param.UpdateUserParam;

public interface UserService {

    User register(RegisterParam param);

    User login(LoginParam loginParam);

    User update(UpdateUserParam updateUserParam, Long userId);
}
