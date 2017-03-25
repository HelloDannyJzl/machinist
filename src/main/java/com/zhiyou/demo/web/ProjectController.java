package com.zhiyou.demo.web;

import java.util.ArrayList;
import java.util.Calendar;
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

import com.zhiyou.demo.service.ProjectService;
import com.zhiyou.demo.until.ControllerUtil;
import com.zhiyou.demo.until.SessionUitl;

import jodd.util.StringUtil;

/**
 * 项目表Controller类
 * @author jzl
 */
@Controller
@RequestMapping(value = "/project")
public class ProjectController {
	
	private Logger logger = LogManager.getLogger("ProjectController");

	@Resource
	private ProjectService projectService;
	
	/** 
	 *  分页查询项目
	 * （根据角色不同：为管理员展示所有项目、为农户展示可用项目）
	 * @param request HttpServletRequest
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/queryAllProject.do")
	public ModelAndView queryAllProject(HttpServletRequest request){
		String totalpage = request.getParameter("totalpage");
		String nextpage = request.getParameter("nextpage");
		String flag = request.getParameter("flag");
		String proname = request.getParameter("proname");
		String pronumber = request.getParameter("pronumber");
		String rolename = SessionUitl.getRoleName(request);
		int totalnumber = 0;
		if("4".equals(flag)){//flag ==4 对应所有项目
			flag =null;
		}
		ModelAndView mav = new ModelAndView();
		if(StringUtil.isEmpty(nextpage)){
			nextpage = "1";
		}
		List<Map<String, Object>> datalist = new ArrayList<>();
		if("管理员".equals(rolename)){
			datalist = projectService.queryAllProject(Integer.valueOf(nextpage),flag,proname,pronumber);
			mav.setViewName("/pro/manage/proIndex");//管理员对应的项目管理主页
		}else if("农民".equals(rolename)){
			flag = "1";
			datalist = projectService.queryAllProject(Integer.valueOf(nextpage),flag,proname,pronumber);
			mav.setViewName("/wyl/farmerIndex");//农户对应的查看项目主页
		}
		totalnumber = projectService.queryAllCount(flag,proname,pronumber);
		//获得当前页数和总页数，在mav中添加将要显示的分页编号
		ControllerUtil.setPageParam(totalnumber, totalpage, mav, request);
		mav.addObject("datalist",datalist);
		mav.addObject("likeproname",proname);
		mav.addObject("likepronumber",pronumber);
		if(flag == null){
			flag = "4";
		}
		mav.addObject("flag",flag);
		return mav;
	}
	
	/**
	 * 专项添加项目
	 * @param request HttpServletRequest
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/toAddProject")
    public ModelAndView toAddProject(HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		String pronumber = getPronumber();
        if( StringUtil.isEmpty(pronumber) ){
        	mav.setViewName("/pro/manage/loginError");
    		mav.addObject("message","项目数量已达极限，不可再添加");
    		return mav;
        }else{
        	mav.setViewName("/pro/manage/proAdd");
        	mav.addObject("pronumber",pronumber);
        	return mav;
        }
    }
	
	/**
	 * 添加项目
	 * @param request HttpServletRequest
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/addProject")
	public ModelAndView addProject(HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		String pronumber = String.valueOf(request.getParameter("pronumber"));
		String proname = String.valueOf(request.getParameter("proname"));
		String proSite0 = String.valueOf(request.getParameter("selProvince"));
		String proSite1 = String.valueOf(request.getParameter("selCity"));
		String proSite2 = String.valueOf(request.getParameter("proSite"));
		if(proSite2.equals("--地点详情--")){
			proSite2="";
		}
		String proaddress = proSite0+"省 "+proSite1+"市 "+proSite2;
		if(StringUtil.isEmpty(pronumber)||StringUtil.isEmpty(proname)||StringUtil.isEmpty(proaddress)){
			mav.setViewName("/pro/manage/loginError");
    		mav.addObject("message","项目添加失败");
    		return mav;  
		}
		if(StringUtil.isEmpty(pronumber)||StringUtil.isEmpty(proname)||StringUtil.isEmpty(proSite0)){
			mav.setViewName("/pro/manage/loginError");
			mav.addObject("message","项目添加失败");
			return mav;
		}
		
		boolean flag = projectService.addProject(pronumber, proname, proaddress);
		if(flag){
			mav.setViewName("redirect:queryAllProject.do");
    	}else{
    		mav.setViewName("/pro/manage/loginError");
    		mav.addObject("message","项目添加失败");
    	}
    	return mav;
	}
	
	
	
	
	/**
	 * 获取最新项目编号
	 * @return 项目编号
	 */
	 private String getPronumber() {
		 int num = projectService.queryAllCount(null,null,null);
		 String pronumber=null;
		 if(num>=99){
			 return null;
		 }
		 if(num>=9){
			 pronumber = String.valueOf(Calendar.getInstance().get(Calendar.YEAR)) + String.valueOf(++num);
		 }
		 if(num<9){
			 pronumber = String.valueOf(Calendar.getInstance().get(Calendar.YEAR)) + "0" + String.valueOf(++num);
		 }
		return pronumber;
	}

	/**
     * 转向修改项目
     * @param request HttpServletRequest
     * @return ModelAndView
     */
    @RequestMapping(value = "/toUpdateProject")
    public ModelAndView toUpdateUser(HttpServletRequest request){
        String id = request.getParameter("id");
        Map<String,Object> projectData = projectService.queryProForNameId(id);
        String proaddress = String.valueOf(projectData.get("proaddress"));
        String [] arr1 = proaddress.split("省 ");
		String [] arr2 = arr1[1].split("市 ");
		String proSite0 = arr1[0];
		String proSite1 = arr2[0];
		String proSite2 = null;
		if(arr2.length==1){
			proSite2 = "--地点详情--";
		}else{
			proSite2 = arr2[1];
		}
        ModelAndView mav = new ModelAndView("/pro/manage/proUpdate");
        if(projectData.size()>0)
            mav.addObject("projectData",projectData);
        	mav.addObject("proSite0",proSite0);
        	mav.addObject("proSite1",proSite1);
        	mav.addObject("proSite2",proSite2);
        return mav;
    }
   
    /**
     * 修改项目
     * @param request HttpServletRequest
     * @return ModelAndView
     */
    @RequestMapping(value = "/updateProject")
    public ModelAndView updateUser(HttpServletRequest request){
        ModelAndView mav = new ModelAndView("redirect:queryAllProject.do");
        String id = request.getParameter("id");
        String flag = request.getParameter("flag");
		String proname = String.valueOf(request.getParameter("proname"));
		String proSite0 = String.valueOf(request.getParameter("selProvince"));
		String proSite1 = String.valueOf(request.getParameter("selCity"));
		String proSite2 = String.valueOf(request.getParameter("proSite"));
		if(proSite2.equals("--地点详情--")){
			proSite2="";
		}
		String proaddress = proSite0+"省 "+proSite1+"市 "+proSite2;
        if(StringUtil.isEmpty(id)||StringUtil.isEmpty(proname)||StringUtil.isEmpty(proSite0)){
        	mav.setViewName("/pro/manage/loginError");
    		mav.addObject("message","项目修改失败");
    		return mav;
        }
        boolean	flagTmp = projectService.updateProject(id, flag, proname,proaddress);
        if(!flagTmp){
        	mav.setViewName("/pro/manage/loginError");
    		mav.addObject("message","项目修改失败");
    }
        return mav;
    }
	
    /**
     * 修改项目标识位Ajax
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     */
    @RequestMapping(value = "/ajaxStartEndPro")
    public void ajaxStartEndPro(HttpServletRequest request,HttpServletResponse response){
    	String id = request.getParameter("id");
    	String flag = request.getParameter("flag");
    	String message = null;
    	if(StringUtil.isEmpty(id)||StringUtil.isEmpty(flag)){
    		message = "{\"message\":\"2\"}";
    		ControllerUtil.writeAjaxReturn(response, message);
    		return;
    	}
    	boolean	flagTmp = projectService.updateProject(id, flag, null,null);
    	
    	if(flagTmp){
			message = "{\"message\":\"1\"}";
		}else{
			message = "{\"message\":\"2\"}";
		}
    	ControllerUtil.writeAjaxReturn(response, message);
    }
    
    
    /**
     * 查询可用项目
     * @param request HttpServletRequest
     * @return ModelAndView
     */
	@RequestMapping(value = "/queryStartPro")
	public ModelAndView queryStartPro(HttpServletRequest request){
		String totalpage = request.getParameter("totalpage");
		String proname = request.getParameter("proname");
		String pronumber = request.getParameter("pronumber");
		String flag = "1";//查询已开启的项目
		int totalnumber = 0;
		if(StringUtil.isEmpty(totalpage)){
			totalpage = "1";
		}
		totalnumber = projectService.queryAllCount(flag,proname,pronumber);
		ModelAndView mav = new ModelAndView();
		ControllerUtil.setPageParam(totalnumber, totalpage, mav, request);//在mav中添加将要显示的分页编号
		List<Map<String, Object>>  datalist = projectService.queryAllProject(Integer.valueOf(totalpage), flag,proname,pronumber);
		mav.setViewName("/pro/manage/proIndex");//农户对应的项目列表页面
		mav.addObject("datalist",datalist);
		return mav;
	}
	
	
}
