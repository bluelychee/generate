package com.xiaozhi.common.generate;

import com.xiaozhi.common.generate.service.GenerateClass;
import com.xiaozhi.common.util.GuidUtil;
import com.xiaozhi.BaseTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestGenerateClass extends BaseTests {
	@Autowired
	private GenerateClass generateClass;

	/*生成单个包单表*/
	@Test
	public void generateSingle() {
		/*
		 * 设置为true,覆盖原文件,适合原文件中未写业务逻辑时生成
		 * 设置为false,文件名重命名与原文件同时存在适合已经写有业务逻辑时候生成
		 * */
		//generateClass.ifCover = false;
		//generateClass.generateAll = false;
		//generateClass.generateAll =true;
		//generateClass.createClass("rbt_procedure_person", "procedure");
		//generateClass.createClass("rbt_procedure_record", "record");
		//generateClass.createClass("rbt_procedure_report_pri", "report");
		generateClass.createClass("sys_role", "sys");
		
		//generateClass.createClass("rbt_res_cust_info", "res");
		/*generateClass.createClass("rbt_procedure", "procedure");*/
		/*generateClass.createClass("rbt_used_procedure", "procedure.used");
		generateClass.createClass("rbt_used_procedure_pri", "procedure.used");
		generateClass.createClass("rbt_used_procedure_key", "procedure.used");
		generateClass.createClass("rbt_used_procedure_record", "procedure.used");*/
		//generateClass.createClass("rbt_res_cust_info", "res");

	}
	
//	/*生成多个包多个表*/
//	@Test
//	public void generateMultiple() {
//		/**
//		 * 设置为true,覆盖原文件,适合原文件中未写业务逻辑时生成
//		 * 设置为false,文件名重命名与原文件同时存在适合已经写有业务逻辑时候生成
//		 * */
//		generateClass.ifCover = true;
//		
//		Map<String, String[]> modules = new HashMap<String, String[]>();
//		
//		//modules.put("interview", new String[]{"rms_interview"});
//		modules.put("talent", new String[]{"rms_talent_info","rms_talent_at_school","rms_talent_credentials",
//				"rms_talent_educations","rms_talent_jobs","rms_talent_languages",
//				"rms_talent_objectives","rms_talent_projects","rms_talent_trainings"
//		});
//		generateClass.createClass(modules);
//	}

	public static void main(String[] args) {
		for (int i = 0; i <1000 ; i++) {
			System.out.println(GuidUtil.getGuid());
		}
	}
}
