package com.chengqing.service.sys.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chengqing.dao.base.BaseDao;
import com.chengqing.dao.sys.FunctionDao;
import com.chengqing.model.sys.Function;
import com.chengqing.service.base.impl.BaseServiceImpl;
import com.chengqing.service.sys.FunctionService;
import com.chengqing.utils.Result;
import com.chengqing.utils.Result.Status;

import net.sf.json.JSONArray;

/**
 * 系统管理:菜单管理
 * @author chengqing
 *
 */
@Service
@Transactional
public class FunctionServiceImpl extends BaseServiceImpl<Function> implements FunctionService {
	
	// dao层---菜单管理
	@Autowired
	private FunctionDao functionDao;
	
	protected BaseDao<Function> getBaseDao() {
		return functionDao;
	}
	
	/**
	 * 页面初始化查询菜单
	 * @param userId
	 * @param parentId
	 * @return
	 */
	public List<Function> selectFunctionByUserId(String userId, String parentId) throws Exception{
		return functionDao.selectFunctionByUserId(userId,parentId);
	}

	/**
	 * 系统菜单list页面   父级功能名称
	 * @param function
	 * @return
	 */
	public JSONArray getFuncTreeJson(Function function) throws Exception{
		JSONArray json = new JSONArray();
		
		Function query = new Function();
		query.setParent(new Function());
		query.getParent().setId(null);
		
		List<Function> list = functionDao.selectList(query);
		if (null != list && list.size() > 0) {
			List<Map<String, Object>> jsonList = new ArrayList<Map<String, Object>>();
			Map<String, Object> map = null;
			for (int i = 0; i < list.size(); i++) {
				Function data = (Function)list.get(i);
				
				String functionName = data.getFunctionName();
				// 去除系统管理菜单不显示
//				if(!functionName.equals(ConstantUtil.MENU_NAME_SYSTEM_MANAGER)){
					map = new HashMap<String, Object>();
					map.put("id", data.getId());
					map.put("text", functionName);
					List<Map<String, Object>> childList = this.getChildNodeList(data);
					if (null != childList && childList.size() > 0) {
						map.put("children", this.getChildNodeList(data));
						map.put("state", "closed");
					}
					jsonList.add(map);
//				}
			}
			json = JSONArray.fromObject(jsonList);
		}
		return json;
	}
	
	
	/**  
	 * 系统菜单list页面   父级功能名称  (获取子节点)    
	 */
	private List<Map<String, Object>> getChildNodeList(Function parent){
		List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
		if (null != parent) {
			Function query = new Function();
			query.setParent(parent);
			List<Function> list = functionDao.selectList(query);
			if (null != list && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					Function data = (Function)list.get(i);
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("id", data.getId());
					map.put("text", data.getFunctionName());
					
					List<Map<String, Object>> childList = this.getChildNodeList(data);
					if (null != childList && childList.size() > 0) {
						map.put("children", this.getChildNodeList(data));
						map.put("state", "closed");
					}
					returnList.add(map);
				}
			}
		}
		return returnList;
	}
	
	
	/**
	 * 校验菜单的唯一性(一级菜单校验名称、二级菜单在一级菜单下只能存在一个)
	 * @param funcNane
	 * @param parentId
	 * @return
	 */
	public Long queryCountByFunctionName(String funcNane, String parentId) throws Exception{
		return functionDao.selectCountByFunctionName(funcNane,parentId);
	}	
	
	/**
	 * 删除菜单
	 * @param functionIds
	 * @return
	 */
	public boolean deleteFunctionByIds(String functionIds) throws Exception{
		if (StringUtils.isNotEmpty(functionIds)) {
			String[] ids = functionIds.split(",");
			for (int i = 0; i < ids.length; i++) {
				String id = ids[i];
				this.deleteFunction(id);
				functionDao.deleteById(id);
			}
		}
		return true;
	}

	/**
	 * 循环删除功能及其子类
	 *
	 */
	private void deleteFunction(String id){
		
		Function query = new Function();
		query.setParent(new Function());
		query.getParent().setId(id);
		
		//查询子节点
		List<Function> list = functionDao.selectList(query);
		if (null != list && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Function func = list.get(i);
				//遍历删除
				this.deleteFunction(func.getId());
				functionDao.deleteById(func.getId());
			}
		}
	}
	
}
