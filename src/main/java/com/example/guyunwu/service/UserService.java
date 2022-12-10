package com.example.guyunwu.service;

import com.example.guyunwu.model.entity.User;
import com.example.guyunwu.model.param.RegisterParam;

public interface UserService {

    User register(RegisterParam param);
}
