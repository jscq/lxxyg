package com.chengqing.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.chengqing.model.sys.Function;
import com.chengqing.service.sys.CodeService;
import com.chengqing.service.sys.FunctionService;
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
	
	@Autowired
	private FunctionService functionService;
	
	@Autowired
	private CodeService codeService;
	
	/**
	 * 登陆检验
	 * @param model 前端ui绑定数据类
	 * @return
	 */
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public ModelAndView login(Model model) {
		try {
			// 用户校验
			
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
	 * 后台首页显示
	 * @param model 前端ui绑定数据类
	 * @return
	 */
	@RequestMapping(value="/index", method = RequestMethod.GET)
	public ModelAndView index(Model model) {
		try {
			
		} catch (Exception e) {
			// 加载出错,应该跳转404页面
			
		}
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
