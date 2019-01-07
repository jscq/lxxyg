package com.chengqing.model.shopping;

import com.chengqing.model.BaseModel;

/**
 * 商品分类
 * @author ChengQing
 *
 */
public class Classify extends BaseModel{

	private static final long serialVersionUID = 1L;

	/**  
	 * 分类名称
	 */   
	private String classifyName;
	
	/**  
	 * 分类编码
	 */   
	private String classifyCode;
	
	
	/**  
	 * 上下架状态
	 * 0-上架  1-下架
	 */   
	private int classifyStatus = 0;


	public String getClassifyName() {
		return classifyName;
	}


	public void setClassifyName(String classifyName) {
		this.classifyName = classifyName;
	}


	public String getClassifyCode() {
		return classifyCode;
	}


	public void setClassifyCode(String classifyCode) {
		this.classifyCode = classifyCode;
	}


	public int getClassifyStatus() {
		return classifyStatus;
	}


	public void setClassifyStatus(int classifyStatus) {
		this.classifyStatus = classifyStatus;
	}
	
	
	
}
