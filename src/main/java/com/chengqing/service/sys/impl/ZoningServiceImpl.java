package com.chengqing.service.sys.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chengqing.dao.base.BaseDao;
import com.chengqing.dao.sys.ZoningDao;
import com.chengqing.model.sys.Zoning;
import com.chengqing.service.base.impl.BaseServiceImpl;
import com.chengqing.service.sys.ZoningService;

@Service
@Transactional
public class ZoningServiceImpl extends BaseServiceImpl<Zoning> implements ZoningService {

	private static Logger logger = Logger.getLogger(ZoningServiceImpl.class);
	
	@Autowired
	private ZoningDao zoningDao;

	@Override
	protected BaseDao<Zoning> getBaseDao() {
		return this.zoningDao;
	}
	
	
}
