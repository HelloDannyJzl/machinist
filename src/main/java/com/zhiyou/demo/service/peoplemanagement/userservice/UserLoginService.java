package com.zhiyou.demo.service.peoplemanagement.userservice;

import java.util.List;
import java.util.Map;

/**
 * 用户注册、管理Service层接口
 * @author Administrator
 *
 */
public interface UserLoginService {
	
	/**
	 * 添加农户注册信息
	 * @param name 农户名字
	 * @param phone 农户联系方式
	 * @param age 农户年龄
	 * @param gender 农户性别
	 * @param adress 农户地址
	 * @param idcard 农户身份证号
	 * @param plantadress 农户种植地址
	 * @param plantnumber 农户种植数量（单位/亩）
	 * @param plantbreed
	 * @param reason
	 * @return
	 */
	boolean addFarmer(String name,String phone,int age,String gender,String adress,String idcard
			,String plantadress,int plantnumber,String plantbreed);
	
	/**
	 * 添加工头注册信息
	 * @param name 角色名称
	 * @param phone 工头联系方式
	 * @param age 工头年龄
	 * @param gender 工头性别
	 * @param adress 工头地址
	 * @param idcard 工头身份证号码
	 * @param jobnumber 工头编号 
	 * @return 添加成功返回true，否则返回fale；
	 */
	boolean addForman(String name,String phone,int age,String gender,String adress,String idcard);
	
	/**
	 * 添加工人注册信息
	 * @param name 工人姓名
	 * @param phone 工人联系方式
	 * @param age 工人年龄
	 * @param gender 工人性别
	 * @param adress 工人家庭住址
	 * @param idcard 工人身份证号码
	 * @param jobnumber 工人编号
	 * @param foremanid 所属工头id
	 * @return 添加成功返回true，否则返回false
	 */
	boolean addWorker(String name,String phone,int age,String gender,String adress,String idcard,String foremanid);
	
	/**
	 * 添加管理员
	 * @param name 工人姓名
	 * @param phone 工人联系方式
	 * @param age 工人年龄
	 * @param gender 工人性别
	 * @param adress 工人家庭住址
	 * @param idcard 工人身份证号码
	 * @param jobnumber 工人编号
	 * @return 添加成功返回true，否则返回false
	 */
	boolean addManage(String name,String phone,int age,String gender,String adress,String idcard,String jobnumber);
	/**
	 * 查询用户信息
	 * @param map 查询添加
	 * @return 查到返回集合，否则返回null
	 */
	List<Map<String,Object>> queryUser(Map<String,Object> map);
	
	
	
	/**
	 * 查询所有正常用户的数量
	 * @return 所有正常用户的数量
	 */
	int queryJobNumber(Map<String,Object> map);
	
	/**
	 * 删除用户
	 * @param id 用户id
	 * @return 删除成功返回true，否则返回false、
	 */
	boolean deleteUser(String id);
	
	/**
	 * 修改用户信息
	 * @param map 要修改的信息
	 * @return 修改成功返回true否则返回false
	 */
	boolean updateUser(Map<String,Object> map);
	
	/**
	 * 分页查询所有信息
	 * @param page 访问的页数
	 * @return 当前页的用户信息
	 */
	List<Map<String,Object>> queryAllUser(int page);
}
