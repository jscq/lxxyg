package com.chengqing.dao.sys.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.chengqing.dao.base.impl.BaseDaoImpl;
import com.chengqing.dao.sys.FunctionDao;
import com.chengqing.exception.DaoException;
import com.chengqing.model.sys.Function;

@Repository
public class FunctionDaoImpl extends BaseDaoImpl<Function> implements FunctionDao{
	
	/**
	 * 页面初始化查询菜单
	 * @param userId
	 * @param parentId
	 * @return
	 */
	public List<Function> selectFunctionByUserId(String userId, String parentId){
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("userId", userId);
			params.put("parentId", parentId);
			return sqlSessionTemplate.selectList(getSqlName("selectFunctionByUserId"), params);
		} catch (Exception e) {
			throw new DaoException(String.format("查询对象列表出错！语句：%s", getSqlName("selectFunctionByUserId")), e);
		}
	}
	
	/**
	 * 校验菜单的唯一性(一级菜单校验名称、二级菜单在一级菜单下只能存在一个)
	 * @param funcNane
	 * @param parentId
	 * @return
	 */
	public Long selectCountByFunctionName(String funcNane, String parentId){
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("funcNane", funcNane);
			params.put("parentId", parentId);
			return sqlSessionTemplate.selectOne(getSqlName("selectCountByFunctionName"), params);
		} catch (Exception e) {
			throw new DaoException(String.format("查询对象列表出错！语句：%s", getSqlName("selectCountByFunctionName")), e);
		}
	}
	
	
	
}
