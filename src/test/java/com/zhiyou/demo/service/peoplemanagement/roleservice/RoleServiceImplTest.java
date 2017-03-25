package com.zhiyou.demo.service.peoplemanagement.roleservice;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import com.zhiyou.demo.BaseJunit4Test;
import com.zhiyou.demo.service.peoplemanagement.roleservice.impl.RoleServiceImpl;

public class RoleServiceImplTest extends BaseJunit4Test{
	
	@Resource
	private RoleServiceImpl roleServiceImpl;
	
	//@Test
	public void addRole(){
		roleServiceImpl.addRole("Service", "f5abb37f-7394-4c26-91a6-b8bae1c7095e");
	}
	
	//@Test
	public void updateRoleTest(){
		roleServiceImpl.updateRole("Ser修改","fd33ca51-c8e6-4b22-a325-42536d3a9fa2","fd33ca51-c8e6-4b22-a325-42536d3a9fa2");
	}
	
	//@Test
	public void queryRoleTest(){
		Map<String,Object> map = roleServiceImpl.queryRole(null,"管理员");
		System.out.println(map);
	}
	
	//@Test
	public void deleteRoleTest(){
		roleServiceImpl.deleteRole("866a4da6-c90b-478c-98f2-868ef8c1f1a3");
	}
	
	//@Test
	public void queryAllCountTest(){
		int number = roleServiceImpl.queryAllCount();
		System.out.println(number);
	}
	
	//@Test
	public void queryAllRoleTest(){
		List<Map<String,Object>> list = roleServiceImpl.queryAllRole(1);
		System.out.println(list.size());
	}
	
	
}
