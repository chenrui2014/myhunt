package com.huntech.huntoms.oms.repository;

import java.util.List;
import java.util.Map;


/* 集团对比分析数据库访问 */
public interface GroupContrastJdbc {
	
	public List<Map<String, Object>> getBranchStationRankingYearData(Long id, Long year);
	
	public List<Map<String, Object>> getBranchStationRankingMonthData(Long id, Long year, Long month);
	
	public List<Map<String, Object>> getStationRankingYearData(Long year);
	
	public List<Map<String, Object>> getStationRankingMonthData(Long year, Long month);

	/* 获取集团年分列表数据 */
	public List<Map<String, Object>> getStationYearConditionData();
	
	/* 获取集团年分列表数据 */
	public List<Map<String, Object>> getStationMonthConditionData();
	
	/* 获取电站间厂用电量年数据 */
	public List<Map<String, Object>> getStationDevoteEYearData(Long year);
	
	public List<Map<String, Object>> getBranchStationEOnetYearData(Long id,Long year);
	
	public List<Map<String, Object>> getStationEOnetYearData(Long year);
	
	public List<Map<String, Object>> getStationEOnetMonthData(Long year, Long month);
	
	public List<Map<String, Object>> getBranchStationEOnetMonthData(Long id,Long year, Long month);

	/* 获取电站间厂用电量月数据 */
	public List<Map<String, Object>> getStationDevoteEMonthData(Long year, Long month);
	
	/* 获取电站间厂用电量年数据 */
	public List<Map<String, Object>> getStationFactoryEYearData(Long year);

	/* 获取电站间厂用电量月数据 */
	public List<Map<String, Object>> getStationFactoryEMonthData(Long year, Long month);

	/* 获取电站间综合厂用电量年数据 */
	public List<Map<String, Object>> getStationCompreFactoryEYearData(Long year);

	/* 获取电站间综合厂用电量月数据 */
	public List<Map<String, Object>> getStationCompreFactoryEMonthData(Long year, Long month);
	
	/* 获取电站间发电计划执行年数据 */
	public List<Map<String, Object>> getStationPlanEYearData(Long year);

	/* 获取电站间发电计划执月数据 */
	public List<Map<String, Object>> getStationPlanEMonthData(Long year, Long month);
	
	/* 获取电站间上网计划执行年数据 */
	public List<Map<String, Object>> getStationPlanOnNetEYearData(Long year);

	/* 获取电站间上网计划执月数据 */
	public List<Map<String, Object>> getStationPlanOnNetEMonthData(Long year, Long month);
	
	public List<Map<String, Object>> getBranchOnNetEYearData(Long year);
	
	public List<Map<String, Object>> getBranchOnNetEMonthData(Long year, Long month);
	
}
