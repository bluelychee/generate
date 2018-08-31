package com.xiaozhi.generate.bean;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.xiaozhi.generate.service.GenerateUtile;

public class GenerateColumn {
	private String tableName;
	private String columnName;
	private String dataType; 
	private String columnKey;
	private String columnCommont;
	private String fieldName;
	private String type;
	private String isNullable;
	private String columnType;
	private int length;
	
	
	private Pattern reg =Pattern.compile("\\d+");
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public String getColumnKey() {
		return columnKey;
	}
	public void setColumnKey(String columnKey) {
		this.columnKey = columnKey;
	}
	public String getColumnCommont() {
		return columnCommont;
	}
	public void setColumnCommont(String columnCommont) {
		this.columnCommont = columnCommont;
	}
	public String getFieldName() {
		return GenerateUtile.formatName(columnName);
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getIsNullable() {
		return isNullable;
	}
	public void setIsNullable(String isNullable) {
		this.isNullable = isNullable;
	}
	public String getColumnType() {
		return columnType;
	}
	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}
	public String getType() throws Exception {
		if("varchar".equals(dataType)||"longtext".equals(dataType)||"text".equals(dataType)||"blob".equals(dataType)||"longblob".equals(dataType)){
			return "String";
		}else if("int".equals(dataType)){
			return "Integer";
		}else if("double".equals(dataType)||("decimal").equals(dataType)){
			return "Double";
		}else if("float".equals(dataType)){
			return "Float";
		}else if("date".equals(dataType)||"datetime".equals(dataType)||"timestamp".equals(dataType)){
			return "Date";
		}else{
			throw new Exception("dataType is unKnow!:"+dataType);
		}
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getLength() {
		Matcher mat = reg.matcher(columnType);
		if(mat.find()){
			return Integer.parseInt(mat.group(0));
		}
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	@Override
	public boolean equals(Object anObject) {
		if (this == anObject) {
            return true;
        }
        if (anObject instanceof GenerateColumn) {
        	GenerateColumn anotherColumn = (GenerateColumn) anObject;
        	if(this.getColumnName().equals(anotherColumn.getColumnName())){
        		return true;
        	}
        }
        return false;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
}
