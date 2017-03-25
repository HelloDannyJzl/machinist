package com.zhiyou.demo.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

/**
 * 项目管理dao层接口
 * @author jzl
 */
@Repository
public interface ProjectDao {
	
	/**
	 * 添加项目
	 * @param param 项目信息
	 */
	void addProject(Map<String, Object> param);
	
	/**
	 * 修改项目
	 * @param param
	 */
	void updateProject(Map<String, Object> param);
	
	/**
	 * 根据项目id查询项目
	 * @param param  项目相关信息
	 * @return 项目详细信息
	 */
	Map<String, Object> queryProjectFornameid(Map<String, Object> param);
	
	/**
	 * 根据条件查询所有项目
	 * param 条件集合：包括分页信息，
	 * 	包含proname/pronumber
	 * flag项目标示位：0表示项目未开始，1表示项目进行中，2表示项目已结束，3表示项目已删除
	 * @return 项目信息集合
	 */
	List<Map<String, Object>> queryAllProject(Map<String, Object> param);
	
	
	/**
	 * 查询项目表中所有项目的数量
	 * param:包含proname/pronumber
	 * 	flag项目标示位：0表示项目未开始，1表示项目进行中，2表示项目已结束，3表示项目已删除
	 * @return 数据数量
	 */
    int queryAllCount(Map<String, Object> param);
    
}
