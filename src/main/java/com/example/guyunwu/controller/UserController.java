package com.example.guyunwu.controller;

import com.example.guyunwu.model.param.RegisterParam;
import com.example.guyunwu.model.response.Result;
import com.example.guyunwu.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation("注册新用户")
    @PostMapping(value = "/register")
    public Result register(@RequestBody @Validated RegisterParam registerParam) {

        return Result.ok();
    }

    @ApiOperation("更新用户信息")
    @PutMapping(value = "/update")
    public Result update(@RequestBody @Validated RegisterParam registerParam) {

        return Result.ok();
    }


}
