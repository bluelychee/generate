package ${servicePkg};

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xiaozhi.common.interceptor.Pager;
import com.xiaozhi.common.util.GuidUtil;
import ${pojoPkg}.${table.beanName}Bean;
import ${daoPkg}.${table.mapperName};

@Service("${table.serviceName?uncap_first}")
public class ${table.serviceName}{
	
	@Resource
	${table.beanName}Mapper ${table.mapperName?uncap_first};
	
	public List<${table.beanName}Bean> find(){
		return ${table.mapperName?uncap_first}.find();
	}

	public List<${table.beanName}Bean> findByCondtion(${table.beanName}Bean entity){
		entity.setIsDel(0);
		return ${table.mapperName?uncap_first}.findByCondtion(entity);
	}
	
	public List<${table.beanName}Bean> findListPage(Pager<${table.beanName}Bean> pager){
		pager.getItem().setIsDel(0);
		return ${table.mapperName?uncap_first}.findListPage(pager);
	}
	
	public ${table.beanName}Bean getByCondtion(${table.beanName}Bean entity){
		entity.setIsDel(0);
		return ${table.mapperName?uncap_first}.getByCondtion(entity);
	}

	public void insert(${table.beanName}Bean entity){
		entity.setId(GuidUtil.getGuid());
		entity.setInputTime(new Date());
		entity.setIsDel(0);
		${table.mapperName?uncap_first}.insert(entity);
	}

	public void insertBatch(List<${table.beanName}Bean> entitys){
		for (${table.beanName}Bean entity : entitys) {
			entity.setId(GuidUtil.getGuid());
			entity.setInputTime(new Date());
			entity.setIsDel(0);
		}
		${table.mapperName?uncap_first}.insertBatch(entitys);
	}

	public void update(${table.beanName}Bean entity){
		entity.setUpdateTime(new Date());
		${table.mapperName?uncap_first}.update(entity);
	}
	
	public void del(${table.beanName}Bean entity){
		update(entity);
	}
}
