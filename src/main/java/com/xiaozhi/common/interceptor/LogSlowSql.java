package com.xiaozhi.common.interceptor;


import com.xiaozhi.common.util.InfoUtil;
import org.apache.log4j.Logger;

/**
 * UserBean： bxl
 * Date： 2016/3/25
 * Time： 15:28
 */

public class LogSlowSql {
    private static final Logger logger = Logger.getLogger(LogSlowSql.class.getName());

    public static void logSql(long time, String sql) throws Throwable {
        if (time >= 1000) {
            InfoUtil.setQueryUserNum();
            logger.debug("\n执行方法:" + sql);
            if (time >= 5000) {
                InfoUtil.setQueryRandomJson();
            }
        }
    }
}