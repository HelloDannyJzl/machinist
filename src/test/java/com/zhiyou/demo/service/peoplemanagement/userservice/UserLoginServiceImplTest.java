package com.zhiyou.demo.service.peoplemanagement.userservice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import com.zhiyou.demo.BaseJunit4Test;
import com.zhiyou.demo.service.peoplemanagement.userservice.impl.UserLoginServiceImpl;

/**
 * 用户注册UesrLoginServiceImpl测试案例
 * @author Administrator
 *
 */
public class UserLoginServiceImplTest extends BaseJunit4Test{
	
	@Resource
	private UserLoginServiceImpl userLoginServiceImpl;
	
	//@Test
	public void addFarmerTest(){
		boolean flag = userLoginServiceImpl.addFarmer("张三", "18838969317", 32, "男", "河南省新乡市八里沟", "411421119941107244","河南省新乡市八里沟", 80, "苹果");
		System.out.println(flag);
	}
	
	//@Test
	public void addFormanTest(){
		boolean flag = userLoginServiceImpl.addForman("李四", "12345678911", 34, "男", "河南省新乡市长垣县", "411421199811057655");
		System.out.println(flag);
	}
	
	//@Test
	public void addWorkerTest(){
		boolean flag = userLoginServiceImpl.addWorker("王五", "12345678912", 35, "男", "河南省开封市郊区", "411421199822057866", "10003");
		System.out.println(flag);
	}
	
	@Test
	public void addManage(){
		boolean flag = userLoginServiceImpl.addManage("张三4", "18838969314", 20, "男", "河南省商丘市民权县", "411421199511047233", "062108");
		System.out.println(flag);
	}
	
	//@Test
	public void queryAllUserTest(){
		List<Map<String,Object>> list = userLoginServiceImpl.queryAllUser(1);
		System.out.println(list.size());
	}
	
	//@Test
	public void updateUserTest(){
		Map<String,Object> map = new HashMap<>();
		map.put("phone", "13462916315");
		map.put("id", "30d3f268-49ce-4523-802d-e1f57cca42ef");
		boolean flag =  userLoginServiceImpl.updateUser(map);
		System.out.println(flag);
	}

}
