package com.xiaozhi.rbt.sys.bean;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.xiaozhi.common.domain.BaseObject;
import com.xiaozhi.common.spring.JsonDateDeserializer;

/*
*角色表
*/
public class SysRoleBean extends BaseObject implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 * 用户ID
	 */
	private String id;
	/**
	 * 用户名
	 */
	private String roleName;
	/**
	 * 录入人
	 */
	private String inputAcc;
	/**
	 * 录入时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date inputTime;
	/**
	 * 更新人
	 */
	private String updateAcc;
	/**
	 * 更新时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date updateTime;
	/**
	 * 用户状态 1:启用,0:禁用
	 */
	private Integer status;
	/**
	 * 是否删除 1:删除,0:未删除
	 */
	private Integer isDel;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getInputAcc() {
		return inputAcc;
	}
	public void setInputAcc(String inputAcc) {
		this.inputAcc = inputAcc;
	}
	public Date getInputTime() {
		return inputTime;
	}
	@JsonDeserialize(using = JsonDateDeserializer.class)
	public void setInputTime(Date inputTime) {
		this.inputTime = inputTime;
	}
	public String getUpdateAcc() {
		return updateAcc;
	}
	public void setUpdateAcc(String updateAcc) {
		this.updateAcc = updateAcc;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	@JsonDeserialize(using = JsonDateDeserializer.class)
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getIsDel() {
		return isDel;
	}
	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}
	
}