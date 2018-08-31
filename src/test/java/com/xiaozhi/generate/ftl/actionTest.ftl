package ${webPkg};

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.qftx.common.BaseUtest;
import com.qftx.common.interceptor.Pager;
import ${pojoPkg}.${table.beanName}Bean;
import ${servicePkg}.${table.serviceName};

public class Test${table.actionName} extends BaseUtest{
	@Resource
	${table.serviceName} ${table.serviceName?uncap_first};
	
	Logger logger = Logger.getLogger(Test${table.actionName}.class);

	@Test
	public void testListPageJson() throws Exception{
		Pager<${table.beanName}Bean> pager = new Pager<${table.beanName}Bean>();
		pager.setItem(new ${table.beanName}Bean());
		String data = testDoPostJSON(testDomain+"/${table.beanName?uncap_first}/listPageJson", JSON.toJSONString(pager));
		logger.info(data);
    }
	
	@Test
	public void testListJson() throws Exception{
		Pager<${table.beanName}Bean> pager = new Pager<${table.beanName}Bean>();
		pager.setItem(new ${table.beanName}Bean());
		String data = testDoPostJSON(testDomain+"/${table.beanName?uncap_first}/listJson", JSON.toJSONString(pager));
		logger.info(data);
    }
	
	@Test
	public void testInfoJson() throws Exception{
		${table.beanName}Bean item = new ${table.beanName}Bean();
		Pager<${table.beanName}Bean> page = new Pager<${table.beanName}Bean>();
    	page.setItem(item);
    	page.setPageSize(1);
        List<${table.beanName}Bean> list = ${table.serviceName?uncap_first}.findListPage(page);
        logger.info(JSON.toJSONString(list));
        if(list!=null && list.size()>0){
        	${table.beanName}Bean ${table.beanName?uncap_first}Bean = list.get(0);
        	String data = testDoPostJSON(testDomain+"/${table.beanName?uncap_first}/infoJson", JSON.toJSONString(${table.beanName?uncap_first}Bean));
        	logger.info(data);
        }
    }
	
	@Test
	public void testAddJson() throws Exception{
		${table.beanName}Bean ${table.beanName?uncap_first}Bean = new ${table.beanName}Bean();
		String data = testDoPostJSON(testDomain+"/${table.beanName?uncap_first}/saveJson", JSON.toJSONString(${table.beanName?uncap_first}Bean));
		logger.info(data);
    }
	
	@Test
	public void testUpdateJson() throws Exception{
		${table.beanName}Bean item = new ${table.beanName}Bean();
		Pager<${table.beanName}Bean> page = new Pager <${table.beanName}Bean>();
    	page.setItem(item);
    	page.setPageSize(1);
    	List<${table.beanName}Bean> list = ${table.serviceName?uncap_first}.findListPage(page);
		logger.info(JSON.toJSONString(list));
	    if(list!=null && list.size()>0){
		    ${table.beanName}Bean ${table.beanName?uncap_first}Bean = list.get(0);
		    String data = testDoPostJSON(testDomain+"/${table.beanName?uncap_first}/saveJson", JSON.toJSONString(${table.beanName?uncap_first}Bean));
		    logger.info(data);
	    }
    }
	
	@Test
	public void testDeleteJson() throws Exception{
		${table.beanName}Bean item = new ${table.beanName}Bean();
		Pager<${table.beanName}Bean> page = new Pager <${table.beanName}Bean>();
    	page.setItem(item);
    	page.setPageSize(1);
    	List<${table.beanName}Bean> list = ${table.serviceName?uncap_first}.findListPage(page);
		logger.info(JSON.toJSONString(list));
	    if(list!=null && list.size()>0){
		    ${table.beanName}Bean ${table.beanName?uncap_first}Bean = list.get(0);
		    ${table.beanName?uncap_first}Bean.setIsDel(1);
		    String data = testDoPostJSON(testDomain+"/${table.beanName?uncap_first}/saveJson", JSON.toJSONString(${table.beanName?uncap_first}Bean));
		    logger.info(data);
	    }
    }
}
