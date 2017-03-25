package com.zhiyou.demo.dao.peoplemanagement;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import com.zhiyou.demo.BaseJunit4Test;
import com.zhiyou.demo.dao.peoplemanagement.userdao.UserLogin;

/**
 * 用户注册测试案例
 * 
 * @author Administrator
 *
 */
public class UserDaoTest extends BaseJunit4Test {

	private static Logger logger = LogManager.getLogger("BaseJunit4Test");

	@Resource
	private UserLogin userLogin;

	//@Test
	public void addUserTest() {
		Map<String, Object> map = new HashMap<>();
		map.put("id", UUID.randomUUID().toString());
		map.put("roleid", "1e85fb18-0d29-4360-92ff-447ede747386");
		map.put("name", "王二朱");
		map.put("phone", "15738969315");
		map.put("flag", "1");
		map.put("age", 45);
		map.put("gender", "男");
		map.put("adress", "安徽西风市民权县");
		map.put("idcard", "422421199511047268");
		map.put("password", "000000");
		map.put("createtime", new Date(System.currentTimeMillis()));
		map.put("updatetime", new Date(System.currentTimeMillis()));
		map.put("account", "223456744");
		map.put("plantadress", "河北省西风市民权县");
		map.put("plantnumber", 20);
		map.put("plantbreed", "棉花");
		map.put("jobnumber", "10002");
		map.put("reason", "信息有误");
		map.put("foremanid", UUID.randomUUID().toString());
		userLogin.addUser(map);
	}
	
	
	//@Test
	public void queryUser(){
		Map<String,Object> map = new HashMap<>();
		map.put("jobnumber", "10002");
		List<Map<String,Object>> list = userLogin.queryUser(map);
		System.out.println(list.size());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
