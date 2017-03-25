package com.zhiyou.demo.until;

import java.util.HashMap;
import java.util.Map;

/**
 * 分页工具类
 * @author Administrator
 *
 */
public class PageUtil {
	
	private static int pagelength = 10;
	
	/**
	 * 根据访问页数，获取数据开始条数结束条数
	 * @param page 访问的页数
	 * @return 开始页数和结束页数
	 */
	public static int[] getStartEndNumber(int page){
		int[] returnValue = new int[2];
		returnValue[0] = (page - 1)*pagelength;
		returnValue[1] = pagelength;
		return returnValue;
	}
	
	/**
	 * 将数据开始条数和结束条数添加进map集合
	 * @param page 访问的页数
	 * @return 含有数据开始条数和结束条数的map结合
	 */
	public static Map<String,Object> getStartEndMap(int page){
		Map<String,Object> map = new HashMap<>();
		int[] pagenumber = PageUtil.getStartEndNumber(page);
		map.put("startnumber", pagenumber[0]);
		map.put("endnumber", pagenumber[1]);
		return map;
	}
	
	/**
	 * 根据总记录数，计算总页数
	 * @param totalnumber 总记录数
	 * @return 总页数
	 */
	public static String getTotalPage(int totalnumber){
		int totalpage = totalnumber/pagelength;
		if(totalnumber%pagelength >0){
			totalpage = totalpage + 1;
		}
		return String.valueOf(totalpage);
	}
}
