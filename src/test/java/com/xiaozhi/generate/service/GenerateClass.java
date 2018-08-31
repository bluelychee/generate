package com.xiaozhi.generate.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.xiaozhi.generate.bean.GenerateColumn;
import com.xiaozhi.generate.bean.GenerateTable;
import com.xiaozhi.generate.dao.GenerateColumnMapper;
import com.xiaozhi.generate.dao.GenerateTableMapper;
import org.springframework.stereotype.Service;

@Service
public class GenerateClass {
	@Resource
	private GenerateTableMapper tableMapper;
	@Resource
	private GenerateColumnMapper columnMapper;
	
	private String srcPath=null;
	private String webPath=null;
	private String testPath=null;
	private String basePkg="com.xiaozhi.rbt";
	
	public boolean ifCover = true;
	public boolean generateAll = true;
	
	public void initPath(){
		//String basePath = this.getClass().getResource("/").getPath().replaceAll("target.*|WebRoot.*", "");
		String basePath = System.getProperty("user.dir");
		srcPath = basePath+ "/src/";
		testPath =basePath+ "/test/";
		webPath =basePath+ "/src.web/";
	}
	
	public void createClass(Map<String,String[]> modules){
		for(String moduleName :modules.keySet()){
			String[] tables = modules.get(moduleName);
			for (String table : tables) {
				createClass(table,moduleName);
			}
		}
	}
	
	public void createClass(String tableName,String modulePkgName){
		initPath();
		GenerateTable table = tableMapper.getTable(tableName);
		List<GenerateColumn> columns = columnMapper.findByTable(tableName);
		table.setColumns(columns);
		
		Map<String, Object> param = new HashMap<String, Object>();
		String pojoPkg = basePkg+"."+modulePkgName+".bean";
		String daoPkg = basePkg+"."+modulePkgName+".dao";
		String servicePkg = basePkg+"."+modulePkgName+".service";
		String webPkg = "com.qftx.web."+modulePkgName;
		
		
		param.put("modulePkgName", modulePkgName );
		param.put("pojoPkg", pojoPkg );
		param.put("daoPkg", daoPkg);
		param.put("servicePkg",servicePkg);
		param.put("webPkg",webPkg);
		
		param.put("table", table);
		param.put("columns", table.getColumns());
		try {
			String beanStr = TempleteManager.process("bean", param);
			createFile(beanStr, getPath(pojoPkg),table.getBeanName()+"Bean.java");
			
			String daoStr = TempleteManager.process("dao", param);
			createFile(daoStr, getPath(daoPkg),table.getMapperName()+".java");
			
			String xmlStr = TempleteManager.process("xml", param);
			createFile(xmlStr, getPath(daoPkg),table.getMapperName()+".xml");
			
			if(generateAll){
				String serviceStr = TempleteManager.process("serviceImpl", param);
				createFile(serviceStr, getPath(servicePkg),table.getServiceName()+".java");
				
				String testStr = TempleteManager.process("serviceTest", param);
				createFile(testStr, getPath(servicePkg),"Test"+table.getServiceName()+".java");
				
				String actionStr = TempleteManager.process("action", param);
				createFile(actionStr, getPath(webPkg),table.getActionName()+".java");
			}
			
			/*String actionTestStr = TempleteManager.process("actionTest", param);
			createFile(actionTestStr, getPath(webPkg),"Test"+table.getActionName()+".java");*/
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private  void createFile(String content, String path, String fileName)
			throws IOException {
		File dir = null;
		if(fileName.startsWith("Test")){
			dir = new File(testPath+path);
		}else if(fileName.endsWith("Action.java")){
			dir = new File(webPath+path);
		}else{
			dir = new File(srcPath+path);
		}
		
		if (!dir.exists()) {
			dir.mkdirs();
		}
		
		File file = new File(dir.getPath()+"/"+ fileName);
		if (ifCover||!file.exists()) {
			file.createNewFile();
			FileWriter fw = new FileWriter(file);
			fw.write(content);

			fw.flush();
			fw.close();
		}else{
			if(!fileName.contains("Service")&&!fileName.contains("Mapper.java")&&!fileName.contains("Action.java")){
				fileName = fileName.replace(".", "1.");
				file = new File(srcPath+path + fileName);
				file.createNewFile();
				FileWriter fw = new FileWriter(file);
				fw.write(content);
				fw.flush();
				fw.close();
			}
		}
		
	}

	public static void main(String[] args) {
		GenerateClass class1 = new GenerateClass();
		class1.initPath();
	}
	
	public  String getPath(String pkg) {
		String temp = "";
		String[] strs = pkg.split("\\.");
		for (String str : strs) {
			temp += str + "\\";
		}
		return temp;
	}
	
}
