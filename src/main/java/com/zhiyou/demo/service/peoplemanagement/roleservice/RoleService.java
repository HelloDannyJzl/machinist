package com.zhiyou.demo.service.peoplemanagement.roleservice;

import java.util.List;
import java.util.Map;

/**
 * 角色管理service层接口
 * @author Administrator
 *
 */
public interface RoleService {
	
	/**
	 * 创建角色
	 * @param roleName 角色名称
	 * @param userid 创建角色者的id
	 * @return 创建成功返回true，否则返回false 
	 */
	boolean addRole(String roleName,String userid);
	
	/**
	 * 修改角色名称
	 * @param roleName 角色名称
	 * @return 修改成功返回true，否则返回false
	 */
	boolean updateRole(String roleName,String id,String userid);
	
	/**
	 * 查询角色
	 * @param id 要查寻角色的id
	 * @return
	 */
	Map<String,Object> queryRole(String id,String rolename);
	
	/**
	 * 删除角色
	 * @param id 要删出角色的id
	 */
	boolean deleteRole(String id);
	
	/**
	 * 查询角色数量
	 * @return 角色数量
	 */
	int queryAllCount();
	
	/**
	 * 分页查询所有角色
	 * @param page 要访问的页数
	 * @return 查询到返回数据集合，否则返回null
	 */
	List<Map<String,Object>> queryAllRole(int page);
	
	/**
	 * 查询所有角色信息
	 * @return 查到返回所有角色信息，否则返回false
	 */
	List<Map<String,Object>> queryAllRole();
	
}
