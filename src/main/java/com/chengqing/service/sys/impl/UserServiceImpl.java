package com.chengqing.service.sys.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chengqing.dao.base.BaseDao;
import com.chengqing.dao.sys.UserDao;
import com.chengqing.model.sys.User;
import com.chengqing.service.base.impl.BaseServiceImpl;
import com.chengqing.service.sys.UserService;

@Service
@Transactional
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

	private static Logger logger = Logger.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserDao userDao;

	@Override
	protected BaseDao<User> getBaseDao() {
		return this.userDao;
	}
	
	
}
