package com.example.project1.dao;

import com.example.project1.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
@Mapper
public interface UserDao {

    @Select("select * from user where userName=#{userName}")
    public User getUserByName(String userName);

    @Insert("insert into user(userName,password,status,registTime) values(#{userName},#{password},#{status},#{registTime}})))")
    public int addUser(User user);

}
