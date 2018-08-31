package com.xiaozhi.common.interceptor;

import java.io.Serializable;

/**
 * 分页对象
 *
 * @author yhq [unicorn668@163.com] 创建时间：2012-11-2 上午11:27:12
 * @version V1.0
 */
public class Page implements Serializable {
    private static final long serialVersionUID = 1L;
    private int showCount = 10; // 每页显示记录数
    private int totalPage; // 总页数
    private int totalResult; // 总记录数
    private int currentPage; // 当前页
    private int currentResult; // 当前记录起始索引

    public int getTotalPage() {
        if (totalResult % showCount == 0)
            totalPage = totalResult / showCount;
        else
            totalPage = totalResult / showCount + 1;
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalResult() {
        return totalResult;
    }

    public void setTotalResult(int totalResult) {
        this.totalResult = totalResult;
    }

    public int getCurrentPage() {
        if (currentPage <= 0)
            currentPage = 1;
        if (currentPage > getTotalPage())
            currentPage = getTotalPage();
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getShowCount() {
        return showCount;
    }

    public void setShowCount(int showCount) {
        this.showCount = showCount;
    }

    public int getCurrentResult() {
        currentResult = (getCurrentPage() - 1) * getShowCount();
        if (currentResult < 0)
            currentResult = 0;
        return currentResult;
    }

    public void setCurrentResult(int currentResult) {
        this.currentResult = currentResult;
    }


}