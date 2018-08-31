package ${servicePkg}.${modulePkgName};

import java.util.List;

import com.flccs.sms.model.Page;
import ${pojoPkg}.${table.beanName}Bean;

public interface ${table.beanName}Service {
	/**
	 * 录入
	 * @param entity
	 */
	void insert${table.beanName}(${table.beanName}Bean entity);
	
	/**
	 * 批量录入
	 * @param entitys
	 */
	void insert${table.beanName}(List<${table.beanName}Bean> entitys);

	/**
	 * 更新
	 * @param entity
	 */
	void update${table.beanName}(${table.beanName}Bean entity);
	
	/**
	 * 分页列表查询
	 * @param page
	 * @return
	 */
	List<${table.beanName}Bean> query${table.beanName}(Page<${table.beanName}Bean> pager);
	
	/**
	 * 查询
	 * @param entity
	 * @return
	 */
	${table.beanName}Bean get${table.beanName}(${table.beanName}Bean entity);
	
	/**
	 * 列表查询
	 * @param entity
	 * @return
	 */
	List<${table.beanName}Bean> find${table.beanName}(${table.beanName}Bean entity);
	
}
