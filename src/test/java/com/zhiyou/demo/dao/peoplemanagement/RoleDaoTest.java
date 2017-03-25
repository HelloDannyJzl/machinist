package com.zhiyou.demo.dao.peoplemanagement;


import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.junit.Test;

import com.zhiyou.demo.BaseJunit4Test;
import com.zhiyou.demo.dao.peoplemanagement.roledao.RoleDao;

public class RoleDaoTest extends BaseJunit4Test{
	
	@Resource
	private RoleDao roleDao;
	
	
	//@Test
	public void addRoleTest(){
		Map<String,Object> map = new HashMap<>();
		map.put("id", UUID.randomUUID().toString());
		map.put("createtime", new Date(System.currentTimeMillis()));
		map.put("userid", "f5abb37f-7394-4c26-91a6-b8bae1c7095e");
		map.put("rolename", "包工头");
		roleDao.addRele(map);
	}
	
	//@Test
	public void updateRoleTest(){
		Map<String,Object> map = new HashMap<>();
		map.put("id","866a4da6-c90b-478c-98f2-868ef8c1f1a3");
		map.put("updatetime", new Date(System.currentTimeMillis()));
		map.put("rolename", "修改");
		roleDao.updateRole(map);
	}
	
	//@Test
	public void deleteRoleTest(){
		roleDao.deleteRole("e7585be7-cbd3-4541-8359-3eaf288a880d");
	}
	
	//@Test
	public void queryRoleTest(){
		Map<String,Object> map = new HashMap<>();
		map.put("rolename", "农民");
		map.put("id", null);
		map = roleDao.queryRole(map);
		System.out.println(map);
	}
	
	//@Test
	public void queryALLCountTest(){
		int number = roleDao.queryALLCount();
		System.out.println(number);
	}
	
	//@Test
	public void queryAllRoleTest1(){
		Map<String,Object> map = new HashMap<>();
		map.put("startnumber", 0);
		map.put("endnumber", 10);
		List<Map<String,Object>> list = roleDao.queryAllRole(map);
		System.out.println(list.size());
	}
	
	@Test
	public void queryAllRoleTest(){
		List<Map<String,Object>> list = roleDao.queryRole();
		System.out.println(list.size());
	}
}
