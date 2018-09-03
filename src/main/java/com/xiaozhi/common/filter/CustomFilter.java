package com.xiaozhi.common.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Create by lizhihui on 2018/8/8
 */
//@WebFilter(filterName = "customFilter",urlPatterns = {"/*"})
public class CustomFilter implements Filter {
    Logger logger = LoggerFactory.getLogger(CustomFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.debug("CustomFilter.init");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = null;
        if(req instanceof HttpServletRequest) {
            request = (HttpServletRequest)req;

            logger.info(request.getRequestURI());
        }
        logger.debug("CustomFilter.doFilter Before");
        chain.doFilter(request, response);
        logger.debug("CustomFilter.doFilter After");
    }

    @Override
    public void destroy() {
        logger.debug("CustomFilter.destroy");
    }
}
