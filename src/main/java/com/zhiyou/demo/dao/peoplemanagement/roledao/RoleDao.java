package com.zhiyou.demo.dao.peoplemanagement.roledao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

/**
 * 角色管理Dao层接口
 * @author Administrator
 *
 */
@Repository
public interface RoleDao {
	
	/**
	 * 添加角色
	 * @param map 角色信息
	 */
	void addRele(Map<String,Object> map);
	
	/**
	 * 修改角色
	 * @param map 修改之后的角色信息
	 */
	void updateRole(Map<String,Object> map);
	
	/**
	 * 查询某一角色
	 * @param map具体角色的信息
	 */
	Map<String,Object> queryRole(Map<String,Object> map);
	
	/**
	 * 查询所有角色
	 * @return 查到返回所有角色信息，否则返回null
	 */
	List<Map<String,Object>> queryRole();
	
	/**
	 * 删除角色
	 * @param id 要删除角色的ID
	 */
	void deleteRole(String id);
	
	/**
	 * 查询所有的角色数量
	 * @return 角色数量
	 */
	int queryALLCount();
	
	/**
	 * 分页查询，查询书籍类型
	 * @param map 查询条件
	 * @return 查到返回所有角色，否则返回null
	 */
	List<Map<String,Object>> queryAllRole(Map<String,Object> map);
}
