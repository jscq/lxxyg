package com.chengqing.web.shopping;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
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

import com.chengqing.model.shopping.Classify;
import com.chengqing.model.sys.Function;
import com.chengqing.model.sys.SysQuery;
import com.chengqing.service.base.BaseService;
import com.chengqing.service.shopping.ClassifyService;
import com.chengqing.service.shopping.impl.ClassifyServiceImpl;
import com.chengqing.utils.PageControl;
import com.chengqing.utils.ResData;
import com.chengqing.utils.Result;
import com.chengqing.utils.Result.Status;
import com.chengqing.web.BaseController;

/**
 * 商品类别管理
 * @author ChengQing
 *
 */
@Controller
@RequestMapping("/classify")
public class ClassifyController extends BaseController<Classify>{

	private static final long serialVersionUID = 1L;
	
	private static Logger logger = Logger.getLogger(ClassifyController.class);
	
	@InitBinder("classify")
    public void initDepartment(WebDataBinder binder){
        binder.setFieldDefaultPrefix("classify.");
    }
    @InitBinder("shoppingQuery")
    public void initSysQuery(WebDataBinder binder){
        binder.setFieldDefaultPrefix("shoppingQuery.");
    }
    
    @Autowired
	private ClassifyService classifyService;
    
    protected BaseService<Classify> getBaseService() {
		return classifyService;
	}
	
    
    /**  
	 * 商品类别列表
	 * 
	 */
	@RequestMapping(value="/listClassify", method = RequestMethod.GET)
	public ModelAndView listClassify() {
		return new ModelAndView("/shopping/classify/listClassify");
	}
	
	
	/**
	 * 商品分类list页面数据
	 * @param query 实体对象
	 * @param rows	行数
	 * @param page	页数
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/listClassifyJson", method = RequestMethod.POST)
	public ResData listFuncJson(@ModelAttribute Classify classify,int rows,int page)
	{
		ResData res = new ResData();
		try {
			PageControl pc = classifyService.selectPageList(classify,rows,page);
			List<Function> list = new ArrayList<Function>();
			res.setRows(null!=pc&&null!=pc.getList()?pc.getList():list);
			res.setTotal(pc.getTotalitem());
			res.setQuery(classify);
		} catch (Exception e) {
			
		}
		return res;
	}
	
	
	/**
	 * 商品类别新增页面
	 * @return
	 */
	@RequestMapping(value="/addClassify", method = RequestMethod.GET)
	public ModelAndView addClassify() {
		return new ModelAndView("/shopping/classify/addClassify");
	}
	
	
	/**
	 * 商品类别编辑页面
	 * @param classify
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/editClassify", method = RequestMethod.GET)
	public ModelAndView editClassify(@ModelAttribute Classify classify ,Model model) {
		try {
			if (null != classify && StringUtils.isNotEmpty(classify.getId())) {
				classify = classifyService.selectById(classify.getId());
				if(null != classify){
					model.addAttribute("classify", classify);
				}
			}
		} catch (Exception e) {

		}
		return new ModelAndView("/shopping/classify/editClassify");
	}
	
	/**
	 * 新增或者编辑数据保存
	 * @param 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/saveOrUpdate", method = RequestMethod.POST)
	public Result saveOrUpdate(@ModelAttribute Classify classify) {
		Result result = new Result();
		try {
			if(null != classify){
				result = classifyService.saveOrUpdate(classify);
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
	 * ---删除
	 *
	 */
	@ResponseBody
	@RequestMapping(value="/deleteClassify", method = RequestMethod.POST)
	public Result deleteClassify(String ids) {
		try {
			classifyService.deleteByIds(ids);
		} catch (Exception e) {
			return new Result(Status.ERROR,e.getMessage());
		}
		return new Result(Status.OK,"");
	}
	
	
	/**
	 * 校验分类的唯一性
	 * @param sysQuery
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/verifyClassifyName",method = RequestMethod.POST)
	public Result verifyFunctionName(@ModelAttribute Classify classify)
	{
		try {
			if(StringUtils.isNotEmpty(classify.getClassifyName()))
			{
				Long result = classifyService.queryCountByClassifyName(classify.getClassifyName());
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
