package com.xiaozhi.generate;

import com.xiaozhi.generate.user.bean.User;
import com.xiaozhi.generate.user.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Create by lizhihui on 2018/8/8
 */

@Controller
public class SessionAction {

    @Value("${app.server.id}")
    private String serverName;

    @Resource
    JdbcTemplate jdbcTemplate;

    @Resource
    UserService userService;

    @RequestMapping("/")
    @ResponseBody
    public String Hello(HttpSession session){

        System.out.println(serverName + "Request!");

        return serverName+"\t"+session.getId();
    }

    @RequestMapping("/time")
    @ResponseBody
    public String time(HttpSession session){



        return jdbcTemplate.queryForObject("select now()", String.class);
    }

    @RequestMapping("/user")
    @ResponseBody
    public List<User> user(HttpSession session){
        return userService.findAll();
    }

    @RequestMapping("/hello")
    @ResponseBody
    public String Hello1(HttpSession session) throws Exception {
        throw new Exception("系统异常！");
    }

}
