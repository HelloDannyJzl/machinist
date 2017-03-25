package com.zhiyou.demo.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import com.zhiyou.demo.BaseJunit4Test;
import com.zhiyou.demo.until.PageUtil;

/**
 * 项目表dao层测试类
 * @author jzl
 */
public class ProjectDaoTest extends BaseJunit4Test{

	@Resource
	private ProjectDao projectDao;
	
	private Logger logger = LogManager.getLogger("ProjectDaoTest");

	@Test
	public void addProjectTest(){
		Map<String, Object> param = new HashMap<>();
		param.put("id", UUID.randomUUID().toString());
		param.put("pronumber", "201612072014");
		param.put("proname", "河南王大锤百亩棉花秋收项目");
		param.put("createtime", new Date(System.currentTimeMillis()));
		param.put("proaddress", "河南省郑州市智游基地");
		param.put("flag", "0");
		try {
			projectDao.addProject(param);
		} catch (RuntimeException e) {
			logger.error("ProjectDaoTest.addProjectTest" ,e);
			e.printStackTrace();
		}
	}
	
	@Test
	public void updateProjectTest(){
		Map<String, Object> param = new HashMap<>();
		param.put("id", "81b548c1-bebc-4f74-b188-032ab7560b5f");
		param.put("updatetime", new Date(System.currentTimeMillis()));
		param.put("starttime", new Date(System.currentTimeMillis()));
		param.put("flag", "1");
		try {
			projectDao.updateProject(param);
		} catch (RuntimeException e) {
			logger.error("ProjectDaoTest.updateProjectTest" ,e);
			e.printStackTrace();
		}
	}
	
	@Test
	public void queryProjectForProidTest(){
		Map<String, Object> param = new HashMap<>();
		param.put("id", "81b548c1-bebc-4f74-b188-032ab7560b5f");
		Map<String, Object> map = projectDao.queryProjectFornameid(param );
		System.out.println(map);
		Assert.assertTrue(map!=null&&map.size()>0);
	}
	
	@Test
	public void queryAllProjectTest(){
		Map<String, Object> map = PageUtil.getStartEndMap(1); 
		map.put("flag", null);
		map.put("likepronumber", "201603");
		List<Map<String, Object>> list = projectDao.queryAllProject(map);
		System.out.println(list);
		Assert.assertTrue(list!=null&&list.size()>0);
	}
	
	
	@Test
	public void queryAllCountTest(){
		Map<String, Object> map = new HashMap<>();
		map.put("flag", "1");
		int cnt = projectDao.queryAllCount(map);
		System.out.println(cnt);
		Assert.assertTrue(cnt>0);
	}
	
	
	
	
}
