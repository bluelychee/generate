package com.xiaozhi.common.util;

import java.util.Date;

/**
 * Created by bxl on 2017/8/9.
 */
public class InfoUtil {
    private static int queryJson = 0;
    private static int queryRandomJson = 0;
    private static int queryTodayCallJson = 0;
    private static int queryPost = 0;
    private static int queryFollow = 0;
    private static int queryTaoByCustId = 0;
    private static int queryNum = 0;
    private static int queryUserNum = 0;
    private static int quickListById = 0;
    private static int queryByCustId = 0;
    private static int queryByCode = 0;
    private static int httpTotal = 0;
    private static int httpFailTotal = 0;
    private static int jmsTotal = 0;
    private static int reviceTotal = 0;
    private static Date startDate = new Date();
    public synchronized static int getReviceTotal() {
        return reviceTotal;
    }
    public synchronized static void setReviceTotal(int size) {
        reviceTotal += size;
    }
    public synchronized static int getHttpTotal() {
        return httpTotal;
    }
    public synchronized static void setHttpTotal() {
        ++httpTotal;
    }
    public synchronized static int getHttpFailTotal() {
        return httpFailTotal;
    }
    public synchronized static void setHttpFailTotal() {
        ++httpFailTotal;
    }
    public synchronized static int getJmsTotal() {
        return jmsTotal;
    }
    public synchronized static void setJmsTotal() {
        ++jmsTotal;
    }
    public synchronized static Date getStartDate() {
        return startDate;
    }
    public synchronized static void setStartDate(Date startDate) {
        InfoUtil.startDate = startDate;
    }
    public synchronized static int getQueryByCustId() {
        return queryByCustId;
    }
    public synchronized static void setQueryByCustId() {
        ++queryByCustId;
    }
    public synchronized static int getQueryByCode() {
        return queryByCode;
    }
    public synchronized static void setQueryByCode() {
        ++queryByCode;
    }
    public synchronized static int getQueryJson() {
        return queryJson;
    }
    public synchronized static void setQueryJson() {
        ++queryJson;
    }
    public synchronized static int getQueryRandomJson() {
        return queryRandomJson;
    }
    public synchronized static void setQueryRandomJson() {
        ++queryRandomJson;
    }

    public synchronized static int getQueryTodayCallJson() {
        return queryTodayCallJson;
    }

    public synchronized static void setQueryTodayCallJson() {
        ++queryTodayCallJson;
    }

    public synchronized static int getQueryPost() {
        return queryPost;
    }

    public synchronized static void setQueryPost() {
        ++queryPost;
    }

    public synchronized static int getQueryFollow() {
        return queryFollow;
    }

    public synchronized static void setQueryFollow() {
        ++queryFollow;
    }

    public synchronized static int getQueryTaoByCustId() {
        return queryTaoByCustId;
    }

    public synchronized static void setQueryTaoByCustId() {
        ++queryTaoByCustId;
    }

    public synchronized static int getQueryNum() {
        return queryNum;
    }

    public synchronized static void setQueryNum() {
        ++queryNum;
    }

    public synchronized static int getQueryUserNum() {
        return queryUserNum;
    }

    public synchronized static void setQueryUserNum() {
        ++queryUserNum;
    }

    public synchronized static int getQuickListById() {
        return quickListById;
    }

    public synchronized static void setQuickListById() {
        ++quickListById;
    }


    public synchronized static void clear() {
        queryJson = 0;
        queryRandomJson = 0;
        queryTodayCallJson = 0;
        queryPost = 0;
        queryFollow = 0;
        queryTaoByCustId = 0;
        queryNum = 0;
        queryUserNum = 0;
        quickListById = 0;
        queryByCustId = 0;
        queryByCode = 0;
        httpTotal = 0;
        httpFailTotal = 0;
        reviceTotal = 0;
        jmsTotal = 0;
        startDate = new Date();
    }

}
