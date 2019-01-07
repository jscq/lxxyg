package com.chengqing.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.chengqing.model.BaseModel;
import com.chengqing.service.base.BaseService;
import com.chengqing.utils.DateEditor;
import com.chengqing.utils.MyCustomNumberEditor;
import com.chengqing.utils.PageControl;
import com.chengqing.utils.ResData;
import com.chengqing.utils.Result;
import com.chengqing.utils.StringEditor;

/**
 * 基础controller
 * @author ChengQing
 * @version 1.2
 */
public abstract class BaseController<T extends BaseModel> implements Serializable {
	
	private static final long serialVersionUID = 1L;

	protected abstract BaseService<T> getBaseService();
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new DateEditor());     
	    binder.registerCustomEditor(int.class, new MyCustomNumberEditor(Integer.class));
	    binder.registerCustomEditor(Integer.class, new MyCustomNumberEditor(Integer.class)); 
	    binder.registerCustomEditor(double.class, new MyCustomNumberEditor(Double.class));
	    binder.registerCustomEditor(float.class, new MyCustomNumberEditor(Float.class));
	    binder.registerCustomEditor(long.class, new MyCustomNumberEditor(Long.class));
	    
	    binder.registerCustomEditor(String.class, new StringEditor());
	  
	}
	
	/**
	 * list页面数据
	 * @param query 实体对象
	 * @param rows	行数
	 * @param page	页数
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/listJson", method = RequestMethod.POST)
	public ResData listMajorJson(@ModelAttribute T t ,int rows,int page)
	{
		ResData res = new ResData();
		try {
			PageControl pc = getBaseService().selectPageList(t,rows,page);
			List<T> list = new ArrayList<T>();
			res.setRows(null!=pc&&null!=pc.getList()?pc.getList():list);
			res.setTotal(pc.getTotalitem());
			res.setQuery(t);
		} catch (Exception e) {
			
		}
		return res;
	}
	
//	 /**  
//		 * 列表
//		 * 
//		 */
//		@RequestMapping(value="/listClassify", method = RequestMethod.GET)
//		public ModelAndView listClassify() {
//			return new ModelAndView("/shopping/classify/listClassify");
//		}
//		
//		/**
//		 * 新增页面
//		 * @return
//		 */
//		@RequestMapping(value="/add", method = RequestMethod.GET)
//		public ModelAndView add() {
//			return new ModelAndView("///add");
//		}
//		
//		/**
//		 * 编辑页面
//		 * @param 
//		 * @param model
//		 * @return
//		 */
//		@RequestMapping(value="/edit", method = RequestMethod.GET)
//		public ModelAndView edit(@ModelAttribute  ,Model model) {
//			try {
//				
//			} catch (Exception e) {
//
//			}
//			return new ModelAndView("///edit");
//		}
//		
//		/**
//		 * 新增或者编辑数据保存
//		 * @param 
//		 * @return
//		 */
//		@ResponseBody
//		@RequestMapping(value="/saveOrUpdate", method = RequestMethod.POST)
//		public Result saveOrUpdate(@ModelAttribute ) {
//			Result result = new Result();
//			try {
//				if(null != ){
//					
//				}else{
//					return new Result(Status.ERROR,"数据异常");
//				}
//				result.setStatus(Status.OK);
//			} catch (Exception e) {
//				return new Result(Status.ERROR,e.getMessage());
//			}
//			return result;
//		}
//		
//		
//		/**  
//		 * ---删除
//		 *
//		 */
//		@ResponseBody
//		@RequestMapping(value="/delete", method = RequestMethod.GET)
//		public Result delete(@ModelAttribute  ) {
//			Result result = new Result();
//			try {
//				
//				result.setStatus(Status.OK);
//			} catch (Exception e) {
//				return new Result(Status.ERROR,e.getMessage());
//			}
//			return result;
//		}
	
}
