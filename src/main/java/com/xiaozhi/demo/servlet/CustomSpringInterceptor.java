package com.xiaozhi.demo.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Create by lizhihui on 2018/8/9
 */
public class CustomSpringInterceptor implements HandlerInterceptor {
    private Logger logger = LoggerFactory.getLogger(CustomSpringInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("CustomSpringInterceptor.preHandle");
       /* if(request.getParameter("name")!=null){
            return true;
        }else{
            return false;
        }
*/
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        logger.info("CustomSpringInterceptor.postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        logger.info("CustomSpringInterceptor.afterCompletion");
    }
}
