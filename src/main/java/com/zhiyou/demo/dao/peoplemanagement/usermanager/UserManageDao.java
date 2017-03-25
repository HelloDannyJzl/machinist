package com.zhiyou.demo.dao.peoplemanagement.usermanager;

import java.util.Map;

/**
 * 用户管理Dao层接口
 * @author Administrator
 *
 */
public interface UserManageDao {
	
	/**
	 * 修改用户信息
	 * @param map 有用户信息
	 */
	void updateUser(Map<String,Object> map);
	
	
	
}
