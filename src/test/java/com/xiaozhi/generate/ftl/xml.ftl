<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="${daoPkg}.${table.beanName}Mapper">
	<sql id="cols">
		<#list columns as column>t.${column.columnName}<#if column_has_next>,</#if></#list>
	</sql>
	
	<sql id="tableName">
		${table.tableName}
	</sql>

	<sql id="setSql">
		<set>
			<#list columns as column><#if !column.equals(table.proColumn) && !column.equals(table.orgIdColumn)>
			<if test="${column.fieldName} != null">${column.columnName} = ${r"#{"}${column.fieldName}},</if>
			</#if></#list>
		</set>
	</sql>
	
	<sql id="whereSql">
		<#if table.orgIdColumn??>
		<if test="${table.orgIdColumn.fieldName} != null<#if table.orgIdColumn.type=="String"> and ${table.orgIdColumn.fieldName} != ''</#if>">and t.${table.orgIdColumn.columnName} = ${r"#{"}${table.orgIdColumn.fieldName}}</if>
		</#if>
		<#list columns as column><#if !column.equals(table.orgIdColumn)>
		<if test="${column.fieldName} != null<#if column.type=="String"> and ${column.fieldName} != ''</#if>">and t.${column.columnName} = ${r"#{"}${column.fieldName}}</if>
		</#if></#list>
	</sql>
	
	<sql id="whereSqlPage">
		<#if table.orgIdColumn??>
		<if test="item.${table.orgIdColumn.fieldName} != null<#if table.orgIdColumn.type=="String"> and item.${table.orgIdColumn.fieldName} != ''</#if>">and t.${table.orgIdColumn.columnName} = ${r"#{"}item.${table.orgIdColumn.fieldName}}</if>
		</#if>
		<#list columns as column><#if !column.equals(table.orgIdColumn)>
		<if test="item.${column.fieldName} != null<#if column.type=="String"> and item.${column.fieldName} != ''</#if>">and t.${column.columnName} = ${r"#{"}item.${column.fieldName}}</if>
		</#if></#list>
	</sql>

	<resultMap type="${pojoPkg}.${table.beanName}Bean" id="${table.beanName}Map">
		<id column="${table.proColumn.columnName}" property="${table.proColumn.fieldName}"/>
		<#list columns as column>
		<#if !column.equals(table.proColumn)>
		<result column="${column.columnName}" property="${column.fieldName}"/>
		</#if>
		</#list>
	</resultMap>
	
	<!-- 查询全部-->
	<select id="find" resultMap="${table.beanName}Map">
		select
		<include refid="cols"/>
		from <include refid="tableName"/> t
	</select>
	
	<!-- 列表查询-->
	<select id="findByCondtion" resultMap="${table.beanName}Map">
		select
		<include refid="cols"/>
		from <include refid="tableName"/> t
		where 1=1
		<include refid="whereSql"/>
		<if test="orderKey != null and orderKey != ''">order by ${r"${"}orderKey}</if>
	</select>
	
	<!-- 分页查询-->
	<select id="findListPage"  parameterType="com.qftx.common.interceptor.Pager" resultMap="${table.beanName}Map">
		select
		<include refid="cols"/>
		from <include refid="tableName"/> t
		where 1=1
		<include refid="whereSqlPage"/>
		<if test="item.orderKey != null and item.orderKey != ''">order by ${r"${"}item.orderKey}</if>
	</select>
	
	<!-- 单个查询-->
	<select id="getByCondtion" resultMap="${table.beanName}Map">
		select
		<include refid="cols"/>
		from <include refid="tableName"/> t
		where 1=1
		<include refid="whereSql"/>
	</select>
	
	<!-- 主键查询-->
	<select id="getByPrimaryKey" resultMap="${table.beanName}Map">
		select
		<include refid="cols"/>
		from <include refid="tableName"/> t
		where 1=1
		<if test="orgId != null and orgId != ''">and t.org_id = ${r"#{"}orgId}</if>
		<if test="id != null and id != ''">and t.id = ${r"#{"}id}</if>
	</select>
	
	<!-- 录入 -->
	<insert id="insert" parameterType="${pojoPkg}.${table.beanName}Bean">
	insert into <include refid="tableName"/>(<#list columns as column>${column.columnName}<#if column_has_next>,</#if></#list>)
	values(<#list columns as column>${r"#{"}${column.fieldName}}<#if column_has_next>,</#if></#list>)
	</insert>
	
	<!-- 批量录入 -->
	<insert id="insertBatch" parameterType="ArrayList">  
    insert into <include refid="tableName"/>(<#list columns as column>${column.columnName}<#if column_has_next>,</#if></#list>)
    values
    <foreach collection="list" item="bean" separator=",">
    	(<#list columns as column>${r"#{"}bean.${column.fieldName}}<#if column_has_next>,</#if></#list>)
    </foreach>
    </insert>
    
    <!-- 动态更新 -->
	<update id="update">
		update <include refid="tableName"/>
		<include refid="setSql"/>
		where 1=1
		<if test="orgId != null and orgId != ''">and org_id = ${r"#{"}orgId}</if>
		<if test="${table.proColumn.fieldName} != null">and ${table.proColumn.columnName} = ${r"#{"}${table.proColumn.fieldName}}</if>
	</update>
</mapper>