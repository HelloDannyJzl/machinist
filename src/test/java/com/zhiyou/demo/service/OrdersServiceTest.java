package com.zhiyou.demo.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import com.zhiyou.demo.BaseJunit4Test;

/** 
* @author wangyanlong: 
* 类说明 订单service层测试用例
*/
public class OrdersServiceTest extends BaseJunit4Test{
	
	@Resource
	private OrdersService ordersService;
	
	@Test
	public void addOrdersTest(){
		String projectid = "20161209";
		String farmername = "杨过";
		Date starttime = new Date(System.currentTimeMillis());
		Date endtime = new Date(System.currentTimeMillis());
		int number = 10;
		double price = 8.0;
		ordersService.addOrders(projectid, farmername, starttime, endtime, number, price);
	}

	
	@Test
	public void updateOrdersTest(){
		String ordersid = "20161209-01";
		Date starttime = new Date(System.currentTimeMillis());
		Date endtime = new Date(System.currentTimeMillis());
		int number = 15;
		double price = 6.0;
		ordersService.updateOrders(ordersid, starttime, endtime, number, price);
	}
	
	@Test
	public void updateOrdersTest1(){
		String ordersid = "20161209-01";
		String foremanid = "741852963";
		String flag = "3";
		ordersService.updateOrders(ordersid, foremanid, flag);
	}
	
	@Test
	public void updateOrdersByManageTest1(){
		String ordersid = "20161209-01";
		String managename = "管理员";
		String flag = "1";
		ordersService.updateOrdersByManage(ordersid, managename, flag);
	}
	

	@Test
	public void deleteOrdersTest(){
		String ordersid = "20161209-01";
		ordersService.deleteOrders(ordersid);
	}
	

	@Test
	public void queryOrderByIdTest(){
		String ordersid = "20161209-01";
		Map<String, Object> map = ordersService.queryOrderById(ordersid);
		System.out.println(map);
	}
	
	@Test
	public void queryAllOrdersTest(){
		String farmername = "杨过";
		String foremanname = null;
		String flag = "0";
		int page = 1;
		List<Map<String, Object>> queryAllOrders = ordersService.queryAllOrders(farmername, foremanname, flag, page);
		for(Map<String, Object> map:queryAllOrders){
			System.out.println(map);
		}
	}

	@Test
	public void queryAllCountTest(){
		String farmername = "杨过";
		String foremanname = null;
		String flag = "0";
		int queryAllCount = ordersService.queryAllCount(farmername, foremanname, flag);
		System.out.println(queryAllCount);
	}
	
	@Test
	public void queryOrdersTest(){
		String projectid = "201601";
		//String ordersid = "201601";
		String ordersid = null;
		String farmername = "汪永辉";
		String foremanname = null;
		String flag = "0";
		int page = 1;
		List<Map<String, Object>> queryAllOrders = ordersService.queryOrders(projectid, ordersid, farmername, foremanname, flag, page);
		for(Map<String, Object> map:queryAllOrders){
			System.out.println(map);
		}
	}
	
	@Test
	public void queryCountTest(){
		String projectid = "201601";
		String ordersid = null;
		String farmername = "汪永辉";
		String foremanname = null;
		String flag = "0";
		int queryAllCount = ordersService.queryCount(projectid, ordersid, farmername, foremanname, flag);
		System.out.println(queryAllCount);
	}
	
	
}
