package com.chengqing.dao.sys.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.chengqing.dao.base.impl.BaseDaoImpl;
import com.chengqing.dao.sys.UserDao;
import com.chengqing.exception.DaoException;
import com.chengqing.model.sys.User;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao{

	@Override
	public Long queryCountByUserName(User user) {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("userName", user.getUserName());
			params.put("passWord", user.getPassWord());
			return sqlSessionTemplate.selectOne(getSqlName("selectByUserName"), params);
		} catch (Exception e) {
			throw new DaoException(String.format("查询对象列表出错！语句：%s", getSqlName("selectByUserName")), e);
		}
	}
	
	
}
