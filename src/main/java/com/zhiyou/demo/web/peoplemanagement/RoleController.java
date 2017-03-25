package com.zhiyou.demo.web.peoplemanagement;

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

import com.zhiyou.demo.service.peoplemanagement.roleservice.impl.RoleServiceImpl;
import com.zhiyou.demo.until.ControllerUtil;

import jodd.util.StringUtil;

/**
 * 角色管理控制层
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/role")
public class RoleController {
		
	private static Logger logger = LogManager.getLogger("RoleController");
	
	@Resource
	private RoleServiceImpl roleServiceImpl;
	
	/**
	 * 查询所有角色信息展示在角色主页上
	 * @param request HttpServletRequest
	 * @return ModelAndView
	 */
	@RequestMapping(value="/queryRole")
	public ModelAndView queryRole(HttpServletRequest request){
		ModelAndView mav = new ModelAndView("/peoplemanagement/role");
		String totalpage = request.getParameter("totalpage");
		int totalnumber = 0;
		if(StringUtil.isEmpty(totalpage)){
			totalnumber = roleServiceImpl.queryAllCount();
		}
		ControllerUtil.setPageParam(totalnumber, totalpage, mav, request);
		List<Map<String,Object>> rolelist = roleServiceImpl.queryAllRole(Integer.valueOf(String.valueOf(mav.getModel().get("currentpage"))));
		mav.addObject("rolelist", rolelist);
		return mav;
	}
	
	/**
	 * 添加角色
	 * @param request HttpServletRequst
	 * @return ModelAndView
	 */
	@RequestMapping(value="/addRole")
	public ModelAndView addRole(HttpServletRequest request){
		ModelAndView mav = new ModelAndView("redirect:queryRole.do");
		String roleName = request.getParameter("roleName");
		if(StringUtil.isEmpty(roleName)){
			logger.error("RoleController.addRole:从页面获取角色名称失败！");
			return mav;
		}
		boolean flag = roleServiceImpl.addRole(roleName, null);
		if(!flag){
			logger.error("RoleController.addRole:新增角色失败！");
			return mav;
		}
		return mav;
	}
	
	/**
	 * 转向修改角色信息
	 * @param request HttpServletRequest
	 * @return 修改页面
	 */
	@RequestMapping(value="/toUpdateRole")
	public ModelAndView toUpdateRole(HttpServletRequest request){
		ModelAndView mav = new ModelAndView("/peoplemanagement/updateRole");
		String id = request.getParameter("id");
		if(StringUtil.isEmpty(id)){
			logger.error("RoleController.toUpdateRole：获得角色id失败");
			return mav;
		}
		Map<String,Object> map = new HashMap<>();
		map.put("id", id);
		Map<String,Object> roleMap = roleServiceImpl.queryRole(id,null);
		mav.addObject("roleMap", roleMap);
		return mav;
	}
	
	/**
	 * 检测修改角色名称是否已经存在
	 * @param request HttpServletRequest
	 * @param response HttpServletResopnse
	 */
	@RequestMapping(value="/checkRole")
	public void checkRole(HttpServletRequest request,HttpServletResponse response){
		String updateName = request.getParameter("updateName");
		String message;
		Map<String,Object> map = roleServiceImpl.queryRole(null, updateName);
		if(map == null ||map.size() > 0){
			message = "{\"message\":\"1\"}";
		}else{
			message = "{\"message\":\"2\"}";
		}
		ControllerUtil.writeAjaxReturn(response, message);
	}
	
	/**
	 * 修改角色名称
	 * @param request HttpServletRequest
	 * @return ModelAndView
	 */
	@RequestMapping(value="/updateRole")
	public ModelAndView updateRole(HttpServletRequest request){
		ModelAndView mav = new ModelAndView("redirect:queryRole.do");
		String id = request.getParameter("roleid");
		String updateName = request.getParameter("updatename");
		//TODO
		boolean flag = roleServiceImpl.updateRole(updateName, id, null);
		if(!flag){
			logger.error("RoleController.updateRole:修改角色失败！");
			return mav;
		}
		return mav;
	}
	
	/**
	 * 删除角色
	 * @param request HttpServletRequest
	 * @return 删除成功后返回试图
	 */
	@RequestMapping(value="/deleteRole")
	public ModelAndView deleteRole(HttpServletRequest request){
		ModelAndView mav = new ModelAndView("redirect:queryRole.do");
		String id = request.getParameter("id");
		if(StringUtil.isEmpty(id)){
			logger.error("删除时获取角色ID失败");
			return mav;
		}
		boolean flag = roleServiceImpl.deleteRole(id);
		if(!flag){
			logger.error("删除角色失败：RoleController.deleteRole");
			return mav;
		}
		return mav;
	}
}
