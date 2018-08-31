package com.xiaozhi.rbt.sys.dao;

import java.util.List;

import com.xiaozhi.common.interceptor.Pager;
import com.xiaozhi.rbt.sys.bean.SysUserBean;

public interface SysUserMapper{
	public List<SysUserBean> find();

	public List<SysUserBean> findByCondtion(SysUserBean entity);
	
	public List<SysUserBean> findListPage(Pager<SysUserBean> pager);
	
	public SysUserBean getByCondtion(SysUserBean entity);
	
	public void insert(SysUserBean entity);

	public void insertBatch(List<SysUserBean> entitys);

	public void update(SysUserBean entity);
}
