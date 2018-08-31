package com.xiaozhi.rbt.sys.service;

import com.xiaozhi.common.interceptor.Pager;
import com.xiaozhi.common.util.GuidUtil;
import com.xiaozhi.rbt.sys.bean.SysUserBean;
import com.xiaozhi.rbt.sys.dao.SysUserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service("sysUserService")
public class SysUserService {

    @Resource
    SysUserMapper sysUserMapper;

    public List<SysUserBean> find() {
        return sysUserMapper.find();
    }

    public List<SysUserBean> findByCondtion(SysUserBean entity) {
        entity.setIsDel(0);
        return sysUserMapper.findByCondtion(entity);
    }

    public List<SysUserBean> findListPage(Pager<SysUserBean> pager) {
        pager.getItem().setIsDel(0);
        return sysUserMapper.findListPage(pager);
    }

    public SysUserBean getByCondtion(SysUserBean entity) {
        entity.setIsDel(0);
        return sysUserMapper.getByCondtion(entity);
    }

    public void insert(SysUserBean entity) {
        entity.setId(GuidUtil.getGuid());
        //entity.setOrgId(user.getOrgId());
        //entity.setInputAcc(user.getAccount());
        entity.setInputTime(new Date());
        entity.setIsDel(0);
        sysUserMapper.insert(entity);
    }

    public void insertBatch(List<SysUserBean> entitys) {
        for (SysUserBean entity : entitys) {
            entity.setId(GuidUtil.getGuid());
            //entity.setOrgId(user.getOrgId());
            //entity.setInputAcc(user.getAccount());
            entity.setInputTime(new Date());
            entity.setIsDel(0);
        }
        sysUserMapper.insertBatch(entitys);
    }

    public void update(SysUserBean entity) {
        //entity.setUpdateAcc(user.getAccount());
        //entity.setOrgId(user.getOrgId());
        entity.setUpdateTime(new Date());
        sysUserMapper.update(entity);
    }

    public void del(SysUserBean entity) {
        update(entity);
    }
}
