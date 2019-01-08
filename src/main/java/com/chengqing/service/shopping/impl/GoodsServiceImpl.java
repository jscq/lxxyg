package com.chengqing.service.shopping.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chengqing.dao.base.BaseDao;
import com.chengqing.model.shopping.Goods;
import com.chengqing.service.base.impl.BaseServiceImpl;
import com.chengqing.service.shopping.GoodsService;

/**
 * service实现类--商品分类
 * @author ChengQing
 *
 */
@Service
@Transactional
public class GoodsServiceImpl extends BaseServiceImpl<Goods> implements GoodsService  {
	
	private static Logger logger = Logger.getLogger(GoodsServiceImpl.class);
	
	protected BaseDao<Goods> getBaseDao() {
		return null;
	}

}
