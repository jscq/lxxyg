package com.chengqing.model.sys;

import java.util.ArrayList;
import java.util.List;

import com.chengqing.model.BaseModel;

/**
 * 菜单
 * @author chengqing
 *
 */
public class Function extends BaseModel{

	private static final long serialVersionUID = 1L;
	
	/**  
	 * 功能名称
	 */   
	private String functionName;
	
	/**  
	 * 功能编码    
	 */   
	private String functionCode;
	
	/**
	 * 父类
	 */
	private Function parent;
	
	/**
	 * 链接URL
	 */
	private String functionUrl;
	
	/**
	 * 之类集合
	 */
	private List<Function> childFuncList = new ArrayList<Function>();

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public String getFunctionCode() {
		return functionCode;
	}

	public void setFunctionCode(String functionCode) {
		this.functionCode = functionCode;
	}

	public Function getParent() {
		return parent;
	}

	public void setParent(Function parent) {
		this.parent = parent;
	}

	public String getFunctionUrl() {
		return functionUrl;
	}

	public void setFunctionUrl(String functionUrl) {
		this.functionUrl = functionUrl;
	}

	public List<Function> getChildFuncList() {
		return childFuncList;
	}

	public void setChildFuncList(List<Function> childFuncList) {
		this.childFuncList = childFuncList;
	}
	
	
}
