package com.zhiyou.demo.web.peoplemanagement;

import java.util.ArrayList;
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

import com.zhiyou.demo.service.peoplemanagement.userservice.impl.UserLoginServiceImpl;
import com.zhiyou.demo.until.ControllerUtil;
import com.zhiyou.demo.until.SessionUitl;

import jodd.util.StringUtil;

/**
 * 用户控制层
 * @author Administrator
 */
@Controller
@RequestMapping(value="user")
public class UserController {
	
	private static Logger logger = LogManager.getLogger("UserController");
	
	@Resource
	private UserLoginServiceImpl userLoginServiceImpl;
	
	/**
	 * 农户注册
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 */
	@RequestMapping(value="/addFarmer")
	private void addFarmer(HttpServletRequest request,HttpServletResponse response){
		Map<String,String> map = getRequest(request);
		String plantbreed = request.getParameter("plantbreed");
		String plantnumber = request.getParameter("plantnumber");
		String plantadress = request.getParameter("plantadress");
		String message;
		if(StringUtil.isEmpty(plantbreed)||StringUtil.isEmpty(plantnumber)||StringUtil.isEmpty(plantadress)){
			logger.error("UserController.addFarmer:未获取农户填写信息");
			return;
		}
		boolean flag = userLoginServiceImpl.addFarmer(map.get("name"), map.get("phone"), Integer.valueOf(String.valueOf(map.get("age"))), map.get("gender"), map.get("adress"), map.get("idcard"), plantadress, Integer.valueOf(plantnumber), plantbreed);
		if(flag){
			message = "{\"message\":\"1\"}";
		}else{
			logger.error("UserController.addFarmer:添加农户注册信息失败");
			return;
		}
		ControllerUtil.writeAjaxReturn(response, message);
	}
	
	/**
	 * 工头注册
	 * @param request HttpServletRequest
	 * @param response HttpServletRsponse
	 */
	@RequestMapping(value="/addForman")
	private void addForman(HttpServletRequest request,HttpServletResponse response){
		Map<String,String> map = getRequest(request);
		String account = request.getParameter("account");
		String message;
		if(StringUtil.isEmpty(account)){
			logger.error("UserController.addForman:未获取工头填写信息");
			return;
		}
		boolean flag = userLoginServiceImpl.addForman(map.get("name"), map.get("phone"), Integer.valueOf(String.valueOf(map.get("age"))), map.get("gender"), map.get("adress"), map.get("idcard"));
		if(flag){
			message = "{\"message\":\"1\"}";
		}else{
			logger.error("UserController.addFarmer:添加注册信息失败");
			return;
		}
		ControllerUtil.writeAjaxReturn(response, message);
	}
	
	/**
	 * 工人注册
	 * @param request HttpServletRequest
	 * @param response HttpServletRsponse
	 */
	@RequestMapping(value="/addWorker")
	private void addWorker(HttpServletRequest request,HttpServletResponse response){
		Map<String,String> map = getRequest(request);
		String account = request.getParameter("account");
		String message;
		if(StringUtil.isEmpty(account)){
			logger.error("UserController.addForman:未获取工头填写信息");
			return;
		}
		boolean flag = userLoginServiceImpl.addWorker(map.get("name"), map.get("phone"), Integer.valueOf(String.valueOf(map.get("age"))), map.get("gender"), map.get("adress"), map.get("idcard"), null);
		if(flag){
			message = "{\"message\":\"1\"}";
		}else{
			logger.error("UserController.addFarmer:添加注册信息失败");
			return;
		}
		ControllerUtil.writeAjaxReturn(response, message);
	}

	
	
	/**
	 * 获取农户、工头、工人共同信息
	 * @param request HttpservletRequet
	 * @return Map
	 */
	private Map<String,String> getRequest(HttpServletRequest request){
		String name = request.getParameter("name");
		String idcard = request.getParameter("idcard");
		String phone = request.getParameter("phone");
		String adress = request.getParameter("adress");
		String age = request.getParameter("age");
		String gender = request.getParameter("gender");
		if(StringUtil.isEmpty(name)||StringUtil.isEmpty(idcard)||StringUtil.isEmpty(phone)||StringUtil.isEmpty(adress)||StringUtil.isEmpty(age)||StringUtil.isEmpty(gender)){
			logger.error("UserController.addFarmer:未获取农户填写信息");
			return null;
		}
		Map<String,String> map = new HashMap<>();
		map.put("name", name);
		map.put("idcard", idcard);
		map.put("phone", phone);
		map.put("adress", adress);
		map.put("age", age);
		map.put("gender", gender);
		return map;
	}
	
	/**
	 * 检查用户账号（密码账号不匹配、可登陆、审核中）
	 * @param request HttpServletRequest
	 * @param response HtttpServletResponse
	 */
	@RequestMapping(value="/checkLogin")
	public void checkLogin(HttpServletRequest request,HttpServletResponse response){
		String userName = request.getParameter("userName");
		String passWord = request.getParameter("passWord");
		String message = null;
		if(StringUtil.isEmpty(userName)&&StringUtil.isEmpty(passWord)){
			logger.error("UserController.checkLogin:未获取用户账号、密码");
			return;
		}
		List<Map<String,Object>> list = checkNumber(userName,passWord);
		if(list.size() < 0){
			message = "{\"message\":\"1\"}";
		}else{
			if("1".equals(list.get(0).get("flag"))){
				if("管理员".equals(list.get(0).get("rolename"))){
					if(userName.equals(list.get(0).get("jobnumber"))){
						message = "{\"message\":\"2\"}";
					}else{
						message = "{\"message\":\"1\"}";
					}
				}else{
					message = "{\"message\":\"2\"}";
				}
			}else if("2".equals(list.get(0).get("flag"))){
				message = "{\"message\":\"3\"}";
			}
		}
		ControllerUtil.writeAjaxReturn(response, message);
	}
	
	/**
	 * 根据用户角色跳转到页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/logins")
	public ModelAndView userLogin(HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		String userName = request.getParameter("username");
		String passWord = request.getParameter("password");
		if(StringUtil.isEmpty(userName)||StringUtil.isEmpty(passWord)){
			logger.error("UserController.addFarmer:未获取用户账号、密码");
			return null;
		}
		List<Map<String,Object>> list = checkNumber(userName,passWord);
		if("农民".equals(list.get(0).get("rolename"))){
			setSession(request,list);
			mav.setViewName("user/farmerLogin");
		}else if("工头".equals(list.get(0).get("rolename"))){
			setSession(request,list);
			mav.setViewName("user/foremanLogin");
		}else if("工人".equals(list.get(0).get("rolename"))){
			setSession(request,list);
			mav.setViewName("user/workerLogin");
		}else if("管理员".equals(list.get(0).get("rolename"))){
			setSession(request,list); 
			mav.setViewName("user/manageMain");
		}
		return mav;
	}
	
	/**
	 * 将用户id、name、rolename写入session
	 * @param request HttpServletRequet
	 * @param list 用户信息
	 */
	private void setSession(HttpServletRequest request,List<Map<String,Object>> list){
		SessionUitl.setId(request, String.valueOf(list.get(0).get("id")));
		SessionUitl.setName(request, String.valueOf(list.get(0).get("name")));
		SessionUitl.setRoleName(request, String.valueOf(list.get(0).get("rolename")));
	}
	
	/**
	 * 检查登录账号是手机号还是员工编号
	 * @param userName 用户账号
	 * @param passWord 用户密码
	 * @param message 返回信息
	 * @return List
	 */
	private List<Map<String,Object>> checkNumber(String userName,String passWord){
		List<Map<String,Object>> list = new ArrayList<>();
		Map<String,Object> map = new HashMap<>();
		String[] number = userName.split("");
		if(number.length == 11){
			map.put("phone", userName);
			map.put("password", passWord);
			list = userLoginServiceImpl.queryUser(map);
		}else if(number.length == 6){
			map.put("jobnumber", userName);
			map.put("password", passWord);
			list = userLoginServiceImpl.queryUser(map);
		}
		return list;
	}
}
