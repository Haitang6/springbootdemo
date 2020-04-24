package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.entity.UserArticle;
import com.example.demo.entity.UserArticleExample;
import com.example.demo.entity.UserExample;
import com.example.demo.mapper.UserArticleMapper;
import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    UserArticleMapper userArticleMapper;

    public User findUserByUid(String uid) {
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andUidEqualTo(uid);
        List<User> users = userMapper.selectByExample(userExample);
        return users.get(0);
    }

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

    //文章是否被当前读者喜欢
    public String isLike(String aid, HttpServletRequest request) {
        User user=(User)request.getSession().getAttribute("user");
        UserArticleExample userArticleExample = new UserArticleExample();
        userArticleExample.createCriteria()
                .andAidEqualTo(aid)
                .andUidEqualTo(user.getUid())
                .andTypeEqualTo(1);
        List<UserArticle> userArticles = userArticleMapper.selectByExample(userArticleExample);
        if (userArticles.size() == 0){
            return "false";
        }else {
            return "true";
        }
    }
    //文章是否被当前读者收藏
    public String isCollect(String aid, HttpServletRequest request) {
        User user=(User)request.getSession().getAttribute("user");
        UserArticleExample userArticleExample = new UserArticleExample();
        userArticleExample.createCriteria()
                .andAidEqualTo(aid)
                .andUidEqualTo(user.getUid())
                .andTypeEqualTo(2);
        List<UserArticle> userArticles = userArticleMapper.selectByExample(userArticleExample);
        if (userArticles.size() == 0){
            return "false";
        }else {
            return "true";
        }
    }
}
