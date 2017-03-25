package com.zhiyou.demo.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zhiyou.demo.dao.OrdersDao;
import com.zhiyou.demo.service.OrdersService;
import com.zhiyou.demo.until.PageUtil;

import jodd.util.StringUtil;

/** 
* @author wangyanlong: 
* 类说明 订单service层接口实现类
*/
@Service
public class OrdersServiceImpl implements OrdersService {
	@Resource
	private OrdersDao ordersDao;

	/**
	 * 组装订单编号 
	 * @param projectid 项目编号
	 * @return
	 */
	private String mergeOrdersid(String projectid){
		int number = ordersDao.queryByProjectid(projectid);
		String ordersid = null;
		if(number<9){
			ordersid = projectid +"-0" +String.valueOf(++number);
		}else if(number<99){
			ordersid = projectid +"-" +String.valueOf(++number);
		}else
			ordersid = null;
		return ordersid ;
	}
	
	@Override
	public boolean addOrders(String projectid, String farmername, Date starttime, Date endtime,
			int number, double price) {
		if(StringUtil.isEmpty(projectid)||StringUtil.isEmpty(farmername)||starttime==null||endtime == null||number<=0||price<0){
			return false;
		}
		String ordersid = mergeOrdersid(projectid);
		if(ordersid==null)
			return false;
		Map<String, Object> param = new HashMap<>();
		param.put("id", UUID.randomUUID().toString());
		param.put("projectid", projectid);
		param.put("ordersid", ordersid);
		param.put("farmername", farmername);
		param.put("createtime", new Date(System.currentTimeMillis()));
		param.put("starttime", new Date(System.currentTimeMillis()));
		param.put("endtime", new Date(System.currentTimeMillis()));
		param.put("number", number);
		param.put("price", price);
		param.put("flag", "0");
		ordersDao.addOrders(param);
		return true;
	}

	@Override
	public boolean updateOrders(String ordersid, Date starttime, Date endtime, int number, double price) {
		if(StringUtil.isEmpty(ordersid)||starttime==null||endtime == null||number<=0||price<0){
			return false;
		}
		Map<String, Object> param = new HashMap<>();
		param.put("ordersid", ordersid);
		param.put("starttime", starttime);
		param.put("endtime", endtime);
		param.put("number", number);
		param.put("price", price);
		param.put("updatetime", new Date(System.currentTimeMillis()));
		ordersDao.updateOrders(param);
		return true;
	}

	@Override
	public boolean updateOrders(String ordersid, String foremanname, String flag) {
		if(StringUtil.isEmpty(ordersid)||StringUtil.isEmpty(foremanname)||StringUtil.isEmpty(flag)){
			return false;
		}
		Map<String, Object> param = new HashMap<>();
		param.put("ordersid", ordersid);
		param.put("foremanname", foremanname);
		param.put("flag", flag);
		param.put("updatetime", new Date(System.currentTimeMillis()));
		ordersDao.updateOrders(param);
		return true;
	}

	@Override
	public boolean updateOrdersByManage(String ordersid, String managename, String flag) {
		if(StringUtil.isEmpty(ordersid)||StringUtil.isEmpty(managename)||StringUtil.isEmpty(flag)){
			return false;
		}
		Map<String, Object> param = new HashMap<>();
		param.put("ordersid", ordersid);
		param.put("managename", managename);
		param.put("flag", flag);
		param.put("updatetime", new Date(System.currentTimeMillis()));
		ordersDao.updateOrders(param);
		return true;
	}

	@Override
	public boolean deleteOrders(String ordersid) {
		if(StringUtil.isEmpty(ordersid)){
			return false;
		}
		Map<String, Object> param = new HashMap<>();
		param.put("ordersid", ordersid);
		param.put("flag", "4");
		param.put("updatetime", new Date(System.currentTimeMillis()));
		ordersDao.deleteOrders(param);
		return true;
	}

	@Override
	public Map<String,Object> queryOrderById(String ordersid) {
		Map<String, Object> map = ordersDao.queryOrderById(ordersid);
		return map;
	}

	@Override
	public List<Map<String, Object>> queryAllOrders(String farmername, String foremanname, String flag, int page) {
		if(page<0)
			return null;
		Map<String, Object> param = new HashMap<>();
		param = PageUtil.getStartEndMap(page);
		param.put("farmername", farmername);
		param.put("foremanname", foremanname);
		param.put("flag", flag);
		List<Map<String, Object>> queryAllOrders = ordersDao.queryAllOrders(param);
		return queryAllOrders;
	}

	@Override
	public int queryAllCount(String farmername, String foremanname, String flag) {
		Map<String, Object> param = new HashMap<>();
		param.put("farmername", farmername);
		param.put("foremanname", foremanname);
		param.put("flag", flag);
		int queryAllCount = ordersDao.queryAllCount(param);
		return queryAllCount;
	}

	@Override
	public List<Map<String, Object>> queryOrders(String projectid, String ordersid, String farmername,
			String foremanname, String flag, int page) {
		if(page<0)
			return null;
		Map<String, Object> param = new HashMap<>();
		param = PageUtil.getStartEndMap(page);
		param.put("likeprojectid", projectid);
		param.put("likeordersid", ordersid);
		param.put("farmername", farmername);
		param.put("foremanname", foremanname);
		param.put("flag", flag);
		List<Map<String, Object>> queryOrders = ordersDao.queryAllOrders(param);
		return queryOrders;
	}

	@Override
	public int queryCount(String projectid, String ordersid, String farmername, String foremanname, String flag) {
		Map<String, Object> param = new HashMap<>();
		param.put("likeprojectid", projectid);
		param.put("likeordersid", ordersid);
		param.put("farmername", farmername);
		param.put("foremanname", foremanname);
		param.put("flag", flag);
		int queryCount = ordersDao.queryAllCount(param);
		return queryCount;
	}

	@Override
	public int queryCountByForemanname(String foremanname) {
		int queryCount = ordersDao.queryCountByForemanname(foremanname);
		return queryCount;
	}



	
	
	
}
