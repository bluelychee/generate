package com.xiaozhi.generate.user.service;

import com.xiaozhi.generate.user.bean.User;
import com.xiaozhi.generate.user.dao.UserMapper;
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
