package com.chengqing.service.sys.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chengqing.dao.base.BaseDao;
import com.chengqing.dao.sys.AreaDao;
import com.chengqing.model.sys.Area;
import com.chengqing.service.base.impl.BaseServiceImpl;
import com.chengqing.service.sys.AreaService;
import com.chengqing.utils.Result;
import com.chengqing.utils.Result.Status;

@Service
@Transactional
public class AreaServiceImpl extends BaseServiceImpl<Area> implements AreaService {

	private static Logger logger = Logger.getLogger(AreaServiceImpl.class);
	
	@Autowired
	private AreaDao areaDao;

	@Override
	protected BaseDao<Area> getBaseDao() {
		return this.areaDao;
	}
	
	/**
	 * 初始加载所以一级行政
	 * @return
	 */
	public List<Area> getAllParent(){
		return areaDao.getAllParent();
	}

	/**
	 * 根据所选一级行政加载下级行政
	 * @param id
	 * @return
	 */
	public List<Area> findByParentId(String id) throws Exception{
		return areaDao.findByParentId(id);
	}
	
	
	/**
	 * 校验同级的名称和代码不能重复
	 * @param area
	 * @return
	 */
	public Result checkArea(Area area) throws Exception{
		Result result = new Result(Status.OK,"");
		
		Long num = areaDao.selectByCondition(area);
		if(null != num && num > 0){
			return new Result(Status.ERROR,"同级已存在行政名称或者行政代码值");
		}
		return result;
	}
	
	
	/**
	 * 删除
	 * @param id
	 */
	public void deleteSelfAndchild(String id) throws Exception{
		
	}
}
