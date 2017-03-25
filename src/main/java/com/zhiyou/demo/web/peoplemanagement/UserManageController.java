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
 * 用户管理控制层
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/manage")
public class UserManageController {
	
	private static Logger logger = LogManager.getLogger("UserManageController");
	
	@Resource
	private UserLoginServiceImpl userLoginServiceImpl;
	
	@RequestMapping(value=("/queryAllUser"))
	public ModelAndView queryAllUser(HttpServletRequest request){
		ModelAndView mav = new ModelAndView("/usermanagement/usermanage");
		String totalpage = request.getParameter("totalpage");
		Map<String,Object> map = new HashMap<>();
		map.put("flag", 1);
		int totalnumber = 0;
		if(StringUtil.isEmpty(totalpage)){
			totalnumber =  userLoginServiceImpl.queryJobNumber(map);
		}
		ControllerUtil.setPageParam(totalnumber, totalpage, mav, request);
		List<Map<String,Object>> userList = userLoginServiceImpl.queryAllUser(Integer.valueOf(String.valueOf(mav.getModel().get("currentpage"))));
		mav.addObject("userlist", userList);
		return mav;
	}
	
	/**
	 * 查询所有待审审核的用户
	 * @param request HttpServletRequest
	 * @return 视图
	 */
	@RequestMapping(value="queryAllLoginUser")
	public ModelAndView queryAllLoginUser(HttpServletRequest request){
		ModelAndView mav = new ModelAndView("/usermanagement/checkUser");
		Map<String,Object> map = new HashMap<>();
		String flag = "3";
		map.put("flag",flag);
		List<Map<String,Object>> list = userLoginServiceImpl.queryUser(map);
		mav.addObject("userlist", list);
		return mav;
	}
	                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          
	/**
	 * 查询所有工人、工头、农民
	 * @param request HttpServletRequest
	 * @return
	 */
	@RequestMapping(value="/queryFFW")
	public ModelAndView queryFFW(HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		String roleName = request.getParameter("message");
		String flag = request.getParameter("flag");
		String locate = request.getParameter("locate");
		Map<String,Object> map = new HashMap<>();
		List<Map<String,Object>> list = checkRole(map,roleName,flag);
		if(StringUtil.isEmpty(locate)){
			mav.setViewName("/usermanagement/usermanage");
		}else{
			mav.setViewName("/usermanagement/checkUser");
		}
		mav.addObject("userlist", list);
		return mav;
	}
	
	/**
	 * 检查用户所属角色、用户状态
	 * @param list List
	 * @param map Map
	 * @param roleName 角色名
	 * @param flag 用户状态
	 */
	private List<Map<String,Object>> checkRole(Map<String,Object> map,String roleName,String flag){
		List<Map<String,Object>> list = new ArrayList<>();
		if(roleName.equals("0")){
			String roleid="54aca416-b816-480c-a6bb-988bf5af7f9b";
			map.put("roleid", roleid);
			if(flag!=null)
				map.put("flag", flag);
			list = userLoginServiceImpl.queryUser(map);
		}else if(roleName.equals("1")){
			String roleid="9117fc8b-3a59-4f3f-8c99-0375797a7eb8";
			map.put("roleid", roleid);
			if(flag!=null)
				map.put("flag", flag);
			list = userLoginServiceImpl.queryUser(map);
		}else if(roleName.equals("2")){
			String roleid="1e85fb18-0d29-4360-92ff-447ede747386";
			map.put("roleid", roleid);
			if(flag!=null)
				map.put("flag", flag);
			list = userLoginServiceImpl.queryUser(map);
		}
		return list;
	}
	
	/**
	 * 根据名字查询
	 * @param request HttpServletRequest
	 * @return
	 */
	@RequestMapping(value="/queryName")
	public ModelAndView queryName(HttpServletRequest request){
		ModelAndView mav = new ModelAndView("/usermanagement/usermanage");
		String name = request.getParameter("name");
		if(StringUtil.isEmpty(name)){
			logger.error("UserManageController.queryName:获取名字失败");
			return mav;
		}
		Map<String,Object> map = new HashMap<>();
		map.put("name", name);
		List<Map<String,Object>> list = userLoginServiceImpl.queryUser(map);
		mav.addObject("userlist", list);
		return mav;
	}
	
	/**
	 * 专项修改用户信息
	 * @param request HttpServletRequest
	 * @return 修改用户信息页面
	 */
	@RequestMapping(value="/toupdateUser")
	public ModelAndView toupdateUser(HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		String id = request.getParameter("id");
		Map<String,Object> map = new HashMap<>();
		List<Map<String,Object>> userList = null;
		if(StringUtil.isEmpty(id)){
			id = SessionUitl.getId(request);
			map.put("id", id);
			userList = userLoginServiceImpl.queryUser(map);
			if("农民".equals(userList.get(0).get("rolename"))){
				mav.setViewName("user/farmerUpdateMessage");
			}else if("工头".equals(userList.get(0).get("rolename"))){
				mav.setViewName("user/formanUpdateMessage");
			}else{
				mav.setViewName("user/workerUpdateMessage");
			}
		}else{
			map.put("id", id);
			userList = userLoginServiceImpl.queryUser(map);
			if("农民".equals(userList.get(0).get("rolename"))){
				mav.setViewName("peoplemanagement/updateFarmer");
			}else if("工头".equals(userList.get(0).get("rolename"))){
				mav.setViewName("peoplemanagement/updateForman");
			}else{
				mav.setViewName("peoplemanagement/updateWorker");
			}
		}
		mav.addObject("userList", userList);
		return mav;
	}
	
	/**
	 * 修改用户信息
	 * @param request HttpServletRequest
	 * @return 主页面
	 */
	@RequestMapping(value="/updateUser")
	public ModelAndView updateUser(HttpServletRequest request){
		ModelAndView mav = new ModelAndView("redirect:queryAllUser.do");
		Map<String,Object> map = getRequest(request);
		String plantbreed = request.getParameter("plantbreed");
		String plantnumber = request.getParameter("plantnumber");
		String plantadress = request.getParameter("plantadress");
		map.put("plantbreed", plantbreed);
		map.put("plantnumber", plantnumber);
		map.put("plantadress", plantadress);
		boolean flag = userLoginServiceImpl.updateUser(map);
		if(!flag){
			logger.error("UserManageController.updateUser:修改失败");
			return mav;
		}
		return mav;
	}
	
	/**
	 * 修改以登录用户信息
	 * @param request HttpServletRequest 
	 */
	@RequestMapping(value="/updateLoginUser")
	public void updateLoginUser(HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> map = getRequest(request);
		String plantbreed = request.getParameter("plantbreed");
		String plantnumber = request.getParameter("plantnumber");
		String plantadress = request.getParameter("plantadress");
		String message = null;
		map.put("plantbreed", plantbreed);
		map.put("plantnumber", plantnumber);
		map.put("plantadress", plantadress);
		boolean flag = userLoginServiceImpl.updateUser(map);
		if(flag){
			message = "{\"message\":\"1\"}";
		}else{
			message = "{\"message\":\"2\"}";
		}
		ControllerUtil.writeAjaxReturn(response, message);
	}
	
	/**
	 * 获取农户、工头、工人共同信息
	 * @param request HttpservletRequet
	 * @return Map
	 */
	private Map<String,Object> getRequest(HttpServletRequest request){
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String idcard = request.getParameter("idcard");
		String phone = request.getParameter("phone");
		String adress = request.getParameter("adress");
		String age = request.getParameter("age");
		String password = request.getParameter("password");
		String gender = request.getParameter("gender");
		Map<String,Object> map = new HashMap<>();
		map.put("id", id);
		map.put("name", name);
		map.put("idcard", idcard);
		map.put("phone", phone);
		map.put("adress", adress);
		map.put("age", age);
		map.put("gender", gender);
		map.put("password", password);
		return map;
	}
	
	/**
	 * 删除用户
	 * @param request HttpServletRequest
	 * @return 主页面
	 */
	@RequestMapping(value="/deleteUser")
	public ModelAndView deleteUser(HttpServletRequest request){
		ModelAndView mav = new ModelAndView("redirect:queryAllUser.do");
		String id = request.getParameter("id");
		if(StringUtil.isEmpty(id)){
			logger.error("UserManageController.deleteUser:获取id为空");
			return mav;
		}
		boolean flag = userLoginServiceImpl.deleteUser(id);
		if(!flag){
			logger.error("UserManageController.deleteUser删除用户失败");
			return mav;
		}
		return mav;
	}
	
	/**
	 * 添加管理员
	 */
	@RequestMapping(value="/addManage")
	private void addManage(HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> map = getRequest(request);
		int number = 10000 + userLoginServiceImpl.queryJobNumber(null);
		String message;
		boolean flag = userLoginServiceImpl.addManage(String.valueOf(map.get("name")), 
				String.valueOf(map.get("phone")), 
				Integer.valueOf(String.valueOf(map.get("age"))), 
				String.valueOf(map.get("gender")), 
				String.valueOf(map.get("adress")), 
				String.valueOf(map.get("idcard")), 
				String.valueOf(number));
		if(flag){
			message = "{\"message\":\"1\"}";
		}else{
			logger.error("UserController.addFarmer:添加管理员注册信息失败");
			return;
		}
		ControllerUtil.writeAjaxReturn(response, message);
	}
	
	/**
	 * 查看用户详情
	 * @param request HttpServletRequest
	 * @return 视图
	 */
	@RequestMapping(value="/checkUser")
	public ModelAndView checkUser(HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		String id = request.getParameter("id");
		if(StringUtil.isEmpty(id)){
			logger.error("UserController.checkUser:获取用户id失败，无法审核");
			return mav;
		}
		Map<String,Object> map = new HashMap<>();
		map.put("id", id);
		List<Map<String,Object>> list = userLoginServiceImpl.queryUser(map);
		if("农民".equals(list.get(0).get("rolename"))){
			mav.setViewName("usermanagement/checkFarmer");
		}else if("工头".equals(list.get(0).get("rolename"))){
			mav.setViewName("usermanagement/checkForman");
		}else{
			mav.setViewName("usermanagement/checkWorker");
		}
		mav.addObject("userlist", list);
		return mav;
	}
	
	/**
	 * 修改用户状态，添加拒绝原因
	 * @param request HttpServletRequest
	 * @return 主页面
	 */
	@RequestMapping(value="/updateFlag")
	public void updateFlag(HttpServletRequest request,HttpServletResponse response){
		String id = request.getParameter("id");
		String reason = request.getParameter("reason");
		String message = null;
		Map<String,Object> map = new HashMap<>();
		if(StringUtil.isEmpty(id)){
			logger.error("UserController.updateFlag:获得 id失败");
			return;
		}
		if(StringUtil.isEmpty(reason)){
			map.put("id", id);
			map.put("flag", 1);
		}else{
			map.put("id", id);
			map.put("flag", 2);
			map.put("reason", reason);
		}
		boolean flag = userLoginServiceImpl.updateUser(map);
		if(flag){
			message = "{\"message\":\"1\"}";
		}else{
			message = "{\"message\":\"2\"}";
		}
		ControllerUtil.writeAjaxReturn(response, message);
	}
}
