package com.xiaozhi.common.interceptor;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties({ "item"})
public class Pager<T> {
    private int pageSize = 10;//一页显示记录数
    private int currentPage = 1;//当前页
    private int intPosition; // 记录游标
    
    private T item;//查询对象
    private List<T> list = new ArrayList<T>();
    private int totalRecord;      //总记录数
    private int totalPage;        //总页数  

    public int getIntPosition() {
        return intPosition;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public Pager() {
    }

    public Pager(int page, int pageSize, int totalRecord) {
        this.setTotalRecord(totalRecord);
        this.setCurrentPage(page);
        this.setPageSize(pageSize);
    }

    /*
    * 计算有关分页参数
    */
    private void init() {
        if (currentPage <= 0) {
            this.currentPage = 1;
        }
        if (totalRecord <= 0) {
            this.totalRecord = 0;
        }
        this.totalPage = (int) Math.ceil((double) totalRecord / (double) pageSize);
        if (currentPage > totalPage&&totalPage!=0) {
            this.currentPage = totalPage;
        }
        this.intPosition = (currentPage - 1) * pageSize;
    }


    public int getCurrentPage() {
        return currentPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
        if (pageSize <= 0) {
            pageSize = totalRecord;
        } else {
            this.pageSize = pageSize;
        }

    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
        init();
    }

     public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
        init();
    }

	public T getItem() {
		return item;
	}

	public void setItem(T item) {
		this.item = item;
	}
}
