package com.chengqing.dao.sys.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.chengqing.constant.SqlId;
import com.chengqing.dao.base.impl.BaseDaoImpl;
import com.chengqing.dao.sys.CodeDao;
import com.chengqing.exception.DaoException;
import com.chengqing.model.sys.Code;

/**
 * 数据字典dao实现类
 * 
 * @author chengqing
 * @version 1.0
 */
@Repository
public class CodeDaoImpl extends BaseDaoImpl<Code> implements CodeDao{

	/**
	 * 查询所有一级数据,上级单位
	 * @return
	 */
	public List<Code> selectAllParent() {
		return sqlSessionTemplate.selectList(getSqlName(SqlId.SQL_SELECT_ALL_PARENT));
	}

	/**
	 * 验证数据字典
	 */
	public Long checkCode(Code code) {
		return sqlSessionTemplate.selectOne(getSqlName(SqlId.SQL_SELECT_COUNT_BY_CODE),code);
	}

	/**
	 * 删除子节点
	 * @return 
	 */
	public int deleteChild(String id) {
		return sqlSessionTemplate.delete(getSqlName(SqlId.SQL_DELETE_BY_PARENTID), id);
	}

	/**
	 * 点击一级字典，查询出二级子字典
	 */
	public List findByParentId(String id) {
		return sqlSessionTemplate.selectList(getSqlName(SqlId.SQL_SELECT_BY_PARENTID),id);
	}

	/**
	 * 根据一级节点名称查询code表
	 */
	public List<Code> findByParentName(String codeName) {
		return sqlSessionTemplate.selectList(getSqlName(SqlId.SQL_SELECT_BY_PARENTNAME),codeName);
	}


	public List<Code> selectListByCodeType(String codeType) {
		return sqlSessionTemplate.selectList(getSqlName(SqlId.SQL_SELECT_BY_CODETYPE),codeType);
	}
	
	public List<Code> selectListByCodeType(String codeType,String sqlId) {
		return sqlSessionTemplate.selectList(getSqlName(sqlId),codeType);
	}
	
	/**
	 * 根据条件精确查询(name)
	 */
	public Code selectByCondition(Code code) {
		try {
			return sqlSessionTemplate.selectOne(getSqlName("selectByCondition"),code);
		} catch (Exception e) {
			throw new DaoException(String.format("查询对象列表出错！语句：%s", getSqlName("selectByCondition")), e);
		}
	}

}
