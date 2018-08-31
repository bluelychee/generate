package ${servicePkg};

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.xiaozhi.BaseTests;
import com.xiaozhi.common.interceptor.Pager;
import org.junit.Test;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import ${pojoPkg}.${table.beanName}Bean;

public class Test${table.serviceName} extends BaseTests{
	@Resource
	${table.serviceName} ${table.serviceName?uncap_first};
	
	Logger logger = Logger.getLogger(Test${table.serviceName}.class);

	@Test
   	public void testInsert() {
        ${table.beanName}Bean entity = new ${table.beanName}Bean();
       	${table.serviceName?uncap_first}.insert(entity,user);
 	}

	@Test
  	public void testInsertBatch() {
        List<${table.beanName}Bean> entitys = new ArrayList<${table.beanName}Bean>();
        for(int i=0;i<100;i++){
            ${table.beanName}Bean entity = new ${table.beanName}Bean();
            entitys.add(entity);
		}
        ${table.serviceName?uncap_first}.insertBatch(entitys,user);
	}
	
	@Test
	public void testFindByCondtion(){
       ${table.beanName}Bean entity = new ${table.beanName}Bean();
		List<${table.beanName}Bean> data = ${table.serviceName?uncap_first}.findByCondtion(entity);
		logger.info(JSON.toJSONString(data));
	}

	@Test
	public void testFindListPage(){
  		${table.beanName}Bean item = new ${table.beanName}Bean();
		Pager<${table.beanName}Bean> page = new Pager <${table.beanName}Bean>();
    	page.setItem(item);
        List<${table.beanName}Bean> data = ${table.serviceName?uncap_first}.findListPage(page);
        logger.info(JSON.toJSONString(data));
    }
    
    @Test
	public void testGetByCondtion(){
		${table.beanName}Bean item = new ${table.beanName}Bean();
		Pager<${table.beanName}Bean> page = new Pager <${table.beanName}Bean>();
    	page.setItem(item);
    	page.setPageSize(1);
        List<${table.beanName}Bean> data = ${table.serviceName?uncap_first}.findListPage(page);
        logger.info(JSON.toJSONString(data));
        if(data!=null && data.size()>0){
        	${table.beanName}Bean bean = ${table.serviceName?uncap_first}.getByCondtion(data.get(0));
        	logger.info(JSON.toJSONString(bean));
        }
    }
	
	@Test
	public void testUpdate(){
  		${table.beanName}Bean item = new ${table.beanName}Bean();
		Pager<${table.beanName}Bean> page = new Pager <${table.beanName}Bean>();
    	page.setItem(item);
    	page.setPageSize(1);
        List<${table.beanName}Bean> data = ${table.serviceName?uncap_first}.findListPage(page);
        if(data!=null && data.size()>0){
        	${table.serviceName?uncap_first}.update(data.get(0), user);
        }
        logger.info(JSON.toJSONString(data));
    }
}
