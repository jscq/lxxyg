package com.chengqing.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.chengqing.model.sys.Function;
import com.chengqing.model.sys.User;
import com.chengqing.service.sys.CodeService;
import com.chengqing.service.sys.FunctionService;
import com.chengqing.service.sys.UserService;
import com.chengqing.utils.Encodes;
import com.chengqing.utils.Result;
import com.chengqing.utils.Result.Status;

/**
 * 后台信息
 * @author chengqing
 * 
 */
@Controller
@RequestMapping("/index")
public class IndexController {
	/**  
	 * 字典表缓存
	 */   
	public static Map CODE_MAP = new HashMap();
	
	// 数据绑定
	@InitBinder("user")
    public void initFunction(WebDataBinder binder){
        binder.setFieldDefaultPrefix("user.");
    }
	
	@Autowired
	private FunctionService functionService;
	
	@Autowired
	private CodeService codeService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private HttpSession httpSession;
	
	/**
	 * 跳转登录页
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/toLogin", method = RequestMethod.GET)
	public ModelAndView toLogin(User user) {
		// 首先要判断是不是已登录
		String name = (String) httpSession.getAttribute("userName");
		if(StringUtils.isNotEmpty(name)) {
			// 已登录，跳转首页
			return new ModelAndView("redirect:/index/toIndex");
		}else {
			// 未登录,跳转登录页
			return new ModelAndView("../../login");
		}
	}
	
	/**
	 * 登陆检验
	 * @param model 前端ui绑定数据类
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public Result login(User user) {
		try {
			user.setPassWord(Encodes.encodeMD5(user.getPassWord()));
			// 用户校验
			long count = userService.queryCountByUserName(user);
			if(count == 1) {
				// 加入session
				httpSession.setAttribute("userName",user.getUserName());
				httpSession.setAttribute("passWord",user.getPassWord());
				return new Result(Status.OK,"");
			}
			return new Result(Status.ERROR,"");
		} catch (Exception e) {
			return new Result(Status.ERROR,e.getMessage());
		}
	}
	
	/**
	 * 后台框架
	 * @param model 前端ui绑定数据类
	 * @return
	 */
	@RequestMapping(value="/toIndex", method = RequestMethod.GET)
	public ModelAndView toIndex(Model model) {
		try {
			// 用户信息
			String name = (String) httpSession.getAttribute("userName");
			model.addAttribute("userName", name);
			
			// 功能菜单加载
			List<Function> returnFunctionList = new ArrayList<Function>();
			
			// 一级菜单
			List<Function> listFunction = functionService.selectFunctionByUserId(null, null);
			// 一级菜单下的二级菜单
			List<Function> childList = null;
			for (Function function : listFunction) {
				childList = functionService.selectFunctionByUserId(null, function.getId());
				if(childList != null && childList.size() > 0){
					function.setChildFuncList(childList);
				}
				returnFunctionList.add(function);
			}
			if(null != returnFunctionList && returnFunctionList.size() > 0){
				model.addAttribute("functionList", returnFunctionList);
			}
		} catch (Exception e) {
			// 加载出错,应该跳转404页面
			
		}
		return new ModelAndView("/index");
	}
	
	/**
	 * 退出
	 * @param model 前端ui绑定数据类
	 * @return
	 */
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public ModelAndView layout(User user) {
		try {
			httpSession.removeAttribute("userName");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("redirect:/index/toLogin");
	}
	
	
	/**
	 * 后台首页显示
	 * @param model 前端ui绑定数据类
	 * @return
	 */
	@RequestMapping(value="/index", method = RequestMethod.GET)
	public ModelAndView index(Model model) {
		
		return new ModelAndView("../../common/building");
	}
	
	
	/**  
	 * 方法说明：初始化编码表
	 *    
	 * @param  参数名称：编码类型     
	 * @return 返回值类型   
	 * @Exception 异常对象  
	 */
	@ResponseBody
	@RequestMapping(value="/initCode",method = RequestMethod.GET)
	public Result initCode(String codeType)
	{
		if(StringUtils.isNotEmpty(codeType))
		{
			List<?> list = codeService.selectByCodeType(codeType);
			return (null!=list&&list.size()>0)?new Result(Status.OK,list):new Result(Status.ERROR,"");
		}else
		{
			Map initCodeMap = CODE_MAP;
			return new Result(Status.OK,initCodeMap);
		}
	}
	
	
	
}
