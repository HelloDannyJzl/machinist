package com.zhiyou.demo.until;

import javax.servlet.http.HttpServletRequest;

/**
 * 通用session工具类
 * @author Administrator
 *
 */
public class SessionUitl {
	

	/**
	 * 将用户id存入session
	 * @param request HttpServletRequest
	 * @param id 用户id
	 */
	public static void setId(HttpServletRequest request,String id){
		request.getSession().setAttribute("id", id);
	}
	
	/**
	 * 将用户名字存入session
	 * @param request HttpServletRequest
	 * @param name 用户名字
	 */
	public static void setName(HttpServletRequest request,String name){
		request.getSession().setAttribute("name", name);
	}
	
	/**
	 * 将用户的角色名称存入session
	 * @param request HttpServletRequest
	 * @param roleName 用户的角色名称
	 */
	public static void setRoleName(HttpServletRequest request,String roleName){
		request.getSession().setAttribute("rolename", roleName);
	}
	
	/**
	 * 从Session中获取用户id
	 * @param request HttpServletRequest
	 * @return 用户id
	 */
	public static String getId(HttpServletRequest request){
		Object object = request.getSession().getAttribute("id");
		if(object != null){
			return String.valueOf(object);
		}else{
			return null;
		}
	}
	
	/**
	 * 从Session中获取用户名
	 * @param request
	 * @return 用户名字 
	 */
	public static String getName(HttpServletRequest request){
		Object object = request.getSession().getAttribute("name");
		if(object != null){
			return String.valueOf(object);
		}else{
			return null;
		}
	}
	
	/**
	 * 从Session 中获取角色名称
	 * @param request HttpServletRequest
	 * @return 用户的角色名称
	 */
	public static String getRoleName(HttpServletRequest request){
		Object object = request.getSession().getAttribute("rolename");
		if(object != null){
			return String.valueOf(object);
		}else{
			return null;
		}
	}
}
