package com.chengqing.service.sys;

import java.util.List;

import com.chengqing.model.sys.Area;
import com.chengqing.service.base.BaseService;
import com.chengqing.utils.Result;


/**   
 * 类描述：   行政区域service   
 *    
 */
public interface AreaService extends BaseService<Area> {
	
	/**
	 * 初始加载所以一级行政
	 * @return
	 */
	List<Area> getAllParent() throws Exception;
	
	/**
	 * 根据所选一级行政加载下级行政
	 * @param id
	 * @return
	 */
	List<Area> findByParentId(String id) throws Exception;
	
	/**
	 * 校验同级的名称和代码不能重复
	 * @param area
	 * @return
	 */
	Result checkArea(Area area) throws Exception;
	
	/**
	 * 删除
	 * @param id
	 */
	void deleteSelfAndchild(String id) throws Exception;

	
}
