package com.xiaozhi.rbt.sys.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.xiaozhi.BaseTests;
import com.xiaozhi.common.interceptor.Pager;
import org.junit.Test;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.xiaozhi.rbt.sys.bean.SysUserBean;

public class TestSysUserService extends BaseTests {
	@Resource
	SysUserService sysUserService;
	
	Logger logger = Logger.getLogger(TestSysUserService.class);

	@Test
   	public void testInsert() {
        SysUserBean entity = new SysUserBean();
       	sysUserService.insert(entity);
 	}

	@Test
  	public void testInsertBatch() {
        List<SysUserBean> entitys = new ArrayList<SysUserBean>();
        for(int i=0;i<100;i++){
            SysUserBean entity = new SysUserBean();
            entitys.add(entity);
		}
        sysUserService.insertBatch(entitys);
	}
	
	@Test
	public void testFindByCondtion(){
       SysUserBean entity = new SysUserBean();
		List<SysUserBean> data = sysUserService.findByCondtion(entity);
		logger.info(JSON.toJSONString(data));
	}

	@Test
	public void testFindListPage(){
  		SysUserBean item = new SysUserBean();
		Pager<SysUserBean> page = new Pager<SysUserBean>();
    	page.setItem(item);
        List<SysUserBean> data = sysUserService.findListPage(page);
        logger.info(JSON.toJSONString(data));
    }
    
    @Test
	public void testGetByCondtion(){
		SysUserBean item = new SysUserBean();
		Pager<SysUserBean> page = new Pager <SysUserBean>();
    	page.setItem(item);
    	page.setPageSize(1);
        List<SysUserBean> data = sysUserService.findListPage(page);
        logger.info(JSON.toJSONString(data));
        if(data!=null && data.size()>0){
        	SysUserBean bean = sysUserService.getByCondtion(data.get(0));
        	logger.info(JSON.toJSONString(bean));
        }
    }
	
	@Test
	public void testUpdate(){
  		SysUserBean item = new SysUserBean();
		Pager<SysUserBean> page = new Pager <SysUserBean>();
    	page.setItem(item);
    	page.setPageSize(1);
        List<SysUserBean> data = sysUserService.findListPage(page);
        if(data!=null && data.size()>0){
        	sysUserService.update(data.get(0));
        }
        logger.info(JSON.toJSONString(data));
    }
}
