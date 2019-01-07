package com.chengqing.dao.sys;

import java.util.List;

import com.chengqing.dao.base.BaseDao;
import com.chengqing.model.sys.Area;

public interface AreaDao extends BaseDao<Area>{
	
	/**
	 * 初始加载所以一级行政
	 * @return
	 */
	List<Area> getAllParent();
	
	/**
	 * 根据所选一级行政加载下级行政
	 * @param id
	 * @return
	 */
	List<Area> findByParentId(String id);
	
	/**
	 * 校验同级的名称和代码不能重复
	 * @param area
	 * @return
	 */
	Long selectByCondition(Area area);

	
	
}
