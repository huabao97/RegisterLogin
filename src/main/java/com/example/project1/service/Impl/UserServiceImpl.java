package com.example.project1.service.Impl;

import com.example.project1.dao.UserDao;
import com.example.project1.pojo.User;
import com.example.project1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    public UserDao userDao;
    @Override
    public User getUserByName(String userName) {
        return userDao.getUserByName(userName);
    }

    @Override
    public int addUser(User user) {
        return userDao.addUser(user);
    }
}
