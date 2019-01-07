package com.chengqing.model.sys;

import com.chengqing.model.BaseModel;

/**
 * 系统管理模块绑定类
 * @author chengqing
 *
 */
public class SysQuery extends BaseModel {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 功能名称
	 */
	private String functionName;
	
	/**
	 * 父节点id
	 */
	private String parentId;
	
	/**
	 * 数据名称
	 * @return
	 */
	private String codeName;
	
	/**
	 * 数据类型
	 */
	private String codeType;

	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	public String getCodeType() {
		return codeType;
	}

	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
}
