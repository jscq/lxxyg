package com.chengqing.dao.shopping.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.chengqing.dao.base.impl.BaseDaoImpl;
import com.chengqing.dao.shopping.ClassifyDao;
import com.chengqing.exception.DaoException;
import com.chengqing.model.shopping.Classify;

/**
 * dao层实现类-商品分类
 * @author ChengQing
 *
 */
@Repository
public class ClassifyDaoImpl  extends BaseDaoImpl<Classify> implements ClassifyDao{

	@Override
	public Long queryCountByClassifyName(String classifyName) {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("classifyName", classifyName);
			return sqlSessionTemplate.selectOne(getSqlName("selectCountByClassifyName"), params);
		} catch (Exception e) {
			throw new DaoException(String.format("查询对象列表出错！语句：%s", getSqlName("selectCountByClassifyName")), e);
		}
	}

}
