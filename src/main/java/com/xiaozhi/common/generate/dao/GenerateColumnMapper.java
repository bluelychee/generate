package com.xiaozhi.common.generate.dao;

import java.util.List;
import java.util.Map;

import com.xiaozhi.common.generate.bean.GenerateColumn;
import org.apache.ibatis.annotations.Param;


public interface GenerateColumnMapper {
	/**
	 * 查询
	 * @param tableName
	 * @return
	 */
	List<GenerateColumn> findByTable(@Param(value = "tableName") String tableName);
	
	List<GenerateColumn>  findAll();
	
	int ct(Map<String, String> params);
}