package com.xiaozhi.generate;

import com.alibaba.fastjson.JSON;
import com.xiaozhi.generate.user.bean.User;
import com.xiaozhi.generate.user.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JsonContent;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Resource
	UserService userService;
	
	private Logger logger = LoggerFactory.getLogger(DemoApplicationTests.class);
	
	@Test
	public void testFindAll() {
		List<User> users = userService.findAll();
		logger.info(JSON.toJSONString(users));
	}

}
