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

    public void deleteUser(Integer id) {
        userMapper.deleteUser(id);
    }

    public void insertUser(String username, String password) {
        userMapper.insertUser(username, password);
    }
}
