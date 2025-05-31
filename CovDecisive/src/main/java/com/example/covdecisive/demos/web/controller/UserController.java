package com.example.covdecisive.demos.web.controller;

import com.example.covdecisive.demos.web.model.User;
import com.example.covdecisive.demos.web.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Api(tags = "API接口")
@RestController
//Spring 中的一个注解，用于支持跨域请求
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation("查询所有用户")
    @GetMapping("/getAllUsers")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/handleLogin")
    @ApiOperation("用户登录")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        String password = body.get("password");

        User user = userService.login(username, password);
        if (user != null) {
            Map<String, Object> data = new HashMap<>();
            data.put("success", true);
            data.put("user_id", user.getUserId());
            data.put("role", user.getUserType());
            return ResponseEntity.ok(data);
        } else {
            return ResponseEntity.status(401).body(Map.of("success", false, "message", "用户名或密码错误"));
        }
    }

}
