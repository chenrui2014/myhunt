package com.huntech.huntoms.oms.repository;

import java.util.List;
import java.util.Map;

/* 集团统计分析数据库访问 */
public interface GroupCensusJdbc {
	
	
	public List<Map<String, Object>> getBranchOrganData(Long id,Long year);
	
	public List<Map<String, Object>> getBranchOrganData(Long id,Long year, Long month);
	
	public List<Map<String, Object>> getGroupOrganData(Long year);
	
	public List<Map<String, Object>> getGroupOrganData(Long year, Long month);
	
	/* 获取集团年分列表数据 */
	public List<Map<String, Object>> getGroupYearConditionData();
	
	/* 获取集团年分列表数据 */
	public List<Map<String, Object>> getGroupMonthConditionData();

	/* 获取集团电量历年数据 */
	public List<Map<String, Object>> getEOverYearData();
	
	/* 获取集团电量年数据 */
	public List<Map<String, Object>> getEYearData(Long year);
	
	/* 获取集团电量约数据 */
	public List<Map<String, Object>> getEMonthData(Long year, Long month);
	
	/* 获取集团电量年数据 */
	public List<Map<String, Object>> getBranchEYearData(Long id,Long year);
	
	/* 获取集团电量约数据 */
	public List<Map<String, Object>> getBranchEMonthData(Long id, Long year, Long month);
	
	/* 获取集团发电效率历年数据 */
	public List<Map<String, Object>> getEfficEOverYearData();
	
	/* 获取集团发电效率年数据 */
	public List<Map<String, Object>> getEfficEYearData(Long year);
	
	/* 获取集团计划发电执行历年数据 */
	public List<Map<String, Object>> getPlanEOverYearData();
	
	/* 获取集团计划发电执行年数据 */
	public List<Map<String, Object>> getPlanEYearData(Long year);
	
	/* 获取集团计划上网发电执行历年数据 */
	public List<Map<String, Object>> getPlanOnNetEOverYearData();
	
	/* 获取集团计划上网发电执行年数据 */
	public List<Map<String, Object>> getPlanOnNetEYearData(Long year);
	
	/* 获取集团计划上网发电执行历年数据 */
	public List<Map<String, Object>> getTotalEOverYearData();
	
	/* 获取集团计划上网发电执行年数据 */
	public List<Map<String, Object>> getTotalEYearData(Long year);
	
	
}
