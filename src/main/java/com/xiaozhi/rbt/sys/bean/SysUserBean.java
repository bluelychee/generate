package com.xiaozhi.rbt.sys.bean;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.xiaozhi.common.domain.BaseObject;
import com.xiaozhi.common.spring.JsonDateDeserializer;

/*
*用户表
*/
public class SysUserBean extends BaseObject implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 * 用户ID
	 */
	private String id;
	/**
	 * 单位ID
	 */
	private String orgId;
	/**
	 * 用户名
	 */
	private String userName;
	/**
	 * 登录密码-MD5加密
	 */
	private String passwordMd5;
	/**
	 * 电话
	 */
	private String mobile;
	/**
	 * 电子邮箱
	 */
	private String email;
	/**
	 * 性别 0-未选择 1-男 2-女
	 */
	private Integer sex;
	/**
	 * 部门
	 */
	private String dept;
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
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPasswordMd5() {
		return passwordMd5;
	}
	public void setPasswordMd5(String passwordMd5) {
		this.passwordMd5 = passwordMd5;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
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