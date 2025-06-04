package com.example.controller;

import com.example.pojo.Emp;
import com.example.pojo.LoginInfo;
import com.example.pojo.Result;
import com.example.servive.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录
 */
@Slf4j
@RestController
public class LoginController {

    @Autowired
    private EmpService empService;

    /**
     * 登录
     */
    @PostMapping("/login")
    public Result login(@RequestBody Emp emp) {
        LoginInfo loginInfo = empService.login(emp);
        if (loginInfo == null) {
            return Result.error("用户名或密码错误");
        } else {
            return Result.success(loginInfo);
        }

    }
}
