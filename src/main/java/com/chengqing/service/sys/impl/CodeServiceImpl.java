package com.chengqing.service.sys.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chengqing.dao.base.BaseDao;
import com.chengqing.dao.sys.CodeDao;
import com.chengqing.model.sys.Code;
import com.chengqing.service.base.impl.BaseServiceImpl;
import com.chengqing.service.sys.CodeService;
import com.chengqing.utils.Result;
import com.chengqing.utils.Result.Status;

@Service
@Transactional
public class CodeServiceImpl extends BaseServiceImpl<Code> implements CodeService {

	private static Logger logger = Logger.getLogger(CodeServiceImpl.class);
	
	@Autowired
	private CodeDao codeDao;

	@Override
	protected BaseDao<Code> getBaseDao() {
		return this.codeDao;
	}

	/**
	 * 查询出所有上级(一级数据)
	 * @return
	 */
	public List<Code> getAllParent() {
		return codeDao.selectAllParent();
	}

	/**
	 * 验证数据字典重复性
	 * @param code
	 * @return
	 */
	public Result checkCode(Code code) {
		Result result = new Result();
		result.setStatus(Status.ERROR);
		if(StringUtils.isBlank(code.getParent().getId())){
			// 一级字典，验证数据名称、数据类型是否存在
			// 验证数据名称
			Code tempCodeByName = new Code();
			tempCodeByName.setCodeName(code.getCodeName());
			if(StringUtils.isNotEmpty(code.getId())){
				tempCodeByName.setId(code.getId());
			}
			Long countByCodeName = codeDao.checkCode(tempCodeByName);
			if(countByCodeName > 0){
				result.setMessage("数据名称已存在,请重新输入");
				return result;
			} 
			// 验证数据类型  
			Code tempCodeByType = new Code();  
			tempCodeByType.setCodeType(code.getCodeType()); 
			if(StringUtils.isNotEmpty(code.getId())){
				tempCodeByType.setId(code.getId());
			}
			Long countByCodeType = codeDao.checkCode(tempCodeByType);
 			if(countByCodeType > 0){
				result.setMessage("数据类型已存在,请重新输入");
				return result;
			}
		}else{
			// 二级字典，验证数据名称、数值是否存在
			// 验证数据名称
			Code tempCodeByName = new Code();
			tempCodeByName.setParent(code.getParent());
			tempCodeByName.setCodeName(code.getCodeName());
			if(StringUtils.isNotEmpty(code.getId())){
				tempCodeByName.setId(code.getId());
			}
			Long countByCodeName = codeDao.checkCode(tempCodeByName);
			if(countByCodeName > 0){
				result.setMessage("数据名称已存在,请重新输入");
				return result;
			} 
			// 数值  
			Code tempCodeByType = new Code();
			tempCodeByType.setParent(code.getParent());
			tempCodeByType.setCodeValue(code.getCodeValue());
			if(StringUtils.isNotEmpty(code.getId())){
				tempCodeByType.setId(code.getId());
			}
			Long countByCodeType = codeDao.checkCode(tempCodeByType);
 			if(countByCodeType > 0){
				result.setMessage("数值已存在,请重新输入");
				return result;
			}
		}
		result.setStatus(Status.OK);
		return result;
	}

	/**
	 * 删除本身以及子节点
	 */
	public void deleteSelfAndchild(String id) {
		// 子节点
		codeDao.deleteChild(id);
		// 本身
		codeDao.deleteById(id);
	}

	/**
	 * 保存或者更新
	 */
	public void saveOrUpdateCode(Code code) {
		//获取登录用户信息
		//User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(StringUtils.isNotEmpty(code.getId())){
			// 更新
			code.setUpdateTime(new Date());
			//code.setUpdateUserId(user.getId());
			codeDao.updateByIdSelective(code);
		}else{
			// 新增
			//code.setCreateUserId(user.getId());
			codeDao.insert(code);
		}
		//ConstantUtil.CODE_MAP = initCodeMap();
	}

	/**
	 * 点击一级字典，查询出二级子字典
	 */
	public List findByParentId(String id) {
		return codeDao.findByParentId(id);
	}

	/**
	 * 初始化字典表的缓存，以Map形式存在
	 * 
	 * 存放形式：
	 * 父名：对应子字典结果集
	 * 子字典结果集：	{子项名：子项值,子项名：子项值,子项名：子项值}
	 */
	@Override
	public Map initCodeMap() {
		logger.debug("初始化字典表");
		List<Code> allParent = getAllParent();
		Map codeMap = new HashedMap();
		for(Code c:allParent) 
		{
			String codeName = c.getCodeType();
			 
			Map childMap = new HashedMap();
			List<Map> findByParentId = (List<Map>)findByParentId(c.getId());
			for(Map childCode:findByParentId)
			{
				childMap.put(childCode.get("codeName"), childCode.get("codeValue"));
				childMap.put(childCode.get("codeValue"), childCode.get("codeName"));
			}
			codeMap.put(codeName, childMap);  
		}
		
		return codeMap;
	}

	@Override
	public Code selectByCondition(Code code) {
		return codeDao.selectByCondition(code);
	}
	
	
	public List<Code> selectByCodeType(String codeType){
		return codeDao.selectListByCodeType(codeType);
	}

}
