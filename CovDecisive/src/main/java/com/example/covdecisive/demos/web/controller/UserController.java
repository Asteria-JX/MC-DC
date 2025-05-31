package com.example.covdecisive.demos.web.controller;

import com.example.covdecisive.demos.web.model.User;
import com.example.covdecisive.demos.web.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Api(tags = "API接口")
@RestController
@RequestMapping("/api")
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

    @ApiOperation("删除指定用户")
    @PostMapping("/deleteUser/{user_id}")
    public void deleteUser(@PathVariable("user_id") int user_id) {
        //System.out.println(user_id);
        userService.deleteUser(user_id);
    }

    @ApiOperation("添加新用户")
    @PostMapping("/insertUser/{username}/{password}")
    public void insertUser(@PathVariable("username") String username, @PathVariable("password") String password) {
        //System.out.println(username+" "+password);
        userService.insertUser(username, password);
    }
}
