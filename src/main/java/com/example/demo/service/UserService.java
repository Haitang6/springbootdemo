package com.example.demo.service;

import com.example.demo.entity.Test;
import com.example.demo.entity.TestExample;
import com.example.demo.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    TestMapper testMapper;

    public void test() {


        TestExample testExample=new TestExample();
        testExample.createCriteria()
                .andUsernameEqualTo("aa");

        List<Test> tests = testMapper.selectByExample(testExample);
        System.out.println(tests);
    }
}
