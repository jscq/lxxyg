package com.chengqing.web.sys;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.chengqing.model.sys.SysQuery;
import com.chengqing.model.sys.User;
import com.chengqing.service.base.BaseService;
import com.chengqing.service.sys.UserService;
import com.chengqing.utils.Encodes;
import com.chengqing.utils.Result;
import com.chengqing.utils.Result.Status;
import com.chengqing.web.BaseController;

/**
 * 系统管理:系统用户
 * @author chengqing
 * @version 1.0
 */
@Controller
@RequestMapping("/user")
public class UserController  extends BaseController<User> {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private UserService userService;
	
	protected BaseService<User> getBaseService() {
		return userService;
	}
	
	// 数据绑定
	@InitBinder("user")
    public void initFunction(WebDataBinder binder){
        binder.setFieldDefaultPrefix("user.");
    }
    @InitBinder("sysQuery")
    public void initSysQuery(WebDataBinder binder){
        binder.setFieldDefaultPrefix("sysQuery.");
    }
	
    
	 /**  
	 * 系统用户-列表
	 * 
	 */
	@RequestMapping(value="/listUser", method = RequestMethod.GET)
	public ModelAndView listUser() {
		return new ModelAndView("/sys/user/listUser");
	}
	
	/**
	 * 系统用户-新增页面
	 * @return
	 */
	@RequestMapping(value="/addUser", method = RequestMethod.GET)
	public ModelAndView addUser() {
		return new ModelAndView("/sys/user/addUser");
	}
	
	/**
	 * 系统用户-编辑页面
	 * @param 
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/editUser", method = RequestMethod.GET)
	public ModelAndView editUser(@ModelAttribute User user,Model model) {
		try {
			
		} catch (Exception e) {

		}
		return new ModelAndView("/sys/user/editUser");
	}
	
	/**
	 * 新增或者编辑数据保存
	 * @param 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/saveOrUpdate", method = RequestMethod.POST)
	public Result saveOrUpdate(User user) {
		Result result = new Result();
		try {
			if(null != user){
				user.setPassWord(Encodes.encodeMD5(user.getPassWord()));
				userService.saveOrUpdate(user);
			}else{
				return new Result(Status.ERROR,"数据异常");
			}
			result.setStatus(Status.OK);
		} catch (Exception e) {
			return new Result(Status.ERROR,e.getMessage());
		}
		return result;
	}
	
	
	/**  
	 * 系统用户---删除
	 *
	 */
	@ResponseBody
	@RequestMapping(value="/deleteUser", method = RequestMethod.POST)
	public Result delete(String ids) {
		try {
			userService.deleteByIds(ids);
		} catch (Exception e) {
			return new Result(Status.ERROR,e.getMessage());
		}
		return new Result(Status.OK,"");
	}
	
	/**  
	 * 系统用户---重置密码
	 *
	 */
	@ResponseBody
	@RequestMapping(value="/resetPassWord", method = RequestMethod.POST)
	public Result resetPassWord(String ids) {
		try {
			// userService.deleteByIds(ids);
			if(StringUtils.isNotEmpty(ids.toString())){
				String[] id = ids.toString().split(",");
				
				if(null != id && id.length > 0){
					for (String temp : id) {
						User user = getBaseService().selectById(temp);
						user.setPassWord(Encodes.encodeMD5("888888"));
						getBaseService().updateByIdSelective(user);
					}
				}
			}
		} catch (Exception e) {
			return new Result(Status.ERROR,e.getMessage());
		}
		return new Result(Status.OK,"");
	}
	
	
	
	/**
	 * 校验用户名的唯一性
	 * @param sysQuery
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/verifyUserName",method = RequestMethod.POST)
	public Result verifyUserName(User user)
	{
		try {
			if(StringUtils.isNotEmpty(user.getUserName()))
			{
				Long result = userService.queryCountByUserName(user);
				if(result==0)
				{
					return new Result(Status.OK,"");
				}
			}
		} catch (Exception e) {

		}
		return new Result(Status.ERROR,"");
	}

}
