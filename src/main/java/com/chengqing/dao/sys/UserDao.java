package com.chengqing.dao.sys;

import com.chengqing.dao.base.BaseDao;
import com.chengqing.model.sys.User;

public interface UserDao extends BaseDao<User> {
	
	/**
	 * 校验用户名是否存在
	 * @param user
	 * @return
	 */
	Long queryCountByUserName(User user);
	
}
