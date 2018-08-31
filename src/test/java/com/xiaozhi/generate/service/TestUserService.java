package com.xiaozhi.generate.service;

import com.alibaba.fastjson.JSON;
import com.xiaozhi.BaseTests;
import com.xiaozhi.demo.user.bean.User;
import com.xiaozhi.demo.user.service.UserService;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.List;


public class TestUserService extends BaseTests {
    @Autowired
    private GenerateClass generateClass;
    @Resource
    UserService userService;

    private Logger logger = LoggerFactory.getLogger(BaseTests.class);

    @Test
    public void testFindAll() {
        List<User> users = userService.findAll();
        logger.info(JSON.toJSONString(users));
    }
}
