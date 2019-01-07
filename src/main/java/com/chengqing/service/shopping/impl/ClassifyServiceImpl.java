package com.chengqing.service.shopping.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chengqing.dao.base.BaseDao;
import com.chengqing.dao.shopping.ClassifyDao;
import com.chengqing.model.shopping.Classify;
import com.chengqing.service.base.impl.BaseServiceImpl;
import com.chengqing.service.shopping.ClassifyService;

/**
 * service实现类--商品分类
 * @author ChengQing
 *
 */
@Service
@Transactional
public class ClassifyServiceImpl  extends BaseServiceImpl<Classify> implements ClassifyService  {

	private static Logger logger = Logger.getLogger(ClassifyServiceImpl.class);
	
	@Autowired
	private ClassifyDao classifyDao;
	
	protected BaseDao<Classify> getBaseDao() {
		return classifyDao;
	}

	public Long queryCountByClassifyName(String classifyName) {
		logger.info("查询分类名称是否存在");
		return classifyDao.queryCountByClassifyName(classifyName);
	}


	
	


}
