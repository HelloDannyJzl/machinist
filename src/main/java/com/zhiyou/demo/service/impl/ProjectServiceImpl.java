package com.zhiyou.demo.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.zhiyou.demo.dao.ProjectDao;
import com.zhiyou.demo.service.ProjectService;
import com.zhiyou.demo.until.PageUtil;

import jodd.util.StringUtil;

/**
 * 项目表Service层实现类
 * @author jzl
 */
@Service
public class ProjectServiceImpl implements ProjectService{

	private Logger logger = LogManager.getLogger("ProjectServiceImpl");
	
	@Resource
	private ProjectDao projectDao;
	
	@Override
	public boolean addProject(String pronumber, String proname, String proaddress) {
		if(StringUtil.isEmpty(pronumber)||StringUtil.isEmpty(proname)||StringUtil.isEmpty(proaddress)){
			return false;
		}
		Map<String, Object> param = new HashMap<>();
		param.put("id", UUID.randomUUID().toString());
		param.put("pronumber", pronumber);
		param.put("proname", proname);
		param.put("proaddress", proaddress);
		param.put("createtime", new Date(System.currentTimeMillis()));
		param.put("flag", "0");
		try {
			projectDao.addProject(param);
		} catch (RuntimeException e) {
			logger.error("ProjectServiceImpl.addProject" ,e);
			e.printStackTrace();
		}
		return true;
	}
	

	@Override
	public boolean updateProject(String id,String flag, String proname,String proaddress) {
		if(StringUtil.isEmpty(id)){
			return false;
		}
		if(proname==null&&flag==null&&proaddress==null){
			return false;
		}
		Map<String, Object> map = queryProForNameId(id);
		if(map==null||map.size()<1){
			return false;
		}
		Map<String, Object> param = new HashMap<>();
		param.put("id", id);
		param.put("flag", flag);
		if("1".equals(flag)){
			param.put("starttime", new Date(System.currentTimeMillis()));
		}else if("2".equals(flag)){
			param.put("endtime", new Date(System.currentTimeMillis()));
		}
		param.put("proname", proname);
		param.put("proaddress", proaddress);
		param.put("updatetime", new Date(System.currentTimeMillis()));
		try {
			projectDao.updateProject(param);
		} catch (RuntimeException e) {
			logger.error("ProjectServiceImpl.updateProject" ,e);
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public List<Map<String, Object>> queryAllProject(int page, String flag,String proname,String pronumber) {
		if(page<1){
			return null;
		}
		Map<String, Object> param  = PageUtil.getStartEndMap(page);
		param.put("flag", flag);
		if(StringUtil.isNotEmpty(proname)){
			param.put("likeproname", proname);
		}
		if(StringUtil.isNotEmpty(pronumber)){
			param.put("likepronumber", pronumber);
		}
		List<Map<String, Object>> list = null;
		try {
			list = projectDao.queryAllProject(param);
		} catch (RuntimeException e) {
			logger.error("ProjectServiceImpl.queryAllProject" ,e);
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int queryAllCount(String flag,String proname,String pronumber) {
		int cnt = 0;
		Map<String, Object> map = new HashMap<>();
		map.put("flag", flag);
		map.put("likeproname", proname);
		map.put("likepronumber", pronumber);
		try {
			cnt = projectDao.queryAllCount(map );
		} catch (RuntimeException e) {
			logger.error("ProjectServiceImpl.queryAllCount" ,e);
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public Map<String, Object> queryProForNameId(String id) {
		if(StringUtil.isEmpty(id)){
			return null;
		}
		Map<String, Object> param = new HashMap<>();
		param.put("id", id);
		try {
			param = projectDao.queryProjectFornameid(param);
		} catch (RuntimeException e) {
			logger.error("ProjectServiceImpl.queryProForNameId" ,e);
			e.printStackTrace();
		}
		return param;
	}



}
