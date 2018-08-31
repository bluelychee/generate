package com.xiaozhi.generate.service;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class TempleteManager {
	private static TempleteManager tplm = null;
	private Configuration cfg = null;
	private TempleteManager() {
		cfg = new Configuration();
		try {
			//String path = this.getClass().getResource("/").getPath().replaceAll("target.*|WebRoot.*", "");
			String path = System.getProperty("user.dir");
			path+="/test/com/qftx/generate/ftl";
			cfg.setDirectoryForTemplateLoading(new File(path));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static Template getTemplate(String name) throws IOException {
		if (tplm == null) {
			tplm = new TempleteManager();
		}
		if(!name.contains(".ftl")){
			name = name+".ftl";
		}
		return tplm.cfg.getTemplate(name);
	}

	public static String process(String templatefile, Map param) throws IOException, TemplateException {
		Template template = TempleteManager.getTemplate(templatefile);
		StringWriter sw = new StringWriter();
		template.process(param, sw);
		return sw.toString();
	}
	
	public static void main(String[] args) throws IOException, TemplateException {
		String str = TempleteManager.process("helloWorld", new HashMap<String, String>());
		System.out.println(str);
	}
}