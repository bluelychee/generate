package com.xiaozhi;

import com.xiaozhi.common.boot.DemoApplication;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
@ComponentScan(basePackages="com.xiaozhi")
public class BaseTests {
}
