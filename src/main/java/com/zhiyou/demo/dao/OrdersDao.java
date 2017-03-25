package com.zhiyou.demo.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

/** 
* @author wangyanlong: 
* 类说明 订单持久化层接口
*/
@Repository
public interface OrdersDao {
	
	/**
	 * 将订单信息添加到订单表中(农户 不需要管理员审核)
	 * @param map 订单信息集合
	 */
	void addOrders(Map<String,Object> map);
	
	/**
	 * 查询项目编号下有几条订单
	 * @param projectid 项目编号
	 * @return 返回订单数目
	 */
	int queryByProjectid (String projectid);

	/**
	 * 修改订单(1、工头添加工头信息2、农户修改订单信息、修改标识 3、管理员审核、修改标识)
	 * @param map 订单信息集合
	 */
	void updateOrders(Map<String,Object> map);
	
	/**
	 * 通过订单编号查询订单详情
	 * @param ordersid
	 * @return
	 */
	Map<String,Object> queryOrderById(String ordersid);
	
	/**
	 * 查询订单
	 * @param projectid 项目编号
	 * @return
	 */
	List<Map<String,Object>> queryOrdersById(String projectid);
	
	/**
	 * 条件分页查询所有订单（管理员查询所有，农户查询自己名下所有订单，工头查询自己名下所有订单）
	 * @param map 查询条件集合
	 * @return 查询到的订单集合
	 */
	List<Map<String,Object>> queryAllOrders(Map<String,Object> map);
	
	/**
	 * 条件查询订单数量（管理员查询所有，农户查询自己名下所有订单，工头查询自己名下所有订单）
	 * @param map 查询条件集合 
	 * @return 订单数量
	 */
	int queryAllCount(Map<String,Object> map);
	
	/**
	 * 根据工头姓名查询其名下所有订单数量
	 * @param foremanname 工头姓名
	 * @return 订单数量
	 */
	int queryCountByForemanname(String foremanname);
	
	/**
	 * 删除订单（未删除将flag修改为4）
	 * @param map 查询条件集合
	 */
	void deleteOrders(Map<String,Object> map);
	
}
