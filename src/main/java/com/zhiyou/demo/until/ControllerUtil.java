package com.zhiyou.demo.until;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

import jodd.util.StringUtil;

/**
 * Controller工具类
 * @author Administrator
 *
 */
public class ControllerUtil {
	
	private static Logger logger = LogManager.getLogger("ControllerUtil");
	
	/**
	 * 获得当前页数和总页数
	 * @param totalnumber 总的信息数量
	 * @param totalpage 总页数
	 * @param mav 返回值
	 * @param request 封装页面请求的对象
	 */
	public static void setPageParam(int totalnumber,String totalpage,ModelAndView mav,HttpServletRequest request){
		String nextPage = request.getParameter("nextpage");
		if(StringUtil.isEmpty(nextPage)){
			nextPage = "1";
		}
		mav.addObject("currentpage", nextPage);
		if(totalnumber > 0){
			totalpage = PageUtil.getTotalPage(totalnumber);
		}
		mav.addObject("totalpage", totalpage);
	}
	
	/**
	 * 组装ajax返回值
	 * @param request HttpservletRequest 
	 * @param response HttpServletResponse
	 */
	public static void writeAjaxReturn(HttpServletResponse response,String message){
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.write(message);
		} catch (IOException e) {
			logger.error("ControllerUtil.writeAjaxReturn",e);
		}finally{
			if(out!=null){
				out.flush();
				out.close();
			}
		}
	}
}
