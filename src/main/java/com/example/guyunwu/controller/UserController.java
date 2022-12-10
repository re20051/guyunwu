package com.example.guyunwu.controller;

import com.example.guyunwu.model.response.Result;
import com.example.guyunwu.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation("登录")
    @GetMapping(value = "/login")
    public Result login() {

        return Result.ok();
    }


}
