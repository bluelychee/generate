package com.xiaozhi.common.domain;

import com.alibaba.fastjson.annotation.JSONType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;


/**
 * 基类
 *
 * @author yhq [unicorn668@163.com] 创建时间：2012-11-2 上午10:22:20
 * @version V1.0
 */
@JsonIgnoreProperties({"sysType", "orderKey", "groupKey", "createTime", "startDate", "endDate", "page"})
@JSONType(ignores = {"sysType", "orderKey", "groupKey", "createTime", "startDate", "endDate", "page"})
public abstract class BaseObject implements Serializable {
    private static final long serialVersionUID = 1L;
    private String orderKey;// 排序字段串用英文逗号分隔
    private String groupKey;// 聚合字段
    private String startDate; //查询开始时间
    private String endDate;   //查询结束时间
    private String limitKey;

    public String getOrderKey() {
        return orderKey;
    }

    public void setOrderKey(String orderKey) {
        this.orderKey = orderKey;
    }

    public String getGroupKey() {
        return groupKey;
    }

    public void setGroupKey(String groupKey) {
        this.groupKey = groupKey;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getLimitKey() {
        return limitKey;
    }

    public void setLimitKey(String limitKey) {
        this.limitKey = limitKey;
    }
}
