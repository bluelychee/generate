package com.xiaozhi.rbt.web.sys;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaozhi.common.domain.BaseJsonResult;
import com.xiaozhi.common.exception.RunException;
import com.xiaozhi.common.interceptor.Pager;
import com.xiaozhi.common.util.StringUtils;
import com.xiaozhi.rbt.sys.bean.SysUserBean;
import com.xiaozhi.rbt.sys.service.SysUserService;

@Controller
@RequestMapping("/sys/sysUser")
public class SysUserAction {
	private static final Logger logger = Logger.getLogger(SysUserAction.class);
	
	@Resource
	SysUserService sysUserService;

	@RequestMapping(value = "/listPage")
	public String listPage(ModelMap model,SysUserBean item) throws RunException{
		return "sys/sysUser/listPage";
	}
	
	@RequestMapping(value = "/savePage")
	public String savePage(ModelMap model,String id) throws RunException{
		return "sys/sysUser/savePage";
	}
	
	@RequestMapping(value = "/infoPage")
	public String infoPage(ModelMap model,String id) throws RunException{
		return "sys/sysUser/infoPage";
	}

	/**
	 * 列表Json-分页
	 */
	@ResponseBody
	@RequestMapping(value = "/listPageJson")
	public Pager<SysUserBean> listPageJson(Pager<SysUserBean> pager) throws RunException{
		if(pager.getItem() == null){
			pager.setItem(new SysUserBean());
		}
		sysUserService.findListPage(pager);
		return pager;
	}

	/**
	 * 列表Json-不分页
	 */
	@ResponseBody
	@RequestMapping(value = "/listJson")
	public List<SysUserBean> listJson(SysUserBean item) throws RunException{
		List<SysUserBean> list = sysUserService.findByCondtion(item);
		return list;
	}

	/**
	 * 详情Ajax
	 */
	@ResponseBody
	@RequestMapping(value = "/infoJson")
	public SysUserBean infoJson(SysUserBean sysUserBean) throws RunException{
		if (!StringUtils.isBlank(sysUserBean.getId())) {
			sysUserBean = sysUserService.getByCondtion(sysUserBean);
		}
		return sysUserBean;
	}

	/**
	 * 保存Ajax（包含新增和编辑）
	 */
	@ResponseBody
	@RequestMapping(value = "/saveJson")
	public SysUserBean updateJson(SysUserBean sysUserBean) throws RunException{
		if (StringUtils.isBlank(sysUserBean.getId())) {
			sysUserService.insert(sysUserBean);
		} else {
			sysUserService.update(sysUserBean);
		}
		return sysUserBean;
	}
	
	/**
	 * 删除Ajax
	 */
	@ResponseBody
	@RequestMapping(value = "/delJson")
	public BaseJsonResult delJson(SysUserBean sysUserBean) throws RunException{
		if (StringUtils.isBlank(sysUserBean.getId())) {
			return BaseJsonResult.error("id 不能为空！");
		} else {
			sysUserBean.setIsDel(1);
			sysUserService.del(sysUserBean);
			return BaseJsonResult.success();
		}
	}

}