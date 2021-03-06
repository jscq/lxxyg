package com.chengqing.service.base;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.chengqing.model.sys.Function;
import com.chengqing.utils.PageControl;
import com.chengqing.utils.Result;
import com.chengqing.utils.Result.Status;

/**
 * service接口层 -- 基本
 * @author ChengQing
 * @version 1.0
 * @param <T>
 */
public interface BaseService<T extends Serializable> {
	
	/**
	 * 添加对象,如果要添加的对象没有设置Id或者Id为空字符串或者是空格，则添加数据之前会调用 generateId()方法设置Id
	 * 
	 * @param entity 要实例化的实体，不能为null
	 * @return 
	 */
	public void insert(T entity) throws Exception;
	
	/**
	 * 根据Id删除对象
	 * 
	 * @param id  要删除的ID，不能为null
	 * @return int 受影响结果数
	 */
	public int deleteById(Serializable id) throws Exception;
	
	/**
	 * 根据Id删除对象
	 * 
	 * @param id  要删除的ID，不能为null
	 * @return int 受影响结果数
	 */
	public void deleteByIds(Serializable id) throws Exception;
	
	/**
	 * 删除所有
	 * 
	 * @return int 受影响结果数
	 */
	public int deleteAll() throws Exception;
	
	/**
	 * 更新对象中已设置的字段，未设置的字段不更新
	 * 
	 * @param entity 要更新的实体对象，不能为null，切ID必须不为null
	 * @return int 受影响结果数
	 */
	public int updateByIdSelective(T entity) throws Exception;
	
	
	
	/**
	 * 保存或者更新
	 * @param t
	 * @return
	 */
	public Result saveOrUpdate(T t) throws Exception;
	
	/**
	 * 查询所有记录列表
	 * 
	 * @return List 结果列表
	 */
	public <V extends T> List<V> selectAll() throws Exception;
	
	/**
	 * 查询对象列表
	 * 
	 * @param query 查询参数，如果未null则查询所有
	 * @return 结果对象列表
	 */
	public <V extends T> List<V> selectList(T query) throws Exception;
	
	/**
	 * 通过Id查询一个对象，如果id为null这会抛出IllegalArgumentException异常
	 * 
	 * @param id 主键，不能为null
	 * @return  结果对象，如果未找到返回null
	 */
	public <V extends T> V selectById(Serializable id) throws Exception;
	
	/**
	 * 查询总记录数
	 * 
	 * @return long 记录总数
	 */
	public Long selectCount() throws Exception;
	
	
	/**
	 * 查询记录数
	 * 
	 * @param query 查询对象，如果为null，则查询对象总数
	 * @return long 记录总数
	 */
	public Long selectCount(T query) throws Exception;
	
	
	
	/**
	 * 查询数据（分页：rows和page）
	 * @param query
	 * @param rows
	 * @param page
	 * @return
	 */
	public <V extends T> PageControl selectPageList(Object query,int rows,int page) throws Exception;
	
	/**
	 * 指定的mapper里的sql查询数据（分页：rows和page）
	 * @param query
	 * @param rows
	 * @param page
	 * @param sqlId
	 * @param sqlCountId
	 * @return
	 */
	public <V extends T> PageControl selectPageList(Object query,int rows,int page,String sqlId,String sqlCountId) throws Exception;
	
	
}
