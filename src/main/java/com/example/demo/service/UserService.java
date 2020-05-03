package com.example.demo.service;

import com.example.demo.dto.ArticleDto;
import com.example.demo.entity.*;
import com.example.demo.enums.ArticleStatusEnum;
import com.example.demo.enums.NotificationStatusEnum;
import com.example.demo.enums.NotificationTypeEnum;
import com.example.demo.enums.UserArticleTypeEnum;
import com.example.demo.mapper.*;
import com.example.demo.utils.DataUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.text.Format;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.text.SimpleDateFormat;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    UserArticleMapper userArticleMapper;
    @Autowired
    UpFanMapper upFanMapper;
    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    UserExtMapper userExtMapper;
    @Autowired
    NotificationMapper notificationMapper;

    public User findUserByUid(String uid) {
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andUidEqualTo(uid);
        List<User> users = userMapper.selectByExample(userExample);
        return users.get(0);
    }

    public void register(User user){
        user.setGmtCreate(new Date());
        user.setUid(UUID.randomUUID().toString());
        user.setUpCount(0);
        user.setFanCount(0);
        user.setFinishedArticleCount(0);
        user.setUnfinishedArticleCount(0);
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
            return UserArticleTypeEnum.UNLIKE.getDescription();
        }else {
            return UserArticleTypeEnum.LIKE.getDescription();
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
            return UserArticleTypeEnum.UNCOLLECTION.getDescription();
        }else {
            return UserArticleTypeEnum.COLLECTION.getDescription();
        }
    }
    //文章的作者是否被当前读者关注
    public String idAttention(String aid, HttpServletRequest request) {
        //根据当前文章获取文章的作者
        ArticleExample articleExample = new ArticleExample();
        articleExample.createCriteria()
                .andAidEqualTo(aid);
        List<Article> articles = articleMapper.selectByExample(articleExample);
        //获取当前登陆者
        User user = (User)request.getSession().getAttribute("user");
        // 查询up_fan数据库
        UpFanExample upFanExample = new UpFanExample();
        upFanExample.createCriteria()
                .andUpidEqualTo(articles.get(0).getUid())
                .andFanidEqualTo(user.getUid());
        List<UpFan> upFans = upFanMapper.selectByExample(upFanExample);
        if (upFans.size()==0){
            return "false";
        }else {
            return "true";
        }
    }

    public void upAndFanInc(UpFan upFan, HttpServletRequest request) {
        //关注功能实现，添加到数据库。
        User user=(User)request.getSession().getAttribute("user");
        upFan.setFanid(user.getUid());
        upFanMapper.insert(upFan);
        //up主的粉丝+1
        User uploader = new User();
        uploader.setFanCount(1);
        uploader.setUid(upFan.getUpid());
        userExtMapper.incFanCount(uploader);
        //当前的读者的关注人数+1
        User fan=new User();
        fan.setUid(user.getUid());
        fan.setUpCount(1);
        userExtMapper.incUpCount(fan);
        //发送关注通知
        Notification notification = new Notification();
        notification.setNid(UUID.randomUUID().toString());
        notification.setNotifier(user.getUid());
        notification.setReceiver(upFan.getUpid());
        notification.setNotifierName(user.getPetName());
        notification.setNotifiedType(NotificationTypeEnum.ATTENTION.getType());
        notification.setGmtCreate(new Date());
        notification.setNotifiedStatus(NotificationStatusEnum.UNREAD.getType());
        //通知存放到数据库
        notificationMapper.insert(notification);

    }

    public void upAndFanDel(UpFan upFan, HttpServletRequest request) {
        //取消关注，删除数据库信息。
        User user=(User)request.getSession().getAttribute("user");
        UpFanExample upFanExample = new UpFanExample();
        upFanExample.createCriteria()
                .andFanidEqualTo(user.getUid())
                .andUpidEqualTo(upFan.getUpid());
        upFanMapper.deleteByExample(upFanExample);
        //up主的粉丝-1
        User uploader = new User();
        uploader.setFanCount(1);
        uploader.setUid(upFan.getUpid());
        userExtMapper.decFanCount(uploader);
        //当前的读者关注的人数-1
        User fan=new User();
        fan.setUid(user.getUid());
        fan.setUpCount(1);
        userExtMapper.decUpCount(fan);
    }


    public String isAuthor(String aid,HttpServletRequest request) {
        //当前文章的作者
        ArticleExample articleExample = new ArticleExample();
        articleExample.createCriteria()
                .andAidEqualTo(aid);
        List<Article> articles = articleMapper.selectByExample(articleExample);
        //当前浏览者
        User user=(User) request.getSession().getAttribute("user");
        if (articles.get(0).getUid().equals(user.getUid())){
            return "true";
        }else {
            return "false";
        }

    }
    //根据当前登陆者查询文章
    public List<Article> findArticle(User user, String isFinished) {
        ArticleExample articleExample = new ArticleExample();
        articleExample.createCriteria()
                .andUidEqualTo(user.getUid())
                .andIsFinishedEqualTo(isFinished);
        List<Article> articles = articleMapper.selectByExample(articleExample);
        return articles;
    }
    //查找我的关注
    public List<User> findUploader(User user) {
        UpFanExample upFanExample = new UpFanExample();
        upFanExample.createCriteria()
                .andFanidEqualTo(user.getUid());
        List<UpFan> uploader = upFanMapper.selectByExample(upFanExample);
        List<User> uploaders=new ArrayList<>();
        //获取uploader的个人信息
        for (UpFan upFan:uploader){
            UserExample userExample = new UserExample();
            userExample.createCriteria()
                    .andUidEqualTo(upFan.getUpid());
            List<User> users = userMapper.selectByExample(userExample);
            uploaders.add(users.get(0));
        }
        return uploaders;
    }
    //查找我的粉丝
    public List<User> findFan(User user) {
        UpFanExample upFanExample = new UpFanExample();
        upFanExample.createCriteria()
                .andUpidEqualTo(user.getUid());
        List<UpFan> fan = upFanMapper.selectByExample(upFanExample);
        List<User> fans=new ArrayList<>();
        //获取粉丝的个人信息
        for (UpFan upFan:fan){
            UserExample userExample = new UserExample();
            userExample.createCriteria()
                    .andUidEqualTo(upFan.getFanid());
            List<User> users = userMapper.selectByExample(userExample);
            fans.add(users.get(0));
        }
        return fans;
    }

    //查找uploader的文章(包含文章是否被点赞关注)
    public List<ArticleDto> findArticleDto(User uploader, HttpServletRequest request) {
        List<ArticleDto> articleDtos=new ArrayList<>();
        //uploader的文章
        List<Article> articles = findArticle(uploader, ArticleStatusEnum.FINISHED.getType());
        //遍历文章，看当前文章读者是否给他点赞 关注
        for (Article article:articles){
            ArticleDto articleDto = new ArticleDto();
            BeanUtils.copyProperties(article,articleDto);
            //时间转换
            articleDto.setGmtCreate(DataUtils.dateToString(article.getGmtCreate(),"yyyy-MM-dd HH:mm:ss"));
            Timestamp timestamp = new Timestamp(article.getGmtCreate().getTime());
            long timestampTime = timestamp.getTime();
            articleDto.setTimestampTime(timestampTime);
            //是否点赞
            String islike = isLike(article.getAid(), request);
            if (UserArticleTypeEnum.LIKE.getDescription().equals(islike)){
                articleDto.setIsLike(UserArticleTypeEnum.LIKE.getDescription());
            }else if (UserArticleTypeEnum.UNLIKE.getDescription().equals(islike)){
                articleDto.setIsLike(UserArticleTypeEnum.UNLIKE.getDescription());
            }
            //是否收藏
            String isCollect = isCollect(article.getAid(), request);
            if (UserArticleTypeEnum.COLLECTION.getDescription().equals(isCollect)){
                articleDto.setIsCollection(UserArticleTypeEnum.COLLECTION.getDescription());
            }else if (UserArticleTypeEnum.UNCOLLECTION.getDescription().equals(isCollect)){
                articleDto.setIsCollection(UserArticleTypeEnum.UNCOLLECTION.getDescription());
            }
            articleDtos.add(articleDto);
        }
        return articleDtos;
    }
}
