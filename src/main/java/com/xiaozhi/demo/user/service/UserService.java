package com.xiaozhi.demo.user.service;

import com.xiaozhi.demo.user.bean.User;
import com.xiaozhi.demo.user.dao.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Create by lizhihui on 2018/8/9
 */
@Service
public class UserService {

    @Resource
    UserMapper userMapper;

    public List<User> findAll(){
        return userMapper.find();
    }
}
