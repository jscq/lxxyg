package com.chengqing.web.sys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
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

import com.chengqing.model.sys.Code;
import com.chengqing.model.sys.SysQuery;
import com.chengqing.service.base.BaseService;
import com.chengqing.service.sys.CodeService;
import com.chengqing.utils.PageControl;
import com.chengqing.utils.Result;
import com.chengqing.utils.Result.Status;
import com.chengqing.web.BaseController;

@Controller
@RequestMapping("/code")
public class CodeController extends BaseController{
	
	private static final long serialVersionUID = 674435902840642810L;

	@InitBinder("code")
    public void initDepartment(WebDataBinder binder){
        binder.setFieldDefaultPrefix("code.");
    }
    @InitBinder("sysQuery")
    public void initSysQuery(WebDataBinder binder){
        binder.setFieldDefaultPrefix("sysQuery.");
    }
    
    @Autowired
	private CodeService codeService;

	/**  
	 * 数据字典列表
	 *
	 * @author chengqing
	 * @version 1.0  
	 */
	@RequestMapping(value="/listCode", method = RequestMethod.GET)
	public ModelAndView listCode() {
		return new ModelAndView("/sys/code/listCode");
	}
	
	/**  
	 * 数据字典---添加
	 *
	 * @author chengqing
	 * @version 1.0  
	 */
	@RequestMapping(value="/addCode", method = RequestMethod.GET)
	public ModelAndView addCode() {
		return new ModelAndView("/sys/code/addCode","map",initLoadPage());
	}

	/**  
	 * 数据字典---编辑
	 *
	 * @author chengqing
	 * @version 1.0  
	 */
	@RequestMapping(value="/editCode", method = RequestMethod.GET)
	public ModelAndView editCode(@ModelAttribute Code code,Model model) {
		try {
			if(StringUtils.isNotEmpty(code.getId())){
				code = codeService.selectById(code.getId());
				model.addAttribute(code);
			}
		} catch (Exception e) {

		}
		return new ModelAndView("/sys/code/editCode","map",initLoadPage());
	}
	
	/**  
	 * 数据字典---验证
	 *
	 * @author chengqing
	 * @version 1.0  
	 */
	@ResponseBody
	@RequestMapping(value="/checkCode", method = RequestMethod.POST)
	public Result checkCode(@ModelAttribute Code code) {
		Result result = null;
		try {
			result = codeService.checkCode(code);
		} catch (Exception e) {
			return new Result(Status.ERROR,e.getMessage());
		}
		return result;
	}
	
	/**  
	 * 数据字典---保存或更新
	 *
	 * @author chengqing
	 * @version 1.0  
	 */
	@ResponseBody
	@RequestMapping(value="/saveOrUpdateCode", method = RequestMethod.POST)
	public Result saveOrUpdateCode(@ModelAttribute Code code) {
		Result result = new Result();
		try {
			codeService.saveOrUpdateCode(code);
		} catch (Exception e) {
			return new Result(Status.ERROR,e.getMessage());
		}
		result.setStatus(Status.OK);
		return result;
	}
	
	
	/**  
	 * 数据字典---删除
	 *
	 * @author chengqing
	 * @version 1.0  
	 */
	@ResponseBody
	@RequestMapping(value="/deleteCode", method = RequestMethod.GET)
	public Result deleteCode(@ModelAttribute Code code) {
		Result result = new Result();
		try {
			// 子节点
			codeService.deleteSelfAndchild(code.getId());
		} catch (Exception e) {
			return new Result(Status.ERROR,e.getMessage());
		}
		result.setStatus(Status.OK);
		return result;
	}
	
	
	/**  
	 * 所属上级
	 *
	 * @author chengqing  
	 * @version 1.0    
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map initLoadPage(){
		Map returnMap = new HashMap();
		// 所属上级
		List<Code> codeList = codeService.getAllParent();
		returnMap.put("codeList", codeList);
		return returnMap;
	}
	
	/**
	 * 分页
	 * @param query 实体对象
	 * @param rows	行数
	 * @param page	页数
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/pageList", method = RequestMethod.POST)
	@SuppressWarnings("rawtypes")
	public Object getPageList(@ModelAttribute SysQuery sysQuery,int rows,int page)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		List list = new ArrayList();
		try {
			if("0".equals(sysQuery.getId())){
				PageControl pc = codeService.selectPageList(sysQuery,rows,page);
				map.put("rows", null!=pc&&null!=pc.getList()?pc.getList():list);
				map.put("total", pc.getTotalitem());
				return map;
			}else{
				List childList = codeService.findByParentId(sysQuery.getId());
				return childList;
			}
		} catch (Exception e) {

		}
		return "";
		
	}
	@Override
	protected BaseService getBaseService() {
		// TODO Auto-generated method stub
		return null;
	}
}
