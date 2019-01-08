package com.chengqing.model.shopping;

import com.chengqing.model.BaseModel;

/**
 * 商品
 * @author ChengQing
 *
 */
public class Goods extends BaseModel {

	private static final long serialVersionUID = 4777358121874276168L;
	
	/**
	 * 所属分类
	 */
	private Classify classify;
	
	/**
	 * 头像
	 */
	private String goodsImage;
	
	/**
	 * 商品名称
	 */
	private String goodsName;
	
	/**
	 * 是否有折扣
	 * 1-有  2-没有
	 */
	private String hasDiscount;
	
	/**
	 * 售价
	 */
	private String goodsPrice;
	
	/**
	 * 折扣
	 */
	private String goodsDiscount;
	
	/**
	 * 原价
	 */
	private String goodsOriginalPrice;

	
	
	/**
	 * 是否可售
	 */
	private String goodsSalable;
	
	
	/**
	 * 已售
	 */
	private String goodsSold;

	
	/**
	 * 余量
	 */
	private String goodsResidual;


	public Classify getClassify() {
		return classify;
	}


	public void setClassify(Classify classify) {
		this.classify = classify;
	}


	public String getGoodsImage() {
		return goodsImage;
	}


	public void setGoodsImage(String goodsImage) {
		this.goodsImage = goodsImage;
	}


	public String getGoodsName() {
		return goodsName;
	}


	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}


	public String getHasDiscount() {
		return hasDiscount;
	}


	public void setHasDiscount(String hasDiscount) {
		this.hasDiscount = hasDiscount;
	}


	public String getGoodsPrice() {
		return goodsPrice;
	}


	public void setGoodsPrice(String goodsPrice) {
		this.goodsPrice = goodsPrice;
	}


	public String getGoodsDiscount() {
		return goodsDiscount;
	}


	public void setGoodsDiscount(String goodsDiscount) {
		this.goodsDiscount = goodsDiscount;
	}


	public String getGoodsOriginalPrice() {
		return goodsOriginalPrice;
	}


	public void setGoodsOriginalPrice(String goodsOriginalPrice) {
		this.goodsOriginalPrice = goodsOriginalPrice;
	}


	public String getGoodsSalable() {
		return goodsSalable;
	}


	public void setGoodsSalable(String goodsSalable) {
		this.goodsSalable = goodsSalable;
	}


	public String getGoodsSold() {
		return goodsSold;
	}


	public void setGoodsSold(String goodsSold) {
		this.goodsSold = goodsSold;
	}


	public String getGoodsResidual() {
		return goodsResidual;
	}


	public void setGoodsResidual(String goodsResidual) {
		this.goodsResidual = goodsResidual;
	}
	
	
	
}
