package com.xiaozhi.common.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Create by lizhihui on 2018/8/9
 */
@WebListener
public class CustomListener implements ServletContextListener {
    private  Logger logger = LoggerFactory.getLogger(CustomListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.info("contenxt init");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.info("context destory");
    }
}
