package ${webPkg};

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qftx.base.shiro.ShiroUser;
import com.qftx.base.shiro.ShiroUtil;
import com.qftx.common.cached.CachedService;
import com.qftx.common.exception.ErrorCode;
import com.qftx.common.exception.RunException;
import com.qftx.common.interceptor.Pager;
import com.qftx.common.util.StringUtils;
import ${pojoPkg}.${table.beanName}Bean;
import ${servicePkg}.${table.serviceName};

@Controller
@RequestMapping("/${table.beanName?uncap_first}")
public class ${table.actionName} {
	private static final Logger logger = Logger.getLogger(${table.actionName}.class);
	
	@Resource
	${table.serviceName} ${table.serviceName?uncap_first};
	@Resource
	private CachedService cachedService;

	/**
	 * 列表页面
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request) {
		ShiroUser user = ShiroUtil.getShiroUser();
		return "/${table.beanName?uncap_first}/list";
	}

	/**
	 * 列表Json-分页
	 */
	@ResponseBody
	@RequestMapping(value = "/listPageJson")
	public Pager<${table.beanName}Bean> listPageJson(HttpServletRequest request,
			Pager<${table.beanName}Bean> pager) {
		ShiroUser user = ShiroUtil.getShiroUser();
		try {
			if(pager.getItem() == null){
				pager.setItem(new ${table.beanName}Bean());
			}
			pager.getItem().setOrgId(user.getOrgId());
			${table.serviceName?uncap_first}.findListPage(pager);
			return pager;
		} catch (Exception e) {
			throw new RunException(ErrorCode.sysError, e);
		}
	}

	/**
	 * 列表Json-不分页
	 */
	@ResponseBody
	@RequestMapping(value = "/listJson")
	public List<${table.beanName}Bean> listPageJson(HttpServletRequest request,
			${table.beanName}Bean ${table.beanName?uncap_first}Bean) {
		ShiroUser user = ShiroUtil.getShiroUser();
		try {
			${table.beanName?uncap_first}Bean.setOrgId(user.getOrgId());
			return ${table.serviceName?uncap_first}.findByCondtion(${table.beanName?uncap_first}Bean);
		} catch (Exception e) {
			throw new RunException(ErrorCode.sysError, e);
		}
	}

	/**
	 * 详情页面
	 */
	@RequestMapping("/info")
	public String info(HttpServletRequest request, ${table.beanName}Bean ${table.beanName?uncap_first}Bean) {
		ShiroUser user = ShiroUtil.getShiroUser();
		if (StringUtils.isBlank(${table.beanName?uncap_first}Bean.getId())) {
			${table.beanName?uncap_first}Bean.setOrgId(user.getOrgId());
			${table.beanName?uncap_first}Bean = ${table.serviceName?uncap_first}.getByCondtion(${table.beanName?uncap_first}Bean);
		}
		request.setAttribute("bean", ${table.beanName?uncap_first}Bean);
		return "/${table.beanName?uncap_first}/info";
	}

	/**
	 * 详情Ajax
	 */
	@ResponseBody
	@RequestMapping(value = "/infoJson")
	public ${table.beanName}Bean infoJson(HttpServletRequest request, ${table.beanName}Bean ${table.beanName?uncap_first}Bean) {
		ShiroUser user = ShiroUtil.getShiroUser();
		try {
			if (StringUtils.isBlank(${table.beanName?uncap_first}Bean.getId())) {
				${table.beanName?uncap_first}Bean.setOrgId(user.getOrgId());
				${table.beanName?uncap_first}Bean = ${table.serviceName?uncap_first}.getByCondtion(${table.beanName?uncap_first}Bean);
			}
			return ${table.beanName?uncap_first}Bean;
		} catch (Exception e) {
			throw new RunException(ErrorCode.sysError, e);
		}
	}

	/**
	 * 保存页面（包含新增和编辑）
	 */
	@RequestMapping("/save")
	public String update(HttpServletRequest request, ${table.beanName}Bean ${table.beanName?uncap_first}Bean) {
		ShiroUser user = ShiroUtil.getShiroUser();
		if (StringUtils.isBlank(${table.beanName?uncap_first}Bean.getId())) {
			${table.beanName?uncap_first}Bean.setOrgId(user.getOrgId());
			${table.beanName?uncap_first}Bean = ${table.serviceName?uncap_first}.getByCondtion(${table.beanName?uncap_first}Bean);
		}
		request.setAttribute("bean", ${table.beanName?uncap_first}Bean);
		return "/${table.beanName?uncap_first}/update";
	}

	/**
	 * 保存Ajax（包含新增和编辑）
	 */
	@ResponseBody
	@RequestMapping(value = "/saveJson")
	public void updateJson(HttpServletRequest request,
			${table.beanName}Bean ${table.beanName?uncap_first}Bean) {
		ShiroUser user = ShiroUtil.getShiroUser();
		try {
			if (StringUtils.isBlank(${table.beanName?uncap_first}Bean.getId())) {
				${table.serviceName?uncap_first}.insert(${table.beanName?uncap_first}Bean, user);
			} else {
				${table.serviceName?uncap_first}.update(${table.beanName?uncap_first}Bean, user);
			}
		} catch (Exception e) {
			throw new RunException(ErrorCode.sysError, e);
		}
	}

}