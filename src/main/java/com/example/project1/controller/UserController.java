package com.example.project1.controller;

import com.example.project1.pojo.User;
import com.example.project1.service.UserService;
import com.example.project1.util.Md5Util;
import com.mysql.cj.util.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.ognl.ObjectElementsAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.net.http.HttpResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Api("用户接口")
@CrossOrigin
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @RequestMapping("/test")
    @ResponseBody
    public String getData(){
        return "string";
    }


    @RequestMapping(value = "/register",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "用户注册",notes = "用户注册")
    public Map<String,Object> register(String userName,String passwd){
        System.out.println(userName+passwd);
        Map<String, Object> map=new HashMap<>();
        if(StringUtils.isNullOrEmpty(userName)||StringUtils.isNullOrEmpty(passwd)){
        map.put("msg","用户名不能为空");
        return map;
        }
        //验证用户名是否已经注册
        User userexist=userService.getUserByName(userName);
        if(userexist!=null){
            map.put("msg","该用户名已经注册");
            return map;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format=sdf.format(new Date());
        User user=new User();
        user.setUserName(userName);

        user.setStatus("1");
        user.setRegistTime(format);
        user.setPasswd(Md5Util.MD5(passwd)+userName);
        int count=userService.addUser(user);
        System.out.println(count);
        if(count==1){
            map.put("msg","注册失败");
            return map;

        }

        map.put("msg","注册成功");
        return map;

    }


    @ResponseBody
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ApiOperation(value="用户登录",notes = "用户登录")
    @CrossOrigin
    public Map<String,Object>login(@RequestParam String userName,@RequestParam String passwd, HttpSession session, HttpResponse response){
        System.out.println(userName+passwd);
        Map<String, Object> map=new HashMap<>();
        User exitUser=userService.getUserByName(userName);
        if(exitUser==null){
            map.put("msg","该用户未注册");
            return map;
        }
        if(!exitUser.getPasswd().equals(Md5Util.MD5(passwd)+userName)){
            map.put("msg","用户输入的密码有误");
            return map;
        }
        session.setAttribute("loginUser",exitUser);
        map.put("msg","登录成功");
        return map;
    }
}
