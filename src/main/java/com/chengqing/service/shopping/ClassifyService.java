package com.chengqing.service.shopping;

import com.chengqing.model.shopping.Classify;
import com.chengqing.service.base.BaseService;

/**
 * service层接口--商品分类
 * @author ChengQing
 *
 */
public interface ClassifyService extends BaseService<Classify>  {
	
	/**
	 * 校验分类名称是否存在
	 * @param classifyName
	 * @return
	 */
	Long queryCountByClassifyName(String classifyName);
	
	
}
