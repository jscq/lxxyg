package com.chengqing.dao.sys;

import java.util.List;

import com.chengqing.dao.base.BaseDao;
import com.chengqing.model.sys.Code;

public interface CodeDao extends BaseDao<Code>{

	/**
	 * 查询出所有上级(一级数据)
	 * @return
	 */
	List<Code> selectAllParent();

	/**
	 * 验证数据字典
	 * @param code
	 * @return 
	 */
	Long checkCode(Code code);
	
	/**
	 * 删除子节点
	 * @param id
	 */
	int deleteChild(String id);
	
	/**
	 * 点击一级字典，查询出二级子字典
	 * @param id
	 * @return 
	 */
	List findByParentId(String id);
	
	/**
	 * 根据一级节点名称查询code表
	 * @param parentName
	 * @return
	 */
	List<Code> findByParentName(String codeName);

	/**
	 * 根据codeType查子节点
	 * @param codeType
	 * @return
	 */
	List<Code> selectListByCodeType(String codeType);

	/**
	 * 根据codeType查子节点
	 * @param codeType
	 * @return
	 */
	List<Code> selectListByCodeType(String codeType,String sqlId);

	/**  
	 * 方法说明：(根据条件精确查询) 
	 *    
	 * @author:WT  
	 * @date:2015年7月28日    
	 */
	public Code selectByCondition(Code code);
	
}
