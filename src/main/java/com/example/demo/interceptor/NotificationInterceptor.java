package com.example.demo.interceptor;

import com.example.demo.dto.NotificationDto;
import com.example.demo.entity.Notification;
import com.example.demo.entity.NotificationExample;
import com.example.demo.entity.User;
import com.example.demo.enums.NotificationStatusEnum;
import com.example.demo.enums.NotificationTypeEnum;
import com.example.demo.mapper.NotificationMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Component
public class NotificationInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    NotificationMapper notificationMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取当前登陆者
        User user = (User) request.getSession().getAttribute("user");
        if (user== null){
            return true;
        }else {
            //通知功能
            NotificationExample notificationExample = new NotificationExample();
            notificationExample.createCriteria()
                    .andReceiverEqualTo(user.getUid())
                    .andNotifiedStatusEqualTo(NotificationStatusEnum.UNREAD.getType());
            List<Notification> notifications = notificationMapper.selectByExample(notificationExample);
            List<NotificationDto> notificationDtos=new ArrayList<>();
            for (Notification notification:notifications){
                NotificationDto notificationDto = new NotificationDto();
                BeanUtils.copyProperties(notification,notificationDto);
                if (notification.getNotifiedType()== NotificationTypeEnum.COMMENT.getType()){
                    notificationDto.setType(NotificationTypeEnum.COMMENT.getDescribe());
                }else if (notification.getNotifiedType()==NotificationTypeEnum.ATTENTION.getType()){
                    notificationDto.setType(NotificationTypeEnum.ATTENTION.getDescribe());
                }else if (notification.getNotifiedType()==NotificationTypeEnum.LIKE.getType()){
                    notificationDto.setType(NotificationTypeEnum.LIKE.getDescribe());
                }else if (notification.getNotifiedType()==NotificationTypeEnum.COLLECTION.getType()){
                    notificationDto.setType(NotificationTypeEnum.COLLECTION.getDescribe());
                }
                notificationDtos.add(notificationDto);
            }
            //通知个数
            request.getSession().setAttribute("notificationCount",notificationDtos.size());
            request.getSession().setAttribute("notificationDtos",notificationDtos);
            return true;
        }
    }
}
