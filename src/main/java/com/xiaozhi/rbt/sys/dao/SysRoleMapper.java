package com.xiaozhi.rbt.sys.dao;

import java.util.List;

import com.xiaozhi.common.interceptor.Pager;
import com.xiaozhi.rbt.sys.bean.SysRoleBean;

public interface SysRoleMapper{
	public List<SysRoleBean> find();

	public List<SysRoleBean> findByCondtion(SysRoleBean entity);
	
	public List<SysRoleBean> findListPage(Pager<SysRoleBean> pager);
	
	public SysRoleBean getByCondtion(SysRoleBean entity);
	
	public void insert(SysRoleBean entity);

	public void insertBatch(List<SysRoleBean> entitys);

	public void update(SysRoleBean entity);
}
