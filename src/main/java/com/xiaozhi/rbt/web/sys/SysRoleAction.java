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
import com.xiaozhi.rbt.sys.bean.SysRoleBean;
import com.xiaozhi.rbt.sys.service.SysRoleService;

@Controller
@RequestMapping("/sys/sysRole")
public class SysRoleAction {
	private static final Logger logger = Logger.getLogger(SysRoleAction.class);
	
	@Resource
	SysRoleService sysRoleService;

	@RequestMapping(value = "/listPage")
	public String listPage(ModelMap model,SysRoleBean item) throws RunException{
		return "sys/sysRole/listPage";
	}
	
	@RequestMapping(value = "/savePage")
	public String savePage(ModelMap model,String id) throws RunException{
		return "sys/sysRole/savePage";
	}
	
	@RequestMapping(value = "/infoPage")
	public String infoPage(ModelMap model,String id) throws RunException{
		return "sys/sysRole/infoPage";
	}

	/**
	 * 列表Json-分页
	 */
	@ResponseBody
	@RequestMapping(value = "/listPageJson")
	public Pager<SysRoleBean> listPageJson(Pager<SysRoleBean> pager) throws RunException{
		if(pager.getItem() == null){
			pager.setItem(new SysRoleBean());
		}
		sysRoleService.findListPage(pager);
		return pager;
	}

	/**
	 * 列表Json-不分页
	 */
	@ResponseBody
	@RequestMapping(value = "/listJson")
	public List<SysRoleBean> listJson(SysRoleBean item) throws RunException{
		List<SysRoleBean> list = sysRoleService.findByCondtion(item);
		return list;
	}

	/**
	 * 详情Ajax
	 */
	@ResponseBody
	@RequestMapping(value = "/infoJson")
	public SysRoleBean infoJson(SysRoleBean sysRoleBean) throws RunException{
		if (!StringUtils.isBlank(sysRoleBean.getId())) {
			sysRoleBean = sysRoleService.getByCondtion(sysRoleBean);
		}
		return sysRoleBean;
	}

	/**
	 * 保存Ajax（包含新增和编辑）
	 */
	@ResponseBody
	@RequestMapping(value = "/saveJson")
	public SysRoleBean updateJson(SysRoleBean sysRoleBean) throws RunException{
		if (StringUtils.isBlank(sysRoleBean.getId())) {
			sysRoleService.insert(sysRoleBean);
		} else {
			sysRoleService.update(sysRoleBean);
		}
		return sysRoleBean;
	}
	
	/**
	 * 删除Ajax
	 */
	@ResponseBody
	@RequestMapping(value = "/delJson")
	public BaseJsonResult delJson(SysRoleBean sysRoleBean) throws RunException{
		if (StringUtils.isBlank(sysRoleBean.getId())) {
			return BaseJsonResult.error("id 不能为空！");
		} else {
			sysRoleBean.setIsDel(1);
			sysRoleService.del(sysRoleBean);
			return BaseJsonResult.success();
		}
	}

}