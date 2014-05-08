package com.huntech.huntoms.oms.service;

import java.util.List;

import com.huntech.huntoms.oms.dto.GroupEBean;
import com.huntech.huntoms.oms.dto.OverViewBean;

/**
 * 集团统计分析Service
 * @author 张晨 
 */
public interface GroupCensusService {
	
	public OverViewBean getGroupOverviewYearData(Long year);
	
	public OverViewBean getGroupOverviewMonthData(Long year, Long month);
	
	public OverViewBean getBranchOverviewYearData(Long id,Long year);
	
	public OverViewBean getBranchOverviewMonthData(Long id,Long year, Long month);
	
	/* 获取集团年份列表 */
	public List<Long> getGroupYearConditionData();
	
	/* 获取集团年份列表 */
	public List<Long> getGroupMonthConditionData();
	
	/* 获取集团电量历年数据 */
	public GroupEBean getEOverYearData();
	
	/* 获取集团电量年数据 */
	public GroupEBean getEYearData(Long year);
	
	/* 获取集团电量约数据 */
	public GroupEBean getEMonthData(Long year, Long month);
	
	/* 获取集团发电效率历年数据 */
	public GroupEBean getEfficEOverYearData();
	
	/* 获取集团发电效率历年数据 */
	public GroupEBean getEfficEYearData(Long year);
	
	/* 获取集团发电计划执行历年数据 */
	public GroupEBean getGroupPlanEOverYearData();
	
	/* 获取集团发电计划执行年数据 */
	public GroupEBean getGroupPlanEYearData(Long year);
	
	/* 获取集团上网发电计划执行历年数据 */
	public GroupEBean getGroupPlanOnNetEOverYearData();
	
	/* 获取集团上网发电计划执行年数据 */
	public GroupEBean getGroupPlanOnNetEYearData(Long year);
	
	/* 获取集团能耗统计历年数据 */
	public GroupEBean getGroupTotalEOverYearData();
	
	/* 获取集团能耗统计年数据 */
	public GroupEBean getGroupTotalEYearData(Long year);

}
