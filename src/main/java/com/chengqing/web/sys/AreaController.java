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

import com.chengqing.model.sys.Area;
import com.chengqing.service.base.BaseService;
import com.chengqing.service.sys.AreaService;
import com.chengqing.utils.PageControl;
import com.chengqing.utils.Result;
import com.chengqing.utils.Result.Status;
import com.chengqing.web.BaseController;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("/area")
public class AreaController extends BaseController{
	
	private static final long serialVersionUID = 674435902840642810L;

	@InitBinder("area")
    public void initDepartment(WebDataBinder binder){
        binder.setFieldDefaultPrefix("area.");
    }
    @InitBinder("sysQuery")
    public void initSysQuery(WebDataBinder binder){
        binder.setFieldDefaultPrefix("sysQuery.");
    }
    
    @Autowired
	private AreaService areaService;

	/**  
	 * 行政区域列表
	 *
	 * @author chengqing
	 * @version 1.0  
	 */
	@RequestMapping(value="/listArea", method = RequestMethod.GET)
	public ModelAndView listArea() {
		return new ModelAndView("/sys/area/listArea");
	}
	
	/**  
	 * 行政区域---添加
	 *
	 * @author chengqing
	 * @version 1.0  
	 */
	@RequestMapping(value="/addArea", method = RequestMethod.GET)
	public ModelAndView addArea() {
		return new ModelAndView("/sys/area/addArea","map",initLoadPage());
	}

	/**  
	 * 行政区域---编辑
	 *
	 * @author chengqing
	 * @version 1.0  
	 */
	@RequestMapping(value="/editArea", method = RequestMethod.GET)
	public ModelAndView editArea(@ModelAttribute Area area,Model model) {
		try {
			if(null != area && StringUtils.isNotEmpty(area.getId())){
				area = areaService.selectById(area.getId());
				if(null != area){
					model.addAttribute(area);
				}
			}
		} catch (Exception e) {

		}
		return new ModelAndView("/sys/area/editArea","map",initLoadPage());
	}
	
	/**  
	 * 行政区域---验证
	 *
	 * @author chengqing
	 * @version 1.0  
	 */
	@ResponseBody
	@RequestMapping(value="/checkArea", method = RequestMethod.POST)
	public Result checkArea(@ModelAttribute Area area) {
		Result result = null;
		try {
			result = areaService.checkArea(area);
		} catch (Exception e) {
			return new Result(Status.ERROR,e.getMessage());
		}
		return result;
	}
	
	/**  
	 * 行政区域---保存或更新
	 *
	 * @author chengqing
	 * @version 1.0  
	 */
	@ResponseBody
	@RequestMapping(value="/saveOrUpdateArea", method = RequestMethod.POST)
	public Result saveOrUpdateArea(@ModelAttribute Area area) {
		Result result = new Result();
		try {
			if(null != area){
				if(StringUtils.isNotEmpty(area.getId())){
					areaService.updateByIdSelective(area);
				}else{
					areaService.insert(area);
				}
			}else{
				return new Result(Status.ERROR,"数据异常");
			}
		} catch (Exception e) {
			return new Result(Status.ERROR,e.getMessage());
		}
		result.setStatus(Status.OK);
		return result;
	}
	
	
	/**  
	 * 行政区域---删除
	 *
	 * @author chengqing
	 * @version 1.0  
	 */
	@ResponseBody
	@RequestMapping(value="/deleteArea", method = RequestMethod.GET)
	public Result deleteCode(@ModelAttribute Area area) {
		Result result = new Result();
		try {
			// 子节点
			areaService.deleteById(area.getId());
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
		List<Area> areaList = null;
		try {
			areaList = areaService.getAllParent();
		} catch (Exception e) {
			e.printStackTrace();
		}
		returnMap.put("areaList", areaList);
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
	public Object getPageList(@ModelAttribute Area area,int rows,int page)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		List list = new ArrayList();
		try {
			if("0".equals(area.getId())){
				PageControl pc = areaService.selectPageList(area,rows,page);
				map.put("rows", null!=pc&&null!=pc.getList()?pc.getList():list);
				map.put("total", pc.getTotalitem());
				return map;
			}else{
				List childList = areaService.findByParentId(area.getId());
				return childList;
			}
		} catch (Exception e) {

		}
		return "";
		
	}
	
	/**
	 * 省市区json数据
	 */
	/**
	 * 加载树*(area)
	 */
	@ResponseBody
	@RequestMapping(value="/getAreaJson",method=RequestMethod.POST)
	public Object getAreaJson(@ModelAttribute Area area){
		JSONArray json = new JSONArray();
	
		List jsonList = new ArrayList();
		try{
			if("0".equals(area.getId())){
				List<Area> listArea = areaService.getAllParent();
				   for(Area a:listArea){
					   String ids= a.getId();
					   String name= a.getName();
					   Map<String, Object> map = new HashMap<String,Object>();
					   map.put("id", ids);
					   map.put("text", name);
					   map.put("state", "closed");
					   jsonList.add(map);
				   }
				   json = JSONArray.fromObject(jsonList);
			}else{
				List<Area>listArea=areaService.findByParentId(area.getId());
				   for(Area a:listArea){
					   String ids= a.getId();
					   String name= a.getName();
					   Map<String, Object> map = new HashMap<String,Object>();
					   map.put("id", ids);
					   map.put("text", name);
					   map.put("state", "closed");
					   jsonList.add(map);
				   }
				   json=JSONArray.fromObject(jsonList);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return json;
	}
	@Override
	protected BaseService getBaseService() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
