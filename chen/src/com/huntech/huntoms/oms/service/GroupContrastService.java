package com.huntech.huntoms.oms.service;

import java.util.List;

import com.huntech.huntoms.oms.dto.GroupEBean;
import com.huntech.huntoms.oms.dto.RankingBean;

/**
 * 集团对比分析Service
 * @author 张晨 
 */
public interface GroupContrastService {
	
	public List<RankingBean> getStationRankingYearData(Long year);
	
	public List<RankingBean> getStationRankingMonthData(Long year, Long month);
	
	public List<RankingBean> getBranchStationRankingYearData(Long id, Long year);
	
	public List<RankingBean> getBranchStationRankingMonthData(Long id, Long year, Long month);

	/* 获取集团年份列表 */
	public List<Long> getStationYearConditionData();
	
	/* 获取集团年份列表 */
	public List<Long> getStationMonthConditionData();
	
	/* 获取电站间厂用电量年数据 */
	public GroupEBean getStationDevoteEYearData(Long year);
	
	/* 获取电站间厂用电量年数据 */
	public GroupEBean getStationDevoteEMonthData(Long year, Long month);
	
	/* 获取电站间厂用电量年数据 */
	public GroupEBean getStationFactoryEYearData(Long year);
	
	/* 获取电站间厂用电量年数据 */
	public GroupEBean getStationFactoryEMonthData(Long year, Long month);

	/* 获取电站间综合厂用电量年数据 */
	public GroupEBean getStationCompreFactoryEYearData(Long year);
	
	/* 获取电站间综合厂用电量月数据 */
	public GroupEBean getStationCompreFactoryEMonthData(Long year, Long month);
	
	/* 获取电站间发电计划执行年数据 */
	public GroupEBean getStationPlanEYearData(Long year);
	
	/* 获取电站间发电计划执行月数据 */
	public GroupEBean getStationPlanEMonthData(Long year, Long month);
	
	/* 获取电站间上网计划执行年数据 */
	public GroupEBean getStationPlanOnNetEYearData(Long year);
	
	/* 获取电站间上网计划执行月数据 */
	public GroupEBean getStationPlanOnNetEMonthData(Long year, Long month);
	
	public GroupEBean getBranchOnNetEYearData(Long year);
	
	public GroupEBean getBranchOnNetEMonthData(Long year, Long month);
	
}
