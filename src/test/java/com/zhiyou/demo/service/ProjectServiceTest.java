package com.zhiyou.demo.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import com.zhiyou.demo.BaseJunit4Test;

/**
 * 项目表Service层测试类
 * @author jzl
 */
public class ProjectServiceTest extends BaseJunit4Test{

	private Logger logger = LogManager.getLogger("ProjectServiceImpl");
	
	@Resource
	private ProjectService projectService;
	
	@Test
	public void addProjectTest(){
		boolean flag = projectService.addProject("20161213", "收麦子", "开封府");
		Assert.assertTrue(flag);
	}
	
	@Test
	public void updateProjectTest(){
		boolean flag = projectService.updateProject("2e7f1528-010b-4169-8581-180f990ef7c3",
				"1", "hehe","heihie");
		Assert.assertTrue(flag);
	}
	
	@Test
	public void queryAllProjectTest(){
		List<Map<String, Object>> list = projectService.queryAllProject(1, null,null,null);
		System.out.println(list);
		Assert.assertTrue(list!=null&&list.size()>0);
	}
	
	@Test
	public void queryAllCountTest(){
		int cnt = projectService.queryAllCount(null,null,null);
		System.out.println(cnt);
		Assert.assertTrue(cnt>0);
	}
	
	@Test
	public void queryProForNameIdTest(){
		Map<String, Object> map = projectService.queryProForNameId("2e7f1528-010b-4169-8581-180f990ef7c3");
		System.out.println(map);
		Assert.assertTrue(map!=null&&map.size()>0);
	}
	
	
}
