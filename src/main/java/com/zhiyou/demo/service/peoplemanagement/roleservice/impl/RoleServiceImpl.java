package com.zhiyou.demo.service.peoplemanagement.roleservice.impl;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.zhiyou.demo.dao.peoplemanagement.roledao.RoleDao;
import com.zhiyou.demo.service.peoplemanagement.roleservice.RoleService;
import com.zhiyou.demo.until.PageUtil;

import jodd.util.StringUtil;
@Service
public class RoleServiceImpl implements RoleService {
	
	private Logger logger = LogManager.getLogger("RoleServiceImpl");
	
	@Resource
	private RoleDao roleDao;
	
	@Override
	public boolean addRole(String roleName, String userid) {
		if(StringUtil.isEmpty(roleName) && StringUtil.isEmpty(userid))
			return false;
		Map<String,Object> map = new HashMap<>();
		map.put("id", UUID.randomUUID().toString());
		map.put("rolename", roleName);
		map.put("userid", userid);
		map.put("createtime", new Date(System.currentTimeMillis()));
		roleDao.addRele(map);
		return true;
	}

	@Override
	public boolean updateRole(String roleName,String id,String userid) {
		if(StringUtil.isEmpty(roleName) || StringUtil.isEmpty(id))
			return false;
		Map<String,Object> map = new HashMap<>();
		map.put("rolename", roleName);
		map.put("id", id);
		map.put("userid", userid);
		map.put("updatetime", new Date(System.currentTimeMillis()));
		roleDao.updateRole(map);
		return true;
	}

	@Override
	public Map<String, Object> queryRole(String id,String rolename) {
		if(StringUtil.isEmpty(id)&&StringUtil.isEmpty(rolename)){
			logger.error("RoleServiceImpl.queryRole:角色id和角色名称全部为空");
			return null;
		}
		Map<String,Object> map = new HashMap<>();
		map.put("id", id);
		map.put("rolename", rolename);
		map = roleDao.queryRole(map);
		return map;
	}

	@Override
	public boolean deleteRole(String id) {
		if(StringUtil.isEmpty(id))
			return false;
		roleDao.deleteRole(id);
		return true;
	}

	@Override
	public int queryAllCount() {
		int number = roleDao.queryALLCount();
		return number;
	}

	@Override
	public List<Map<String, Object>> queryAllRole(int page) {
		if(page < 0)
			return null;
		Map<String,Object> map = new HashMap<>();
		map = PageUtil.getStartEndMap(page);
		List<Map<String,Object>> list = roleDao.queryAllRole(map);
		return list;
	}

	@Override
	public List<Map<String,Object>> queryAllRole() {
		List<Map<String,Object>> listRole = roleDao.queryRole();
		return listRole;
	}
}
