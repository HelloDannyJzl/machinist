package com.zhiyou.demo.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.zhiyou.demo.service.OrdersService;
import com.zhiyou.demo.service.peoplemanagement.userservice.UserLoginService;
import com.zhiyou.demo.until.ControllerUtil;
import com.zhiyou.demo.until.SessionUitl;

import jodd.util.StringUtil;



/** 
* @author wangyanlong: 
* 类说明  订单controller处理类
*/
@Controller
@RequestMapping("/orders")
public class OrdersController {
	private Logger logger = LogManager.getLogger();
	
	@Resource
	private OrdersService ordersService;
	@Resource
	private UserLoginService userLoginService;
	
	/**
	 * 转向添加订单
	 * @param request
	 * @return 添加订单详情页
	 */
	@RequestMapping("/toAddOrders")
	public ModelAndView toAddOrders(HttpServletRequest request){
		ModelAndView mav = new ModelAndView("wyl/ordersAdd");
		String projectid = request.getParameter("pronumber");
		String farmername = SessionUitl.getName(request);
		String id = SessionUitl.getId(request);
		Map<String,Object> map = new HashMap<>();
		map.put("id", id);
		List<Map<String, Object>> queryUser = userLoginService.queryUser(map);
		String phone = queryUser.get(0).get("phone").toString();
		map.put("farmername", farmername);
		map.put("projectid", projectid);
		map.put("phone", phone);
		mav.addObject("map",map);
		return mav;
	}

	/**
	 * 创建订单
	 * @map request HttpServletRequest
	 */                                                                               
	@RequestMapping("/addOrders")
	public void addOrders(HttpServletRequest request,HttpServletResponse response){
		String message = null;
		String projectid = request.getParameter("projectid");
		String farmername = request.getParameter("farmername");
		String start =request.getParameter("starttime");
		String end =request.getParameter("endtime");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		Date starttime = null;
		Date endtime = null;
		try {
			starttime = format.parse(start);
			endtime = format.parse(end);
		} catch (ParseException e) {
			logger.error("OrdersController.addOrders",e);
		}
		int number = Integer.valueOf(request.getParameter("number"));
		double price = Double.valueOf(request.getParameter("price"));
		boolean flag = ordersService.addOrders(projectid, farmername, starttime, endtime, number, price);
		if(flag){
			message = "{\"message\":\"1\"}";
		}else{
			message = "{\"message\":\"2\"}";
		}
		ControllerUtil.writeAjaxReturn(response, message);
	}
	
	/**
	 * （条件）查询订单
	 * @param request
	 * @return 返回查询到的订单页面
	 */
	@RequestMapping("/queryOrders")
	public ModelAndView queryOrders(HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		String totalpage = request.getParameter("totalpage");
		int totalnumber;
		String projectid = request.getParameter("projectid");
		String ordersid = request.getParameter("ordersid");
		String foremanname = request.getParameter("foremanname");
		String flag = request.getParameter("flag");
		if("5".equals(flag))
			flag = null;
		String roleName = SessionUitl.getRoleName(request);
		String name = SessionUitl.getName(request);
		List<Map<String, Object>> queryALLOrders = new ArrayList<>();
		if("管理员".equals(roleName)){
			totalnumber = ordersService.queryCount(projectid, ordersid, null, null, flag);
			ControllerUtil.setPageParam(totalnumber, totalpage, mav, request);
			queryALLOrders = ordersService.queryOrders(projectid,ordersid,null,null,flag,Integer.valueOf(String.valueOf(mav.getModel().get("currentpage"))));
			mav.setViewName("wyl/ordersCheck");
		}else if("农民".equals(roleName)){
			totalnumber = ordersService.queryCount(projectid, ordersid, name, null, flag);
			ControllerUtil.setPageParam(totalnumber, totalpage, mav, request);
			queryALLOrders = ordersService.queryOrders(projectid,ordersid,name,null,flag,Integer.valueOf(String.valueOf(mav.getModel().get("currentpage"))));
			mav.setViewName("wyl/ordersIndex");
		}else if("工头".equals(roleName)){
			if(flag == null){
				flag = "1";
				totalnumber = ordersService.queryCount(projectid, ordersid, null, null, flag);
				ControllerUtil.setPageParam(totalnumber, totalpage, mav, request);
				queryALLOrders = ordersService.queryOrders(projectid,ordersid,null,null,flag,Integer.valueOf(String.valueOf(mav.getModel().get("currentpage"))));
				mav.setViewName("wyl/ordersAccept");
			}else if("0".equals(flag)){
				totalnumber = ordersService.queryCount(projectid, ordersid, null, foremanname, flag);
				ControllerUtil.setPageParam(totalnumber, totalpage, mav, request);
				queryALLOrders = ordersService.queryOrders(projectid,ordersid,null,foremanname,flag,Integer.valueOf(String.valueOf(mav.getModel().get("currentpage"))));
				mav.setViewName("wyl/foremanIndex");
			}
		}
		mav.addObject("queryAllOrders",queryALLOrders);
		mav.addObject("likeprojectid",projectid);
		mav.addObject("likeordersid",ordersid);
		mav.addObject("foremanname",foremanname);
		if(flag == null)
			flag = "5";
		mav.addObject("flag",flag);
		return mav;
	}
	
	/**
	 * 转向修改订单页面
	 * @param request
	 * @return 修改订单详情页面
	 */
	@RequestMapping(value="/toUpdateOrders")
	public ModelAndView toUpdateOrders(HttpServletRequest request){
		String ordersid= request.getParameter("ordersid");
		String id = SessionUitl.getId(request);
		Map<String, Object> param = new HashMap<>();
		param.put("id", id);
		List<Map<String, Object>> list = userLoginService.queryUser(param);
		String phone = list.get(0).get("phone").toString();
		ModelAndView mav = new ModelAndView("/wyl/ordersUpdate");
		Map<String, Object> dataMap = ordersService.queryOrderById(ordersid);
		dataMap.put("phone", phone);
        if(dataMap!=null){
            mav.addObject("dataMap",dataMap);
        }
        return mav;
	}
	
	 /**
	  * 修改订单（农户）
	  * @param request
	  * @param response
	  */
	@RequestMapping(value="/updateOrders")
	public void updateOrders(HttpServletRequest request,HttpServletResponse response){
		String message = null;
		String ordersid = request.getParameter("ordersid");
		String start =request.getParameter("starttime");
		String end =request.getParameter("endtime");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		Date starttime = null;
		Date endtime = null;
		try {
			starttime = format.parse(start);
			endtime = format.parse(end);
		} catch (ParseException e) {
			logger.error("OrdersController.updateOrders",e);
		}
		int number = Integer.valueOf(request.getParameter("number"));
		double price = Double.valueOf(request.getParameter("price"));
		boolean flag = ordersService.updateOrders(ordersid, starttime, endtime, number, price);
		if(flag){
			message = "{\"message\":\"1\"}";
		}else{
			message = "{\"message\":\"2\"}";
		}
		ControllerUtil.writeAjaxReturn(response, message);
	}
	
	/**
	 * 农户删除订单
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/deleteOrders")
	public void deleteOrders(HttpServletRequest request,HttpServletResponse response){
		String message = null;
		ModelAndView mav = new ModelAndView("redirect:queryOrders.do");
		String ordersid = request.getParameter("ordersid");
		boolean flag = ordersService.deleteOrders(ordersid);
		if(flag){
			message = "{\"message\":\"1\"}";
		}else{
			message = "{\"message\":\"2\"}";
		}
		ControllerUtil.writeAjaxReturn(response, message);
	}
	
	/**
	 * 判断订单状态
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/checkFlag")
	public void checkFlag(HttpServletRequest request,HttpServletResponse response){
		String ordersid = request.getParameter("ordersid");
		String message = null;
		Map<String, Object> map = ordersService.queryOrderById(ordersid);
		if("0".equals(map.get("flag"))){
			message = "{\"message\":\"0\"}";
		}else if("1".equals(map.get("flag"))){
			message = "{\"message\":\"1\"}";
		}else if("2".equals(map.get("flag"))){
			message = "{\"message\":\"2\"}";
		}else if("3".equals(map.get("flag"))){
			message = "{\"message\":\"3\"}";
		}else {
			message = "{\"message\":\"4\"}";
		}
		ControllerUtil.writeAjaxReturn(response, message);
	}
	
	 /**
	  * 管理员审核订单
	  * @param request
	  * @param response
	  */
	@RequestMapping(value="/checkOrders")
	public void checkOrders(HttpServletRequest request,HttpServletResponse response){
		String message = null;
		String ordersid = request.getParameter("ordersid");
		String managename = SessionUitl.getName(request);
		String flag = "1";
		boolean updateOrdersByManage = ordersService.updateOrdersByManage(ordersid, managename, flag);
		if(updateOrdersByManage){
			message = "{\"message\":\"1\"}";
		}else{
			message = "{\"message\":\"2\"}";
		}
		ControllerUtil.writeAjaxReturn(response, message);
	}
	
	/**
	 * 工头接单
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/acceptOrders")
	public void acceptOrders(HttpServletRequest request,HttpServletResponse response){
		String message = null;
		String ordersid = request.getParameter("ordersid");
		String foremanname = SessionUitl.getName(request);
		String flag = "2";
		boolean updateOrdersByManage = ordersService.updateOrders(ordersid, foremanname, flag);
		if(updateOrdersByManage){
			message = "{\"message\":\"1\"}";
		}else{
			message = "{\"message\":\"2\"}";
		}
		ControllerUtil.writeAjaxReturn(response, message);
		
	}
	
	/**
	 * 工头（条件）查询自己名下所有的订单
	 * @param request
	 * @return
	 */
	@RequestMapping("/queryOrdersByForeman")
	public ModelAndView queryOrdersByForeman(HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		String totalpage = request.getParameter("totalpage");
		int totalnumber;
		String projectid = request.getParameter("projectid");
		String ordersid = request.getParameter("ordersid");
		String flag = request.getParameter("flag");
		List<Map<String, Object>> queryAllOrders = new ArrayList<>();
		String foremanname = SessionUitl.getName(request);
		if(flag == null)
			totalnumber = ordersService.queryCount(projectid, ordersid, null, foremanname, flag);
		else {
			if("0".equals(flag)){
				if(StringUtil.isEmpty(projectid)&&StringUtil.isEmpty(ordersid)){
					totalnumber = ordersService.queryCountByForemanname(foremanname);
				}else{
					totalnumber = ordersService.queryCount(projectid, ordersid, null, foremanname, null);
				}
			}else{
			totalnumber = ordersService.queryCount(projectid, ordersid, null, foremanname, flag);
			}
		}
		ControllerUtil.setPageParam(totalnumber, totalpage, mav, request);
		if("0".equals(flag)) {
			 queryAllOrders = ordersService.queryOrders(projectid,ordersid,null,foremanname,null,Integer.valueOf(String.valueOf(mav.getModel().get("currentpage"))));
		} else {
			 queryAllOrders = ordersService.queryOrders(projectid,ordersid,null,foremanname,flag,Integer.valueOf(String.valueOf(mav.getModel().get("currentpage"))));
		}
		mav.setViewName("wyl/foremanIndex");
		mav.addObject("queryAllOrders",queryAllOrders);
		mav.addObject("likeprojectid",projectid);
		mav.addObject("likeordersid",ordersid);
		mav.addObject("foremanname",foremanname);
		if(flag == null)
			flag = "0";
		mav.addObject("flag",flag);
		return mav;
	}
	
	/**
	 * 工头结束订单
	 * @param request
	 * @return
	 */
	@RequestMapping("/closeOrders")
	public void closeOrders(HttpServletRequest request,HttpServletResponse response){
		String message = null;
		String ordersid = request.getParameter("ordersid");
		String foremanname = SessionUitl.getName(request);
		String flag = "3";
		boolean updateOrders = ordersService.updateOrders(ordersid, foremanname, flag);
		if(updateOrders){
			message = "{\"message\":\"1\"}";
		}else{
			message = "{\"message\":\"2\"}";
		}
		ControllerUtil.writeAjaxReturn(response, message);
			
	}
	
	
	
}
