package com.chengqing.service.sys;

import java.util.List;

import com.chengqing.model.sys.Function;
import com.chengqing.service.base.BaseService;
import com.chengqing.utils.Result;

import net.sf.json.JSONArray;

/**
 * 系统管理:菜单管理
 * @author chengqing
 *
 */
public interface FunctionService extends BaseService<Function> {
	
	/**
	 * 页面初始化查询菜单
	 * @param userId
	 * @param parentId
	 * @return
	 */
	List<Function> selectFunctionByUserId(String userId, String parentId) throws Exception;
	
	/**
	 * 系统菜单list页面   父级功能名称
	 * @param function
	 * @return
	 */
	JSONArray getFuncTreeJson(Function function) throws Exception;
	
	/**
	 * 校验菜单的唯一性(一级菜单校验名称、二级菜单在一级菜单下只能存在一个)
	 * @param funcNane
	 * @param parentId
	 * @return
	 */
	Long queryCountByFunctionName(String funcNane, String parentId) throws Exception;
	
	/**
	 * 删除菜单
	 * @param functionIds
	 * @return
	 */
	boolean deleteFunctionByIds(String functionIds) throws Exception;

}
