package com.xiaozhi.rbt.sys.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiaozhi.common.interceptor.Pager;
import com.xiaozhi.common.util.GuidUtil;
import com.xiaozhi.rbt.sys.bean.SysRoleBean;
import com.xiaozhi.rbt.sys.dao.SysRoleMapper;

@Service("sysRoleService")
public class SysRoleService{
	
	@Resource
	SysRoleMapper sysRoleMapper;
	
	public List<SysRoleBean> find(){
		return sysRoleMapper.find();
	}

	public List<SysRoleBean> findByCondtion(SysRoleBean entity){
		entity.setIsDel(0);
		return sysRoleMapper.findByCondtion(entity);
	}
	
	public List<SysRoleBean> findListPage(Pager<SysRoleBean> pager){
		pager.getItem().setIsDel(0);
		return sysRoleMapper.findListPage(pager);
	}
	
	public SysRoleBean getByCondtion(SysRoleBean entity){
		entity.setIsDel(0);
		return sysRoleMapper.getByCondtion(entity);
	}

	public void insert(SysRoleBean entity){
		entity.setId(GuidUtil.getGuid());
		entity.setInputTime(new Date());
		entity.setIsDel(0);
		sysRoleMapper.insert(entity);
	}

	public void insertBatch(List<SysRoleBean> entitys){
		for (SysRoleBean entity : entitys) {
			entity.setId(GuidUtil.getGuid());
			entity.setInputTime(new Date());
			entity.setIsDel(0);
		}
		sysRoleMapper.insertBatch(entitys);
	}

	public void update(SysRoleBean entity){
		entity.setUpdateTime(new Date());
		sysRoleMapper.update(entity);
	}
	
	public void del(SysRoleBean entity){
		update(entity);
	}
}
