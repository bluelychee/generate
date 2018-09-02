package ${pojoPkg};

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.xiaozhi.common.domain.BaseObject;
import com.xiaozhi.common.spring.JsonDateDeserializer;

/*
*${table.tableCommont}
*/
public class ${table.beanName}Bean extends BaseObject implements Serializable{
	private static final long serialVersionUID = 1L;
<#list columns as column>
	/**
	 * ${column.columnCommont}
	 */
	<#if column.getType() == "Date">
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	</#if>
	private ${column.getType()} ${column.fieldName};
</#list>

<#list columns as column>
	public ${column.getType()} get${column.fieldName?cap_first}() {
		return ${column.fieldName};
	}
	<#if column.getType() == "Date">
	@JsonDeserialize(using = JsonDateDeserializer.class)
	</#if>
	public void set${column.fieldName?cap_first}(${column.getType()} ${column.fieldName}) {
		this.${column.fieldName} = ${column.fieldName};
	}
</#list>
	
}