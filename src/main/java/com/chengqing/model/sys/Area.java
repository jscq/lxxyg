package com.chengqing.model.sys;

import com.chengqing.model.BaseModel;

/**
 * 行政区域
 * @author ChengQing
 *
 */
public class Area extends BaseModel{

	private static final long serialVersionUID = 2312722231072566200L;
	
	/**
	 * 行政编码
	 */
	private String code;
	
	/**
	 * 行政名称
	 */
	private String name;
	
	/**
	 * 上级
	 */
	private String parentId;
	
	/**
	 * 等级
	 */
	private int level;
	
	/**
	 * 英文
	 */
	private String en;
	
	/**
	 * 英文简称
	 */
	private String shortEn;
	
	/**
	 * 
	 */
	private String state;
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getEn() {
		return en;
	}

	public void setEn(String en) {
		this.en = en;
	}

	public String getShortEn() {
		return shortEn;
	}

	public void setShortEn(String shortEn) {
		this.shortEn = shortEn;
	}
	
}
