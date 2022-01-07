package com.example.project1.service;

import com.example.project1.pojo.User;

public interface UserService {

    public User getUserByName(String userName);

    public int addUser(User user);
}
