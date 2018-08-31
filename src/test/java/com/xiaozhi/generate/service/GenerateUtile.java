package com.xiaozhi.generate.service;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class GenerateUtile {
	public static String formatName(String name){
		name = name.toLowerCase().replace("rbt_", "");
		if(name.contains("_")){
			Pattern pattern =Pattern.compile("_(\\S)");
			Matcher mat = pattern.matcher(name);
			while(mat.find()){
				name = name.replace("_"+mat.group(1), mat.group(1).toUpperCase());
			}
		}
		
		return name;
	}
	
	public static void main(String[] args) {
		String str ="plan_sale_year_log";
		System.out.println(formatName(str));
	}
}
