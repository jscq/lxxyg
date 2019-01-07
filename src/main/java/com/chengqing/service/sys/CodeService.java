package com.chengqing.service.sys;

import java.util.List;
import java.util.Map;

import com.chengqing.model.sys.Code;
import com.chengqing.service.base.BaseService;
import com.chengqing.utils.Result;


/**   
 * 类描述：   数据字典service
 * 
 * 项目名称：sys   
 * 类名称：CodeService   
 * 创建人：WT   
 * 创建时间：2015年6月24日 上午10:57:01   
 * 修改人：   
 * 修改时间：   
 * 修改备注：   
 * @version    
 *    
 */
public interface CodeService extends BaseService<Code> {

	/**
	 * 查询出所有上级(一级数据)
	 * @return
	 */
	List<Code> getAllParent();
	
	/**
	 * 验证数据字典重复性
	 * @param code
	 * @return
	 */
	Result checkCode(Code code);

	/**
	 * 删除本身以及子节点
	 * @param id
	 */
	void deleteSelfAndchild(String id);

	/**
	 * 保存或者更新
	 * @param code
	 */
	void saveOrUpdateCode(Code code);

	/**
	 * 点击一级字典，查询出二级子字典
	 * @param id
	 * @return
	 */
	List<Code> findByParentId(String id);
	
	Map initCodeMap();
	
	/**
	 * 查询
	 * @param code
	 * @return
	 */
	Code selectByCondition(Code code);
	
	List<Code> selectByCodeType(String codeName);
	
}
