package com.example.demo.service;

import com.example.demo.entity.Test;
import com.example.demo.entity.TestExample;
import com.example.demo.entity.User;
import com.example.demo.entity.UserExample;
import com.example.demo.mapper.TestMapper;
import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    TestMapper testMapper;

    @Autowired
    UserMapper userMapper;
    public void test() {

        TestExample testExample=new TestExample();
        testExample.createCriteria()
                .andUsernameEqualTo("aa");

        List<Test> tests = testMapper.selectByExample(testExample);
        System.out.println(tests);
    }

    public void register(User user){
        UserExample userExample=new UserExample();
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
}
