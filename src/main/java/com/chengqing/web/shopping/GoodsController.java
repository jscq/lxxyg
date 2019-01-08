package com.chengqing.web.shopping;

import java.util.List;

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
import com.chengqing.model.shopping.Goods;
import com.chengqing.service.base.BaseService;
import com.chengqing.service.shopping.ClassifyService;
import com.chengqing.service.shopping.GoodsService;
import com.chengqing.utils.Result;
import com.chengqing.utils.Result.Status;
import com.chengqing.web.BaseController;

/**
 * 商品管理
 * @author ChengQing
 *
 */
@Controller
@RequestMapping("/goods")
public class GoodsController  extends BaseController<Goods>{

	private static final long serialVersionUID = 6517186761554706496L;

	private static Logger logger = Logger.getLogger(GoodsController.class);

	
	@InitBinder("goods")
    public void initDepartment(WebDataBinder binder){
        binder.setFieldDefaultPrefix("goods.");
    }
    @InitBinder("shoppingQuery")
    public void initSysQuery(WebDataBinder binder){
        binder.setFieldDefaultPrefix("shoppingQuery.");
    }
    
    @Autowired
	private GoodsService goodsService;
    
    @Autowired
 	private ClassifyService classifyService;
    
    protected BaseService<Goods> getBaseService() {
		return goodsService;
	}
    
    
	 /**  
	 * 列表
	 * 
	 */
	@RequestMapping(value="/listGoods", method = RequestMethod.GET)
	public ModelAndView listGoods() {
		return new ModelAndView("/shopping/goods/listGoods");
	}
	
	/**
	 * 新增页面
	 * @return
	 */
	@RequestMapping(value="/addGoods", method = RequestMethod.GET)
	public ModelAndView addGoods(Model model) {
		// 查询所以没有下架的商品分类
		Classify classify = new Classify();
		classify.setClassifyStatus(0);
		List<Classify> listClassify = null;
		try {
			listClassify = classifyService.selectList(classify);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info(listClassify);
		model.addAttribute("listClassify", listClassify);
		return new ModelAndView("/shopping/goods/addGoods");
	}
	
	/**
	 * 编辑页面
	 * @param 
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/edit", method = RequestMethod.GET)
	public ModelAndView edit(@ModelAttribute  Goods goods,Model model) {
		try {
			
		} catch (Exception e) {

		}
		return new ModelAndView("/shopping/goods/edit");
	}
	
	/**
	 * 新增或者编辑数据保存
	 * @param 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/saveOrUpdate", method = RequestMethod.POST)
	public Result saveOrUpdate(@ModelAttribute  Goods goods) {
		Result result = new Result();
		try {
			if(null != goods){
				
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
	@RequestMapping(value="/delete", method = RequestMethod.GET)
	public Result delete(@ModelAttribute  Goods goods) {
		Result result = new Result();
		try {
			
			result.setStatus(Status.OK);
		} catch (Exception e) {
			return new Result(Status.ERROR,e.getMessage());
		}
		return result;
	}
	
}
