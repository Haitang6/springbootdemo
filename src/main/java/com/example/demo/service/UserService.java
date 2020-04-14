package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.entity.UserExample;
import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    public void register(User user){
        userMapper.insert(user);
    }

    public List<User> login (User user){
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andPhoneNumberEqualTo(user.getPhoneNumber())
                .andPasswordEqualTo(user.getPassword());
        List<User> users = userMapper.selectByExample(userExample);

        return users;
    }

    public void resetPwd(User user) {
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andPhoneNumberEqualTo(user.getPhoneNumber());
        List<User> users = userMapper.selectByExample(userExample);
        if (users.size()!=0){
            users.get(0).setPassword(user.getPassword());
            userMapper.updateByExample(users.get(0),userExample);
        }else {
//            输入的手机号错误。
        }
    }

    public void updateToken(User user) {
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andUidEqualTo(user.getUid());
        userMapper.updateByExample(user,userExample);
    }

    public User findByToken(String tokenValve) {
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andTokenEqualTo(tokenValve);
        List<User> users = userMapper.selectByExample(userExample);
        if (users.size()==0){
            System.out.println("没有找到");
            return null;
        }else {
            return users.get(0);
        }
    }
}
