package ${webPkg};

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qftx.base.shiro.ShiroUser;
import com.qftx.base.shiro.ShiroUtil;
import com.qftx.common.cached.CachedService;
import com.qftx.common.domain.BaseJsonResult;
import com.qftx.common.exception.RunException;
import com.qftx.common.interceptor.Pager;
import com.qftx.common.util.StringUtils;
import ${pojoPkg}.${table.beanName}Bean;
import ${servicePkg}.${table.serviceName};

@Controller
@RequestMapping("/${modulePkgName}/${table.beanName?uncap_first}")
public class ${table.actionName} {
	private static final Logger logger = Logger.getLogger(${table.actionName}.class);
	
	@Resource
	${table.serviceName} ${table.serviceName?uncap_first};
	@Resource
	private CachedService cachedService;

	@RequestMapping(value = "/listPage")
	public String listPage(ModelMap model,${table.beanName}Bean item) throws RunException{
		ShiroUser user = ShiroUtil.getShiroUser();
		return "${modulePkgName}/${table.beanName?uncap_first}/listPage";
	}
	
	@RequestMapping(value = "/savePage")
	public String savePage(ModelMap model,String id) throws RunException{
		ShiroUser user = ShiroUtil.getShiroUser();
		return "${modulePkgName}/${table.beanName?uncap_first}/savePage";
	}
	
	@RequestMapping(value = "/infoPage")
	public String infoPage(ModelMap model,String id) throws RunException{
		ShiroUser user = ShiroUtil.getShiroUser();
		return "${modulePkgName}/${table.beanName?uncap_first}/infoPage";
	}

	/**
	 * 列表Json-分页
	 */
	@ResponseBody
	@RequestMapping(value = "/listPageJson")
	public Pager<${table.beanName}Bean> listPageJson(Pager<${table.beanName}Bean> pager) throws RunException{
		ShiroUser user = ShiroUtil.getShiroUser();
		if(pager.getItem() == null){
			pager.setItem(new ${table.beanName}Bean());
		}
		pager.getItem().setOrgId(user.getOrgId());
		${table.serviceName?uncap_first}.findListPage(pager);
		return pager;
	}

	/**
	 * 列表Json-不分页
	 */
	@ResponseBody
	@RequestMapping(value = "/listJson")
	public List<${table.beanName}Bean> listJson(${table.beanName}Bean item) throws RunException{
		ShiroUser user = ShiroUtil.getShiroUser();
		item.setOrgId(user.getOrgId());
		List<${table.beanName}Bean> list = ${table.serviceName?uncap_first}.findByCondtion(item);
		return list;
	}

	/**
	 * 详情Ajax
	 */
	@ResponseBody
	@RequestMapping(value = "/infoJson")
	public ${table.beanName}Bean infoJson(${table.beanName}Bean ${table.beanName?uncap_first}Bean) throws RunException{
		ShiroUser user = ShiroUtil.getShiroUser();
		if (!StringUtils.isBlank(${table.beanName?uncap_first}Bean.getId())) {
			${table.beanName?uncap_first}Bean.setOrgId(user.getOrgId());
			${table.beanName?uncap_first}Bean = ${table.serviceName?uncap_first}.getByCondtion(${table.beanName?uncap_first}Bean);
		}
		return ${table.beanName?uncap_first}Bean;
	}

	/**
	 * 保存Ajax（包含新增和编辑）
	 */
	@ResponseBody
	@RequestMapping(value = "/saveJson")
	public ${table.beanName}Bean updateJson(${table.beanName}Bean ${table.beanName?uncap_first}Bean) throws RunException{
		ShiroUser user = ShiroUtil.getShiroUser();
		if (StringUtils.isBlank(${table.beanName?uncap_first}Bean.getId())) {
			${table.serviceName?uncap_first}.insert(${table.beanName?uncap_first}Bean, user);
		} else {
			${table.serviceName?uncap_first}.update(${table.beanName?uncap_first}Bean, user);
		}
		return ${table.beanName?uncap_first}Bean;
	}
	
	/**
	 * 删除Ajax
	 */
	@ResponseBody
	@RequestMapping(value = "/delJson")
	public BaseJsonResult delJson(${table.beanName}Bean ${table.beanName?uncap_first}Bean) throws RunException{
		ShiroUser user = ShiroUtil.getShiroUser();
		if (StringUtils.isBlank(${table.beanName?uncap_first}Bean.getId())) {
			return BaseJsonResult.error("id 不能为空！");
		} else {
			${table.beanName?uncap_first}Bean.setIsDel(1);
			${table.serviceName?uncap_first}.del(${table.beanName?uncap_first}Bean, user);
			return BaseJsonResult.success();
		}
	}

}