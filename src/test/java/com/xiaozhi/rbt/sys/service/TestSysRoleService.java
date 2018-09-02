package com.xiaozhi.rbt.sys.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.xiaozhi.BaseTests;
import com.xiaozhi.common.interceptor.Pager;
import org.junit.Test;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.xiaozhi.rbt.sys.bean.SysRoleBean;

public class TestSysRoleService extends BaseTests{
	@Resource
	SysRoleService sysRoleService;
	
	Logger logger = Logger.getLogger(TestSysRoleService.class);

	@Test
   	public void testInsert() {
        SysRoleBean entity = new SysRoleBean();
       	sysRoleService.insert(entity);
 	}

	@Test
  	public void testInsertBatch() {
        List<SysRoleBean> entitys = new ArrayList<SysRoleBean>();
        for(int i=0;i<100;i++){
            SysRoleBean entity = new SysRoleBean();
            entitys.add(entity);
		}
        sysRoleService.insertBatch(entitys);
	}
	
	@Test
	public void testFindByCondtion(){
       SysRoleBean entity = new SysRoleBean();
		List<SysRoleBean> data = sysRoleService.findByCondtion(entity);
		logger.info(JSON.toJSONString(data));
	}

	@Test
	public void testFindListPage(){
  		SysRoleBean item = new SysRoleBean();
		Pager<SysRoleBean> page = new Pager <SysRoleBean>();
    	page.setItem(item);
        List<SysRoleBean> data = sysRoleService.findListPage(page);
        logger.info(JSON.toJSONString(data));
    }
    
    @Test
	public void testGetByCondtion(){
		SysRoleBean item = new SysRoleBean();
		Pager<SysRoleBean> page = new Pager <SysRoleBean>();
    	page.setItem(item);
    	page.setPageSize(1);
        List<SysRoleBean> data = sysRoleService.findListPage(page);
        logger.info(JSON.toJSONString(data));
        if(data!=null && data.size()>0){
        	SysRoleBean bean = sysRoleService.getByCondtion(data.get(0));
        	logger.info(JSON.toJSONString(bean));
        }
    }
	
	@Test
	public void testUpdate(){
  		SysRoleBean item = new SysRoleBean();
		Pager<SysRoleBean> page = new Pager <SysRoleBean>();
    	page.setItem(item);
    	page.setPageSize(1);
        List<SysRoleBean> data = sysRoleService.findListPage(page);
        if(data!=null && data.size()>0){
        	sysRoleService.update(data.get(0));
        }
        logger.info(JSON.toJSONString(data));
    }
}
