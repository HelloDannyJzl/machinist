package com.zhiyou.demo.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.junit.Test;

import com.zhiyou.demo.BaseJunit4Test;

/** 
* @author wangyanlong: 
* 类说明  订单持久化层测试类
*/
public class OrdersTest extends BaseJunit4Test{
	
	@Resource
	private OrdersDao ordersDao;

	@Test
	public void addOrdersTest(){
		Map<String, Object> param = new HashMap<>();
		param.put("id", UUID.randomUUID().toString());
		param.put("projectid", "20161208");
		param.put("ordersid", "20161208-01");
		param.put("farmername", "金庸");
		param.put("createtime", new Date(System.currentTimeMillis()));
		param.put("starttime", new Date(System.currentTimeMillis()));
		param.put("endtime", new Date(System.currentTimeMillis()));
		param.put("flag", "0");
		param.put("number", 10);
		param.put("price", 8);
		ordersDao.addOrders(param);
	}
	
	@Test
	public void queryByProjectidTest(){
		String projectid = "20161209";
		int number = ordersDao.queryByProjectid(projectid);
		System.out.println(number);
	}
	
	
	@Test
	public void updateOrdersTest(){
		Map<String, Object> param = new HashMap<>();
		param.put("ordersid", "20161208-01");
		param.put("updtetime", new Date(System.currentTimeMillis()));
//		param.put("foremanid", "111111");
//		param.put("starttime", new Date(System.currentTimeMillis()));
//		param.put("endtime", new Date(System.currentTimeMillis()));
		param.put("flag", "1");
//		param.put("number", 20);
//		param.put("price", 10);
		param.put("managename", "管理员");
		ordersDao.updateOrders(param);
	}

	@Test
	public void deleteOrdersTest(){
		Map<String, Object> param = new HashMap<>();
		param.put("ordersid", "20161208-01");
		param.put("updtetime", new Date(System.currentTimeMillis()));
		param.put("flag", "3");
		ordersDao.deleteOrders(param);
	}
	
	@Test
	public void queryOrderByIdTest(){
		String ordersid = "20161209-01";
		Map<String, Object> map = ordersDao.queryOrderById(ordersid);
		System.out.println(map);
	}
	

	@Test
	public void quertAllOrdersTest(){
		Map<String, Object> param = new HashMap<>();
		param.put("farmername","杨过");
		param.put("startnumber", 1);
		param.put("endnumber", 2);
		//param.put("flag", "0");
		List<Map<String, Object>> queryAllOrders = ordersDao.queryAllOrders(param);
		for (Map<String, Object> map : queryAllOrders) {
			System.out.println(map);
		}
		
	}
	

	@Test
	public void queryAllCountTest(){
		Map<String, Object> param = new HashMap<>();
		//param.put("farmername","金庸");
		param.put("flag","0");
		int queryAllCount = ordersDao.queryAllCount(param);
		System.out.println(queryAllCount);
	}
	
}
