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
	private int hasDiscount;
	
	/**
	 * 售价
	 */
	private double goodsPrice;
	
	/**
	 * 折扣
	 */
	private double goodsDiscount;
	
	/**
	 * 原价
	 */
	private double goodsOriginalPrice;

	/**
	 * 是否长期可售
	 * 1-是    2-否
	 */
	private int goodsSalable;
	
	
	/**
	 * 已售
	 */
	private double goodsSold;

	
	/**
	 * 余量
	 */
	private double goodsResidual;


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


	public int getHasDiscount() {
		return hasDiscount;
	}


	public void setHasDiscount(int hasDiscount) {
		this.hasDiscount = hasDiscount;
	}


	public double getGoodsPrice() {
		return goodsPrice;
	}


	public void setGoodsPrice(double goodsPrice) {
		this.goodsPrice = goodsPrice;
	}


	public double getGoodsDiscount() {
		return goodsDiscount;
	}


	public void setGoodsDiscount(double goodsDiscount) {
		this.goodsDiscount = goodsDiscount;
	}


	public double getGoodsOriginalPrice() {
		return goodsOriginalPrice;
	}


	public void setGoodsOriginalPrice(double goodsOriginalPrice) {
		this.goodsOriginalPrice = goodsOriginalPrice;
	}


	public int getGoodsSalable() {
		return goodsSalable;
	}


	public void setGoodsSalable(int goodsSalable) {
		this.goodsSalable = goodsSalable;
	}


	public double getGoodsSold() {
		return goodsSold;
	}


	public void setGoodsSold(double goodsSold) {
		this.goodsSold = goodsSold;
	}


	public double getGoodsResidual() {
		return goodsResidual;
	}


	public void setGoodsResidual(double goodsResidual) {
		this.goodsResidual = goodsResidual;
	}
}
