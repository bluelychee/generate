package com.xiaozhi.generate.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;

/**
 * Create by lizhihui on 2018/8/8
 */
//@WebFilter(filterName = "customFilter",urlPatterns = {"/*"})
public class CustomFilter implements Filter {
    Logger logger = LoggerFactory.getLogger(CustomFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String s = toString();
        logger.info("CustomFilter.init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        logger.info("CustomFilter.doFilter Before");
        chain.doFilter(request, response);
        logger.info("CustomFilter.doFilter After");
    }

    @Override
    public void destroy() {
        logger.info("CustomFilter.destroy");
    }
}
