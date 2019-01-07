package com.chengqing.model.sys;

import com.chengqing.model.BaseModel;


public class Code extends BaseModel{

	private static final long serialVersionUID = 8252424063806879210L;
	
	/**  
	 * 名称
	 */   
	private String codeName;
	
	/**  
	 * 值       
	 */   
	private String codeValue;
	
	/**  
	 * 类型       
	 */   
	private String codeType;
	
	/**
	 * 上级单位
	 */
	private Code parent;
	
	/**
	 * 数据描述
	 */
	private String codeDescription;
	
	/**
	 * 状态默认关闭
	 */
	private String state = "closed";
	
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCodeDescription() {
		return codeDescription;
	}

	public void setCodeDescription(String codeDescription) {
		this.codeDescription = codeDescription;
	}

	public Code getParent() {
		return parent;
	}

	public void setParent(Code parent) {
		this.parent = parent;
	}

	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}

	public String getCodeValue() {
		return codeValue;
	}

	public void setCodeValue(String codeValue) {
		this.codeValue = codeValue;
	}

	public String getCodeType() {
		return codeType;
	}

	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}

}
