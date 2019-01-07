package com.chengqing.model.sys;

import com.chengqing.model.BaseModel;

/**
 * 家庭住址
 * @author ChengQing
 *
 */
public class Zoning extends BaseModel {

	private static final long serialVersionUID = 1L;

	private String province;//省
	private String city;//市
	private String county;//区
	private String street;//街道
	
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCounty() {
		return county;
	}
	public void setCounty(String county) {
		this.county = county;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
}
