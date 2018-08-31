package com.xiaozhi.rbt.sys.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiaozhi.common.interceptor.Pager;
import com.xiaozhi.common.util.GuidUtil;
import com.xiaozhi.rbt.sys.bean.SysUserBean;
import com.xiaozhi.rbt.sys.dao.SysUserMapper;

@Service("sysUserService")
public class SysUserService{
	
	@Resource
	SysUserMapper sysUserMapper;
	
	public List<SysUserBean> find(){
		return sysUserMapper.find();
	}

	public List<SysUserBean> findByCondtion(SysUserBean entity){
		entity.setIsDel(0);
		return sysUserMapper.findByCondtion(entity);
	}
	
	public List<SysUserBean> findListPage(Pager<SysUserBean> pager){
		pager.getItem().setIsDel(0);
		return sysUserMapper.findListPage(pager);
	}
	
	public SysUserBean getByCondtion(SysUserBean entity){
		entity.setIsDel(0);
		return sysUserMapper.getByCondtion(entity);
	}

	public void insert(SysUserBean entity){
		entity.setId(GuidUtil.getGuid());
		entity.setInputTime(new Date());
		entity.setIsDel(0);
		sysUserMapper.insert(entity);
	}

	public void insertBatch(List<SysUserBean> entitys){
		for (SysUserBean entity : entitys) {
			entity.setId(GuidUtil.getGuid());
			entity.setInputTime(new Date());
			entity.setIsDel(0);
		}
		sysUserMapper.insertBatch(entitys);
	}

	public void update(SysUserBean entity){
		entity.setUpdateTime(new Date());
		sysUserMapper.update(entity);
	}
	
	public void del(SysUserBean entity){
		update(entity);
	}
}
