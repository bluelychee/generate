package com.xiaozhi.generate.dao;

import com.xiaozhi.generate.bean.GenerateTable;
import org.apache.ibatis.annotations.Param;

public interface GenerateTableMapper {
	/**
	 * 查询
	 * @param tableName
	 * @return
	 */
	GenerateTable getTable(@Param(value = "tableName") String tableName);
}