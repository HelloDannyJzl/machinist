package com.zhiyou.demo.service.peoplemanagement.userservice.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.zhiyou.demo.dao.peoplemanagement.userdao.UserLogin;
import com.zhiyou.demo.service.peoplemanagement.userservice.UserLoginService;
import com.zhiyou.demo.until.PageUtil;

import jodd.util.StringUtil;
@Service
public class UserLoginServiceImpl implements UserLoginService {
	
	private static Logger logger = LogManager.getLogger("UserLoginServiceImpl");
	
	@Resource
	private UserLogin userLogin;

	@Override
	public boolean addFarmer(String name, String phone, int age, String gender, String adress,
			String idcard, String plantadress, int plantnumber, String plantbreed) {
		if(StringUtil.isEmpty(name)||StringUtil.isEmpty(phone)||age < 0||StringUtil.isEmpty(gender)
				||StringUtil.isEmpty(adress)||StringUtil.isEmpty(idcard)||StringUtil.isEmpty(plantadress)||plantnumber < 0||StringUtil.isEmpty(plantbreed)){
			logger.error("UserLoginServiceImpl.addFarmer：获取某一字段为空");
			return false;
		}
		String roleid = "1e85fb18-0d29-4360-92ff-447ede747386";
		Map<String,Object> map = new HashMap<>();
		map = addMap(roleid,name,phone,age,gender,adress,idcard);
		map.put("plantadress", plantadress);
		map.put("plantnumber", plantnumber);
		map.put("plantbreed", plantbreed);
		userLogin.addUser(map);
		return true;
	}
	
	/**
	 * 每个角色注册相同信息
	 * @param roleid 角色id
 	 * @param name 角色名字
	 * @param phone 角色联系方式
	 * @param age 角色年龄
	 * @param gender 角色性别
	 * @param adress 家庭住址
	 * @param idcard 角色联系方式
	 * @param reason 未审核通过原因
	 * @return map
	 */
	private Map<String,Object> addMap(String roleid,String name,String phone,int age,String gender,String adress,String idcard){
		String flag = "3";
		String password = "000000";
		Map<String,Object> map = new HashMap<>();
		map.put("id", UUID.randomUUID().toString());
		map.put("roleid", roleid);
		map.put("name", name);
		map.put("phone", phone);
		map.put("flag", flag);
		map.put("age", age);
		map.put("gender", gender);
		map.put("adress", adress);
		map.put("idcard", idcard);
		map.put("password", password);
		map.put("createtime", new Date(System.currentTimeMillis()));
		return map;
	}

	@Override
	public boolean addForman(String name, String phone, int age, String gender, String adress,
			String idcard) {
		if(StringUtil.isEmpty(name)||StringUtil.isEmpty(phone)||age < 0||StringUtil.isEmpty(gender)
				||StringUtil.isEmpty(idcard)){
			logger.error("UserLoginServiceImpl.addForman：获取某一字段为空");
			return false;
		}
		String roleid = "54aca416-b816-480c-a6bb-988bf5af7f9b";
		Map<String,Object> map = new HashMap<>();
		map = addMap(roleid,name,phone,age,gender,adress,idcard);
		userLogin.addUser(map);
		return true;
	}

	@Override
	public boolean addWorker(String name, String phone, int age, String gender, String adress,
			String idcard, String foremanid) {
		if(StringUtil.isEmpty(name)||StringUtil.isEmpty(phone)||age < 0||StringUtil.isEmpty(gender)
				||StringUtil.isEmpty(idcard)){
			logger.error("UserLoginServiceImpl.addForman：获取某一字段为空");
			return false;
		}
		String roleid = "9117fc8b-3a59-4f3f-8c99-0375797a7eb8";
		Map<String,Object> map = new HashMap<>();
		map = addMap(roleid,name,phone,age,gender,adress,idcard);
		map.put("foremanid", foremanid);
		userLogin.addUser(map);
		return true;
	}

	@Override
	public int queryJobNumber(Map<String,Object> map) {
		int number = userLogin.queryJobNumber();
		return number;
	}

	@Override
	public List<Map<String, Object>> queryUser(Map<String,Object> map) {
		List<Map<String,Object>> list = userLogin.queryUser(map);
		return list;
	}

	@Override
	public boolean deleteUser(String id) {
		if(StringUtil.isEmpty(id))
			return false;
		Map<String,Object> map = new HashMap<>();
		map.put("id", id);
		userLogin.deleteUser(map);
		return true;
	}

	@Override
	public boolean updateUser(Map<String, Object> map) {
		if(map == null && map.size() < 0){
			logger.error("UserLoginServiceImpl.updateUser:更新传来的额map为空");
			return false;
		}
		userLogin.updateUser(map);
		return true;
	}

	@Override
	public List<Map<String, Object>> queryAllUser(int page) {
		if(page < 0){
			logger.error("UserLoginServiceImpl.queryAllUser:访问页数为零");
			return null;
		}
		Map<String,Object> map = PageUtil.getStartEndMap(page);
		List<Map<String,Object>> list = userLogin.queryAllUser(map);
		return list;
	}

	@Override
	public boolean addManage(String name, String phone, int age, String gender, String adress, String idcard,
			String jobnumber) {
		if(StringUtil.isEmpty(name)||StringUtil.isEmpty(phone)||age < 0||StringUtil.isEmpty(gender)
				||StringUtil.isEmpty(idcard)||StringUtil.isEmpty(jobnumber)){
			logger.error("UserLoginServiceImpl.addManage：获取某一字段为空");
			return false;
		}
		String flag = "1";
		String password = "000000";
		Map<String,Object> map = new HashMap<>();
		map.put("id", UUID.randomUUID().toString());
		map.put("roleid", "06210863-adda-4b57-90a1-37dd69120f1");
		map.put("name", name);
		map.put("phone", phone);
		map.put("flag", flag);
		map.put("age", age);
		map.put("gender", gender);
		map.put("adress", adress);
		map.put("idcard", idcard);
		map.put("jobnumber", jobnumber);
		map.put("password", password);
		map.put("createtime", new Date(System.currentTimeMillis()));
		userLogin.addUser(map);
		return true;
	}
}
