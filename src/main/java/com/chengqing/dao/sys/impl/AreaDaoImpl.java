package com.chengqing.dao.sys.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.chengqing.dao.base.impl.BaseDaoImpl;
import com.chengqing.dao.sys.AreaDao;
import com.chengqing.model.sys.Area;

/**
 * 行政区域dao实现类
 * 
 * @author chengqing
 * @version 1.0
 */
@Repository
public class AreaDaoImpl extends BaseDaoImpl<Area> implements AreaDao{

	/**
	 * 初始加载所以一级行政
	 * @return
	 */
	public List<Area> getAllParent(){
		return sqlSessionTemplate.selectList(getSqlName("selectAllParent"));
	}

	
	/**
	 * 根据所选一级行政加载下级行政
	 * @param id
	 * @return
	 */
	public List<Area> findByParentId(String id){
		return sqlSessionTemplate.selectList(getSqlName("selectByParentId"),id);
	}
	
	
	/**
	 * 校验同级的名称和代码不能重复
	 * @param area
	 * @return
	 */
	public Long selectByCondition(Area area){
		return sqlSessionTemplate.selectOne(getSqlName("selectCodeCount"),area);
	}
}
