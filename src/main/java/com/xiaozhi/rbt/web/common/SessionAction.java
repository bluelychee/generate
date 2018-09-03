package com.xiaozhi.rbt.web.common;

import com.xiaozhi.common.exception.AuthExcepiton;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * Create by lizhihui on 2018/8/8
 */

@Controller
public class SessionAction {
    @Value("${app.server.id}")
    private String serverName;
    @Resource
    JdbcTemplate jdbcTemplate;

    private static Log logger = LogFactory.getLog(SessionAction.class);

    @RequestMapping("/index")
    @ResponseBody
    public String Hello(HttpSession session) {
        logger.info(serverName + "Request!");
        return serverName + "\t" + session.getId();
    }

    @RequestMapping("/time")
    @ResponseBody
    public String time(HttpSession session) {
        return jdbcTemplate.queryForObject("select now()", String.class);
    }

    @RequestMapping("/hello")
    @ResponseBody
    public String Hello1(HttpSession session) throws Exception {
        throw new Exception("系统异常！");
    }

    @RequestMapping(value = {"/login","/"},method = RequestMethod.GET)
    public String loginGet() {
        return "login";
    }

    @RequestMapping(value = {"/login"},method = RequestMethod.POST)
    public String loginPost(String username, String password, ModelMap model) {
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(username, password.toCharArray());
            Subject user = SecurityUtils.getSubject();
            token.setRememberMe(true);
            user.login(token);
            return "redirect:/index";
        }catch (UnknownAccountException e) {
            model.addAttribute("message", "账号不存在!");
            logger.debug(e.getMessage(), e);
            return "/login";
        } catch (IncorrectCredentialsException e) {
            model.addAttribute("message", "密码错误!");
            logger.debug(e.getMessage(), e);
            return "/login";
        } catch (LockedAccountException e) {
            model.addAttribute("message", "账号被锁定!");
            logger.debug(e.getMessage(), e);
            return "/login";
        } catch (Exception e) {
            model.addAttribute("message", "未知错误,请联系管理员.");
            logger.debug(e.getMessage(), e);
            return "/login";
        }
    }
}
