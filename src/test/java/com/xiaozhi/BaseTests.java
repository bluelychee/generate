package com.xiaozhi;

import com.alibaba.fastjson.JSON;
import com.xiaozhi.demo.app.DemoApplication;
import com.xiaozhi.demo.user.bean.User;
import com.xiaozhi.demo.user.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
@ComponentScan(basePackages="com.xiaozhi")
public class BaseTests {
}
