package com.zhiyou.demo.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

/** 
* @author wangyanlong: 
* 类说明  订单service层接口
*/
@Service
public interface OrdersService {
	
	/**
	 * 添加订单
	 * @param projectid 项目编号
	 * @param farmerid 农户id
	 * @param starttime 开工时间
	 * @param endtime 完工时间
	 * @param number 用工需求人数
	 * @param price 用工单价
	 * @return 添加成功返回true，否则返回false
	 */
	boolean addOrders(String projectid,String farmerid,Date starttime,Date endtime,int number,double price);
	
	/**
	 * 农户修改订单
	 * @param ordersid 订单编号 
	 * @param starttime 开工时间
	 * @param endtime 完工时间
	 * *@param number 用工需求人数
	 * @param price 用工单价
	 * @return 修改成功返回true，否则返回false
	 */
	boolean updateOrders(String ordersid,Date starttime,Date endtime,int number,double price);

	/**
	 * 工头开始结束接单
	 * @param ordersid 订单编号
	 * @param foremanname 工头姓名
	 * @param flag 订单状态标识
	 * @return 成功返回true，否则返回false
	 */
	boolean updateOrders(String ordersid,String foremanname,String flag);
	
	/**
	 * 管理员审核订单
	 * @param ordersid 订单编号
	 * @param managename 管理员姓名
	 * @param flag 订单状态标识
	 * @return 成功返回true，否则返回false
	 */
	boolean updateOrdersByManage(String ordersid,String managename,String flag);
	
	
	
	/**
	 * 删除订单（未删除 只修改标识）
	 * @param ordersid 订单编号
	 * @return 成功返回true，否则返回false
	 */
	boolean deleteOrders(String ordersid);
	
	/**
	 * 通过项目编号查询订单详情
	 * @param ordersid 订单编号
	 * @return 成功返回集合，否则返回null
	 */
	Map<String,Object> queryOrderById(String ordersid);
	
	/**
	 * 分页查询所有的订单
	 * @param farmername 农户姓名
	 * @param foremanname 工头姓名
	 * @param flag 订单状态标识
	 * @param page 要访问的页数
	 * @return 返回查询到的订单集合
	 */
	List<Map<String, Object>> queryAllOrders(String farmername,String foremanname,String flag,int page);
	
	/**
	 * 条件查询订单数量
	 * @param farmername 农户姓名
	 * @param foremanname 工头姓名
	 * @param flag 订单状态标识
	 * @return 订单数量
	 */
	int queryAllCount(String farmername,String foremanname,String flag);
	
	/**
	 * 工头下所有订单数量
	 * @param foremanname 工头姓名
	 * @return 订单数量
	 */
	int queryCountByForemanname(String foremanname);	
	
	/**
	 * 
	 * @param projectid
	 * @param ordersid
	 * @param farmername
	 * @param foremanname
	 * @param flag
	 * @return
	 */
	int queryCount(String projectid ,String ordersid,String farmername,String foremanname,String flag);
	
	/**
	 * 
	 * @param projectid
	 * @param ordersid
	 * @param farmername
	 * @param foremanname
	 * @param flag
	 * @param page
	 * @return
	 */
	List<Map<String, Object>> queryOrders(String projectid ,String ordersid,String farmername,String foremanname,String flag,int page);
}
