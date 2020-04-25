package com.example.demo.mapper;

import com.example.demo.entity.User;

public interface UserExtMapper {
    void incUpCount(User user);

    void incFanCount(User user);

    void decFanCount(User uploader);

    void decUpCount(User fan);
}
