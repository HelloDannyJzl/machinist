package com.zhiyou.demo.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

/**
 * 项目表service层接口
 * @author jzl
 */
@Service
public interface ProjectService {

	/**
	 * 添加项目
	 * @param pronumber 项目编号
	 * @param proname 项目名称
	 * @param proaddress 项目地址
	 * @return 添加成功返回true，否则返回false
	 */
	boolean addProject(String pronumber,String proname,String proaddress);
	
	/**
	 * 修改项目
	 * @param flag项目标示位：0表示项目未开始，1表示项目进行中，2表示项目已结束，3表示项目已删除
	 * @param id 项目id
	 * @param proname 项目名称
	 * @param proaddress 项目地址
	 * @return 修改成功返回true，否则返回false
	 */
	boolean updateProject(String id,String flag,String proname,String proaddress);
	
	/**
	 * 根据条件查询项目
	 * @param id 项目id
	 * @return 项目信息 
	 */
	Map<String, Object> queryProForNameId(String id);
	
	/**
	 * 根据条件查询所有项目
	 * @param proname 项目名称
	 * @param pronumber 项目编号
	 * @param page 分页页数
	 * @param flag项目标示位：0表示项目未开始，1表示项目进行中，2表示项目已结束，3表示项目已删除
	 * @return 查询到的项目信息集合，未查到返回null
	 */ 
	List<Map<String, Object>> queryAllProject(int page,String flag,String proname,String pronumber);
	
	/**
	 * 根据条件查询项目表中所有项目的数量
	 * @param proname 项目名称
	 * @param pronumber 项目编号
	 * @param flag项目标示位：0表示项目未开始，1表示项目进行中，2表示项目已结束，3表示项目已删除
	 * @return 查询到的数据数量，未查到返回false
	 */
	int queryAllCount(String flag,String proname,String pronumber);
	
	
}
