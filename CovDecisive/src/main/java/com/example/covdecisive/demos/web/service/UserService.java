package com.example.covdecisive.demos.web.service;
import com.example.covdecisive.demos.web.model.User;
import com.example.covdecisive.demos.web.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public List<User> getAllUsers() {
        return userMapper.getAllUsers();
    }

    public User login(String username, String password) {
        User user = userMapper.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            System.out.println("✅ 登录成功：" + user);
            return user;
        }
        return null;
    }
}
