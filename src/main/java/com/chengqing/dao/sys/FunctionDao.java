package com.chengqing.dao.sys;

import java.util.List;

import com.chengqing.dao.base.BaseDao;
import com.chengqing.model.sys.Function;

public interface FunctionDao extends BaseDao<Function> {
	
	/**
	 * 页面初始化查询菜单
	 * @param userId
	 * @param parentId
	 * @return
	 */
	List<Function> selectFunctionByUserId(String userId, String parentId);
	
	/**
	 * 校验菜单的唯一性(一级菜单校验名称、二级菜单在一级菜单下只能存在一个)
	 * @param funcNane
	 * @param parentId
	 * @return
	 */
	Long selectCountByFunctionName(String funcNane, String parentId);
	
}
