package com.chengqing.dao.shopping;

import com.chengqing.dao.base.BaseDao;
import com.chengqing.model.shopping.Classify;

/**
 * dao层接口--商品分类
 * @author ChengQing
 *
 */
public interface ClassifyDao  extends BaseDao<Classify>{
	
	/**
	 * 校验分类名称是否存在
	 * @param classifyName
	 * @return
	 */
	Long queryCountByClassifyName(String classifyName);

}
