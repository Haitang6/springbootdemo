package com.example.demo.service;

import com.example.demo.entity.Notification;
import com.example.demo.entity.NotificationExample;
import com.example.demo.enums.NotificationStatusEnum;
import com.example.demo.enums.NotificationTypeEnum;
import com.example.demo.mapper.NotificationMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServeice {
    @Autowired
    NotificationMapper notificationMapper;


    //把通知的未读改成已读
    public void read(String nid) {
        //根据nid查找通知
        NotificationExample notificationExample = new NotificationExample();
        notificationExample.createCriteria()
                .andNidEqualTo(nid);
        List<Notification> notifications = notificationMapper.selectByExample(notificationExample);
        //更新
        Notification notification = new Notification();
        BeanUtils.copyProperties(notifications.get(0),notification);
        notification.setNotifiedStatus(NotificationStatusEnum.READ.getType());
        notificationMapper.updateByExample(notification,notificationExample);
    }

    public String findAidByNid(String nid) {
        NotificationExample notificationExample = new NotificationExample();
        notificationExample.createCriteria()
                .andNidEqualTo(nid);
        List<Notification> notifications = notificationMapper.selectByExample(notificationExample);
        return notifications.get(0).getAid();

    }
}
