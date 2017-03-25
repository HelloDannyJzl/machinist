package com.zhiyou.demo.dao.peoplemanagement.userdao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

/**
 * 用户注册、管理Dao层接口
 * @author Administrator
 *
 */
@Repository
public interface UserLogin {
	
	/**
	 * 添加注册信息
	 * @param map
	 */
	void addUser(Map<String,Object> map);
	
	/**
	 * 查询用户（按姓名、所属工头）
	 * @param map 查询条件 
	 * @return 查到返回List信息集合，否则返回null
	 */
	List<Map<String,Object>> queryUser(Map<String,Object> map);
	
	/**
	 * 查询所有正常用户
	 * @return 所有正常用户的数量
	 */
	int queryJobNumber();
	
	/**
	 * 修改用户信息
	 * @param map 有用户信息
	 */
	void updateUser(Map<String,Object> map);
	
	/**
	 * 分页查询所用户
	 * @param map 查询条件
	 * @return 查到返回list集合，否则返回null
	 */
	List<Map<String,Object>> queryAllUser(Map<String,Object> map);
	
	
	/**
	 * 删除用户
	 * @param map 删除条件
	 */
	void deleteUser(Map<String,Object> map);
}
