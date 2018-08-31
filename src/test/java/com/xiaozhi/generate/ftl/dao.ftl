package ${daoPkg};

import java.util.List;

import com.qftx.common.interceptor.Pager;
import ${pojoPkg}.${table.beanName}Bean;

public interface ${table.mapperName}{
	public List<${table.beanName}Bean> find();

	public List<${table.beanName}Bean> findByCondtion(${table.beanName}Bean entity);
	
	public List<${table.beanName}Bean> findListPage(Pager<${table.beanName}Bean> pager);
	
	public ${table.beanName}Bean getByCondtion(${table.beanName}Bean entity);
	
	public void insert(${table.beanName}Bean entity);

	public void insertBatch(List<${table.beanName}Bean> entitys);

	public void update(${table.beanName}Bean entity);
}
