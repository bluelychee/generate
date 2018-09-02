package com.xiaozhi.common.spring;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.xiaozhi.common.exception.RunException;
import com.xiaozhi.common.util.DateUtil;

import java.io.IOException;
import java.util.Date;
import java.util.regex.Pattern;

public class JsonDateDeserializer extends JsonDeserializer<Date> {
	//yyyy-MM-dd HH:mm:ss
	private static final Pattern reg1 = Pattern.compile("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}$");
	//yyyy-MM-dd HH:mm
	private static final Pattern reg2 = Pattern.compile("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}$");
	//yyyy-MM-dd HH:mm:ss
	private static final Pattern reg3 = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");
	
	private static final Pattern reg4 = Pattern.compile("^\\d{13}$");
	
	
	@Override
	public Date deserialize(JsonParser jp, DeserializationContext ctxt) throws JsonParseException, IOException {
		String date = jp.getText();
		if(reg1.matcher(date).find()){
			return DateUtil.parse(date,DateUtil.hour24HMSPattern);
		}if(reg2.matcher(date).find()){
			return DateUtil.parse(date,DateUtil.hour24HMPattern);
		}if(reg3.matcher(date).find()){
			return DateUtil.parse(date,DateUtil.defaultPattern);
		}if(reg4.matcher(date).find()){
			return new Date(Long.valueOf(date));
		}else{
			throw new RunException(date+" 日期格式不识别！");
		}
	}
}
