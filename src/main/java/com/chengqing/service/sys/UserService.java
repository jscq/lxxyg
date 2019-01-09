package com.chengqing.service.sys;

import com.chengqing.model.sys.User;
import com.chengqing.service.base.BaseService;


/**   
 * 类描述：   用户信息service   
 *    
 */
public interface UserService extends BaseService<User> {
	
	/**
	 * 校验用户名是否存在
	 * @param user
	 * @return
	 */
	Long queryCountByUserName(User user);
	
	
	
}
