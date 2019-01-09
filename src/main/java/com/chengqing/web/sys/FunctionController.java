package com.chengqing.web.sys;

import java.util.ArrayList;
import java.util.List;

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

import com.chengqing.model.sys.Function;
import com.chengqing.model.sys.SysQuery;
import com.chengqing.service.base.BaseService;
import com.chengqing.service.sys.FunctionService;
import com.chengqing.utils.PageControl;
import com.chengqing.utils.ResData;
import com.chengqing.utils.Result;
import com.chengqing.utils.Result.Status;
import com.chengqing.web.BaseController;

import net.sf.json.JSONArray;

/**
 * 系统管理:菜单管理
 * @author chengqing
 * @version 1.1
 */
@Controller
@RequestMapping("/function")
public class FunctionController extends BaseController  {
	
	private static final long serialVersionUID = -8609502609172262236L;

	// 数据绑定
	@InitBinder("function")
    public void initFunction(WebDataBinder binder){
        binder.setFieldDefaultPrefix("function.");
    }
    @InitBinder("sysQuery")
    public void initSysQuery(WebDataBinder binder){
        binder.setFieldDefaultPrefix("sysQuery.");
    }
    
    // 菜单管理service层注入
    @Autowired
    private FunctionService functionService;
    
	protected BaseService getBaseService() {
		return functionService;
	}
	
    
    /**  
	 * 系统菜单list页面   
	 */
	@RequestMapping(value="/listFunction", method = RequestMethod.GET)
	public ModelAndView listFunc() {
		try {
			
		} catch (Exception e) {
			// 页面加载错误,跳转404
			
		}
		return new ModelAndView("/sys/function/listFunction");
	}
	
	/**
	 * 系统菜单list页面数据
	 * @param query 实体对象
	 * @param rows	行数
	 * @param page	页数
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/listFunctionJson", method = RequestMethod.POST)
	public ResData listFuncJson(@ModelAttribute Function function,int rows,int page)
	{
		ResData res = new ResData();
		try {
			PageControl pc = functionService.selectPageList(function,rows,page);
			List<Function> list = new ArrayList<Function>();
			res.setRows(null!=pc&&null!=pc.getList()?pc.getList():list);
			res.setTotal(pc.getTotalitem());
			res.setQuery(function);
		} catch (Exception e) {
			
		}
		return res;
	}
	
	
	/**  
	 * 系统菜单list页面   父级功能名称
	 */
	@ResponseBody
	@RequestMapping(value = "/getFunctionTreeJson", method = RequestMethod.POST)
	public JSONArray getFuncTreeJson(@ModelAttribute Function function)
	{
		//获取功能树
		JSONArray json = null;
		try {
			json = functionService.getFuncTreeJson(function);
		} catch (Exception e) {
			// 查询数据失败,前端要给个提示
		}
		return json;
	}
	
	
	/**
	 * 添加页面 
	 */
	@RequestMapping(value="/addFunction", method = RequestMethod.GET)
	public ModelAndView addFunc() {
		return new ModelAndView("/sys/function/addFunction");
	}
	
	/**
	 * 修改页面 
	 */
	@RequestMapping(value="/editFunction", method = RequestMethod.GET)
	public ModelAndView editFunction(@ModelAttribute Function function,Model model) {
		try {
			if (null != function && StringUtils.isNotEmpty(function.getId())) {
				function = functionService.selectById(function.getId());
				if(null != function){
					model.addAttribute("function", function);
				}
			}
		} catch (Exception e) {
		
		}
		return new ModelAndView("/sys/function/editFunction");
	}
	
	/**
	 * 保存功能信息
	 */
	@RequestMapping(value="/saveFunction",method = RequestMethod.POST)
	@ResponseBody
	public Result saveFunc(@ModelAttribute Function function) {
		Result result = new Result();
		try {
			//修改或保存
			result = functionService.saveOrUpdate(function);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(Status.ERROR, "");
		}
		return result;
	}
	
	/**  
	 * 删除功能及其子节点信息
	 */
	@ResponseBody
	@RequestMapping(value="/deleteFunction",method = RequestMethod.POST)
	public Result deleteFunction(String functionIds) {
		boolean b = false;
		try{
			if(StringUtils.isNotEmpty(functionIds)) {
				b = functionService.deleteFunctionByIds(functionIds);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		if(b) {
			return new Result(Status.OK,"");
		}else {
			return new Result(Status.ERROR,"");
		}
	}
	
	/**
	 * 校验菜单的唯一性(一级菜单校验名称、二级菜单在一级菜单下只能存在一个)
	 * @param sysQuery
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/verifyFunctionName",method = RequestMethod.POST)
	public Result verifyFunctionName(@ModelAttribute SysQuery sysQuery)
	{
		try {
			if(StringUtils.isNotEmpty(sysQuery.getFunctionName()))
			{
				Long result = functionService.queryCountByFunctionName(sysQuery.getFunctionName(),sysQuery.getParentId());
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
