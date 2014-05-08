package com.huntech.huntoms.oms.ctrl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huntech.huntoms.oms.dto.GroupEBean;
import com.huntech.huntoms.oms.dto.OverViewBean;
import com.huntech.huntoms.oms.dto.RankingBean;
import com.huntech.huntoms.oms.service.GroupCensusService;
import com.huntech.huntoms.oms.service.GroupContrastService;

/**
 * 集团运营分析Controller
 * @author 张晨
 */
@Controller
@RequestMapping("/group")
public class GroupOperationController {
	
	@Autowired
	private GroupCensusService groupCensusService;  // 集团统计分析Service注入
	@Autowired
	private GroupContrastService groupContrastService;  // 集团对比分析Service注入
	

	@RequestMapping(value ="/groupOverview", method = RequestMethod.GET)
	public String groupOverview() throws Exception {
		return "oms/group/groupOverview";
	}
	
	@RequestMapping(value ="/branchOverview", method = RequestMethod.GET)
	public String branchOverview() throws Exception {
		return "oms/group/branchOverview";
	}
	
	@RequestMapping(value ="/branchStationRanking", method = RequestMethod.GET)
	public String branchStationRanking() throws Exception {
		return "oms/branch/branchStationRanking";
	}
	
	/* 集团历年电量统计分析页面显示 */
	@RequestMapping(value ="/groupEOverYear", method = RequestMethod.GET)
	public String groupEOverYear() throws Exception {
		return "oms/group/groupEOverYear";
	}
	
	/* 集团年电量统计分析页面显示  */
	@RequestMapping(value ="/groupEYear", method = RequestMethod.GET)
	public String groupEYear() throws Exception {
		return "oms/group/groupEYear";
	}
	
	/* 集团年电量统计分析页面显示  */
	@RequestMapping(value ="/groupEYear/{year}", method = RequestMethod.GET)
	public String groupEYear(@PathVariable("year")Long year) throws Exception {
		return "oms/group/groupEYear";
	}
	
	/* 集团年电量统计分析页面显示  */
	@RequestMapping(value ="/groupEMonth", method = RequestMethod.GET)
	public String groupEMonth() throws Exception {
		return "oms/group/groupEMonth";
	}
	
	/* 集团年电量统计分析页面显示  */
	@RequestMapping(value ="/groupEMonth/{year}/{month}", method = RequestMethod.GET)
	public String groupEMonth(@PathVariable("year")Long year, @PathVariable("month")Long month) throws Exception {
		return "oms/group/groupEMonth";
	}
	
	/* 集团历年发电效率统计分析页面显示 */
	@RequestMapping(value ="/groupEfficEOverYear", method = RequestMethod.GET)	
	public String groupEfficOverYear() throws Exception {
		return "oms/group/groupEfficEOverYear";
	}

	/* 集团年发电效率统计分析页面显示 */
	@RequestMapping(value ="/groupEfficEYear", method = RequestMethod.GET)	
	public String groupEfficYear() throws Exception {
		return "oms/group/groupEfficEYear";
	}
	
	/* 集团年发电效率统计分析页面显示 */
	@RequestMapping(value ="/groupEfficEYear/{year}", method = RequestMethod.GET)	
	public String groupEfficYear(@PathVariable("year")Long year) throws Exception {
		return "oms/group/groupEfficEYear";
	}

	/* 集团历年发电计划执行分析页面显示 */
	@RequestMapping(value ="/groupPlanEOverYear", method = RequestMethod.GET)	
	public String groupPlanEOverYear() throws Exception {
		return "oms/group/groupPlanEOverYear";
	}

	/* 集团年发电计划执行分析页面显示 */
	@RequestMapping(value ="/groupPlanEYear", method = RequestMethod.GET)	
	public String groupPlanEYear() throws Exception {
		return "oms/group/groupPlanEYear";
	}
	
	/* 集团年发电效率统计分析页面显示 */
	@RequestMapping(value ="/groupPlanEYear/{year}", method = RequestMethod.GET)	
	public String groupPlanEYear(@PathVariable("year")Long year) throws Exception {
		return "oms/group/groupPlanEYear";
	}
	
	/* 集团历年发电计划执行分析页面显示 */
	@RequestMapping(value ="/groupPlanOnNetEOverYear", method = RequestMethod.GET)	
	public String groupPlanOnNetEOverYear() throws Exception {
		return "oms/group/groupPlanOnNetEOverYear";
	}

	/* 集团年发电计划执行分析页面显示 */
	@RequestMapping(value ="/groupPlanOnNetEYear", method = RequestMethod.GET)	
	public String groupPlanOnNetEYear() throws Exception {
		return "oms/group/groupPlanOnNetEYear";
	}
	
	/* 集团年发电效率统计分析页面显示 */
	@RequestMapping(value ="/groupPlanOnNetEYear/{year}", method = RequestMethod.GET)	
	public String groupPlanOnNetEYear(@PathVariable("year")Long year) throws Exception {
		return "oms/group/groupPlanOnNetEYear";
	}
	
	/* 集团历年能耗统计分析页面显示 */
	@RequestMapping(value ="/groupTotalEOverYear", method = RequestMethod.GET)	
	public String groupTotalEOverYear() throws Exception {
		return "oms/group/groupTotalEOverYear";
	}

	/* 集团年能耗统计分析页面显示 */
	@RequestMapping(value ="/groupTotalEYear", method = RequestMethod.GET)	
	public String groupTotalEYear() throws Exception {
		return "oms/group/groupTotalEYear";
	}
	
	/* 集团年能耗统计分析页面显示 */
	@RequestMapping(value ="/groupTotalEYear/{year}", method = RequestMethod.GET)	
	public String groupTotalEYear(@PathVariable("year")Long year) throws Exception {
		return "oms/group/groupTotalEYear";
	}
	
	/* 电站运行排名页面显示 */
	@RequestMapping(value ="/stationRanking", method = RequestMethod.GET)	
	public String stationRanking() throws Exception {
		return "oms/group/stationRanking";
	}
	
	/* 电站间厂用电量年对比分析页面显示 */
	@RequestMapping(value ="/stationFactoryEYear", method = RequestMethod.GET)	
	public String stationFactoryEYear() throws Exception {
		return "oms/group/stationFactoryEYear";
	}
	
	/* 电站间厂用电量年对比分析页面显示 */
	@RequestMapping(value ="/stationFactoryEMonth", method = RequestMethod.GET)	
	public String stationFactoryEMonth() throws Exception {
		return "oms/group/stationFactoryEMonth";
	}
	
	/* 电站间综合厂用电量年对比分析页面显示 */
	@RequestMapping(value ="/stationCompreFactoryEYear", method = RequestMethod.GET)	
	public String stationCompreFactoryEYear() throws Exception {
		return "oms/group/stationCompreFactoryEYear";
	}
	
	/* 电站间综合厂用电量年对比分析页面显示 */
	@RequestMapping(value ="/stationCompreFactoryEMonth", method = RequestMethod.GET)	
	public String stationCompreFactoryEMonth() throws Exception {
		return "oms/group/stationCompreFactoryEMonth";
	}	
	
	/* 集团历年发电计划执行分析页面显示 */
	@RequestMapping(value ="/stationPlanEYear/{year}", method = RequestMethod.GET)	
	public String stationPlanEYear(@PathVariable("year")Long year) throws Exception {
		return "oms/group/stationPlanEYear";
	}
	
	/* 集团历年发电计划执行分析页面显示 */
	@RequestMapping(value ="/stationPlanEYear", method = RequestMethod.GET)	
	public String stationPlanEYear() throws Exception {
		return "oms/group/stationPlanEYear";
	}

	/* 集团月发电计划执行分析页面显示 */
	@RequestMapping(value ="/stationPlanEMonth/{year}/{month}", method = RequestMethod.GET)	
	public String stationPlanEMonth(@PathVariable("year")Long year, @PathVariable("month")Long month) throws Exception {
		return "oms/group/stationPlanEMonth";
	}
	
	/* 集团月发电计划执行分析页面显示 */
	@RequestMapping(value ="/stationPlanEMonth", method = RequestMethod.GET)	
	public String stationPlanEMonth() throws Exception {
		return "oms/group/stationPlanEMonth";
	}
	
	/* 集团历年上网计划执行分析页面显示 */
	@RequestMapping(value ="/stationPlanOnNetEYear", method = RequestMethod.GET)	
	public String stationPlanOnNetEYear() throws Exception {
		return "oms/group/stationPlanOnNetEYear";
	}

	/* 集团月上网计划执行分析页面显示 */
	@RequestMapping(value ="/stationPlanOnNetEMonth", method = RequestMethod.GET)	
	public String stationPlanOnNetEMonth() throws Exception {
		return "oms/group/stationPlanOnNetEMonth";
	}
	
	/* 电站间年发电贡献页面显示 */
	@RequestMapping(value ="/stationDevoteEYear", method = RequestMethod.GET)	
	public String stationDevoteEYear() throws Exception {
		return "oms/group/stationDevoteEYear";
	}

	/* 电站间月发电贡献页面显示 */
	@RequestMapping(value ="/stationDevoteEMonth", method = RequestMethod.GET)	
	public String stationDevoteEMonth() throws Exception {
		return "oms/group/stationDevoteEMonth";
	}

	@RequestMapping(value = "/getGroupOverviewYearData/{year}", method = RequestMethod.GET)
    @ResponseBody
    public OverViewBean getGroupOverviewYearData(@PathVariable("year")Long year) throws Exception{  
		OverViewBean result = groupCensusService.getGroupOverviewYearData(year);
		return result;
    } 
	
	@RequestMapping(value = "/getGroupOverviewMonthData/{year}/{month}", method = RequestMethod.GET)
    @ResponseBody
    public OverViewBean getGroupOverviewMonthData(@PathVariable("year")Long year, @PathVariable("month")Long month) throws Exception{  
		OverViewBean result = groupCensusService.getGroupOverviewMonthData(year, month);
		return result;
    } 
	
	@RequestMapping(value = "/getBranchOverviewYearData/{id}/{year}", method = RequestMethod.GET)
    @ResponseBody
    public OverViewBean getBranchOverviewYearData(@PathVariable("id")Long id, @PathVariable("year")Long year) throws Exception{  
		OverViewBean result = groupCensusService.getBranchOverviewYearData(id,year);
		return result;
    } 
	
	@RequestMapping(value = "/getBranchOverviewMonthData/{id}/{year}/{month}", method = RequestMethod.GET)
    @ResponseBody
    public OverViewBean getBranchOverviewMonthData(@PathVariable("id")Long id,@PathVariable("year")Long year, @PathVariable("month")Long month) throws Exception{  
		OverViewBean result = groupCensusService.getBranchOverviewMonthData(id,year, month);
		return result;
    } 
	
	/* 集团历年电量统计分析数据获取 */
	@RequestMapping(value = "/getGroupEOverYearData", method = RequestMethod.GET)
    @ResponseBody
    public GroupEBean getGroupEOverYearData() throws Exception{  
		GroupEBean result = groupCensusService.getEOverYearData();
		return result;
    } 
	
	/* 集团年电量统计分析数据获取 */
	@RequestMapping(value = "/getGroupEYearData/{year}", method = RequestMethod.GET)
    @ResponseBody
    public GroupEBean getGroupEYearData(@PathVariable("year")Long year) throws Exception{  
		GroupEBean result = groupCensusService.getEYearData(year);
		return result;
    } 
	
	/* 集团年电量统计分析数据获取 */
	@RequestMapping(value = "/getGroupEMonthData/{year}/{month}", method = RequestMethod.GET)
    @ResponseBody
    public GroupEBean getGroupEMonthData(@PathVariable("year")Long year, @PathVariable("month")Long month) throws Exception{  
		GroupEBean result = groupCensusService.getEMonthData(year, month);
		return result;
    } 
	
	/* 集团历年发电效率统计分析数据获取 */
	@RequestMapping(value = "/getGroupEfficEOverYearData", method = RequestMethod.GET)
    @ResponseBody
	public GroupEBean getGroupEfficEOverYearData() throws Exception {
		GroupEBean result = groupCensusService.getEfficEOverYearData();
		return result;
	}

	/* 集团历年发电效率统计分析数据获取 */
	@RequestMapping(value = "/getGroupEfficEYearData/{year}", method = RequestMethod.GET)
    @ResponseBody
	public GroupEBean getGroupEfficEYearData(@PathVariable("year")Long year) throws Exception {
		GroupEBean result = groupCensusService.getEfficEYearData(year);
		return result;
	}
	
	/* 集团历年发电效率统计分析数据获取 */
	@RequestMapping(value = "/getGroupPlanEOverYearData", method = RequestMethod.GET)
    @ResponseBody
	public GroupEBean getGroupPlanEOverYearData() throws Exception {
		GroupEBean result = groupCensusService.getGroupPlanEOverYearData();
		return result;
	}
	
	/* 集团年发电效率统计分析数据获取 */
	@RequestMapping(value = "/getGroupPlanEYearData/{year}", method = RequestMethod.GET)
    @ResponseBody
	public GroupEBean getGroupPlanEYearData(@PathVariable("year")Long year) throws Exception {
		GroupEBean result = groupCensusService.getGroupPlanEYearData(year);
		return result;
	}
	
	/* 集团历年上网计划执行分析数据获取 */
	@RequestMapping(value = "/getGroupPlanOnNetEOverYearData", method = RequestMethod.GET)
    @ResponseBody
	public GroupEBean getGroupPlanOnNetEOverYearData() throws Exception {
		GroupEBean result = groupCensusService.getGroupPlanOnNetEOverYearData();
		return result;
	}
	
	/* 集团年上网计划执行分析数据获取 */
	@RequestMapping(value = "/getGroupPlanOnNetEYearData/{year}", method = RequestMethod.GET)
    @ResponseBody
	public GroupEBean getGroupPlanOnNetEYearData(@PathVariable("year")Long year) throws Exception {
		GroupEBean result = groupCensusService.getGroupPlanOnNetEYearData(year);
		return result;
	}
	
	/* 集团历年能耗统计分析数据获取 */
	@RequestMapping(value = "/getGroupTotalEOverYearData", method = RequestMethod.GET)
    @ResponseBody
	public GroupEBean getGroupTotalEOverYearData() throws Exception {
		GroupEBean result = groupCensusService.getGroupTotalEOverYearData();
		return result;
	}
	
	/* 集团年能耗统计分析数据获取 */
	@RequestMapping(value = "/getGroupTotalEYearData/{year}", method = RequestMethod.GET)
    @ResponseBody
	public GroupEBean getGroupTotalEYearData(@PathVariable("year")Long year) throws Exception {
		GroupEBean result = groupCensusService.getGroupTotalEYearData(year);
		return result;
	}
	
	/* 电站排名年数据获取 */
	@RequestMapping(value = "/getStationRankingYearData/{year}", method = RequestMethod.GET)
    @ResponseBody
	public List<RankingBean> getStationRankingYearData(@PathVariable("year")Long year) throws Exception {
		List<RankingBean> result = groupContrastService.getStationRankingYearData(year);
		return result;
	}
	
	/* 电站排名月数据获取 */
	@RequestMapping(value = "/getStationRankingMonthData/{year}/{month}", method = RequestMethod.GET)
    @ResponseBody
	public List<RankingBean> getStationRankingMonthData(@PathVariable("year")Long year, @PathVariable("month")Long month) throws Exception {
		List<RankingBean> result = groupContrastService.getStationRankingMonthData(year, month);
		return result;
	}
	
	/* 电站排名年数据获取 */
	@RequestMapping(value = "/getBranchStationRankingYearData/{id}/{year}", method = RequestMethod.GET)
    @ResponseBody
	public List<RankingBean> getBranchStationRankingYearData(@PathVariable("id")Long id, @PathVariable("year")Long year) throws Exception {
		List<RankingBean> result = groupContrastService.getBranchStationRankingYearData(id, year);
		return result;
	}
	
	/* 电站排名月数据获取 */
	@RequestMapping(value = "/getBranchStationRankingMonthData/{id}/{year}/{month}", method = RequestMethod.GET)
    @ResponseBody
	public List<RankingBean> getBranchStationRankingMonthData(@PathVariable("id")Long id, @PathVariable("year")Long year, @PathVariable("month")Long month) throws Exception {
		List<RankingBean> result = groupContrastService.getBranchStationRankingMonthData(id, year, month);
		return result;
	}
	
	/* 电站间年发电贡献数据获取 */
	@RequestMapping(value = "/getStationDevoteEYearData/{year}", method = RequestMethod.GET)
    @ResponseBody
	public GroupEBean getStationDevoteEYearData(@PathVariable("year")Long year) throws Exception {
		GroupEBean result = groupContrastService.getStationDevoteEYearData(year);
		return result;
	}
	
	/* 电站间月发电贡献数据获取 */
	@RequestMapping(value = "/getStationDevoteEMonthData/{year}/{month}", method = RequestMethod.GET)
    @ResponseBody
	public GroupEBean getStationDevoteEMonthData(@PathVariable("year")Long year, @PathVariable("month")Long month) throws Exception {
		GroupEBean result = groupContrastService.getStationDevoteEMonthData(year, month);
		return result;
	}
	
	/* 电站间厂用电量年对比分析数据获取 */
	@RequestMapping(value = "/getStationFactoryEYearData/{year}", method = RequestMethod.GET)
    @ResponseBody
	public GroupEBean getStationFactoryEYearData(@PathVariable("year")Long year) throws Exception {
		GroupEBean result = groupContrastService.getStationFactoryEYearData(year);
		return result;
	}
	
	/* 电站间厂用电量月对比分析数据获取 */
	@RequestMapping(value = "/getStationFactoryEMonthData/{year}/{month}", method = RequestMethod.GET)
    @ResponseBody
	public GroupEBean getStationFactoryEMonthData(@PathVariable("year")Long year, @PathVariable("month")Long month) throws Exception {
		GroupEBean result = groupContrastService.getStationFactoryEMonthData(year, month);
		return result;
	}
	
	/* 电站间综合厂用电量年对比分析数据获取 */
	@RequestMapping(value = "/getStationCompreFactoryEYearData/{year}", method = RequestMethod.GET)
    @ResponseBody
	public GroupEBean getStationCompreFactoryEYearData(@PathVariable("year")Long year) throws Exception {
		GroupEBean result = groupContrastService.getStationCompreFactoryEYearData(year);
		return result;
	}
	
	/* 电站间综合厂用电量年对比分析数据获取 */
	@RequestMapping(value = "/getStationCompreFactoryEMonthData/{year}/{month}", method = RequestMethod.GET)
    @ResponseBody
	public GroupEBean getStationCompreFactoryEMonthData(@PathVariable("year")Long year, @PathVariable("month")Long month) throws Exception {
		GroupEBean result = groupContrastService.getStationCompreFactoryEMonthData(year, month);
		return result;
	}
	
	/* 电站间年发电计划执行分析数据获取 */
	@RequestMapping(value = "/getStationPlanEYearData/{year}", method = RequestMethod.GET)
    @ResponseBody
	public GroupEBean getStationPlanEYearData(@PathVariable("year")Long year) throws Exception {
		GroupEBean result = groupContrastService.getStationPlanEYearData(year);
		return result;
	}
	
	/* 电站间月发电计划执行分析数据获取 */
	@RequestMapping(value = "/getStationPlanEMonthData/{year}/{month}", method = RequestMethod.GET)
    @ResponseBody
	public GroupEBean getStationPlanEMonthData(@PathVariable("year")Long year, @PathVariable("month")Long month) throws Exception {
		GroupEBean result = groupContrastService.getStationPlanEMonthData(year, month);
		return result;
	}
	
	/* 电站间年上网计划执行分析数据获取 */
	@RequestMapping(value = "/getStationPlanOnNetEYearData/{year}", method = RequestMethod.GET)
    @ResponseBody
	public GroupEBean getStationPlanOnNetEYearData(@PathVariable("year")Long year) throws Exception {
		GroupEBean result = groupContrastService.getStationPlanOnNetEYearData(year);
		return result;
	}
	
	/* 电站间月上网计划执行分析数据获取 */
	@RequestMapping(value = "/getStationPlanOnNetEMonthData/{year}/{month}", method = RequestMethod.GET)
    @ResponseBody
	public GroupEBean getStationPlanOnNetEMonthData(@PathVariable("year")Long year, @PathVariable("month")Long month) throws Exception {
		GroupEBean result = groupContrastService.getStationPlanOnNetEMonthData(year, month);
		return result;
	}
	
	@RequestMapping(value = "/getGroupYearConditionData", method = RequestMethod.GET)
    @ResponseBody
	public List<Long> getGroupYearConditionData() throws Exception {
		List<Long> result = groupCensusService.getGroupYearConditionData();
		return result;
	}
	
	@RequestMapping(value = "/getGroupMonthConditionData", method = RequestMethod.GET)
    @ResponseBody
	public List<Long> getGroupMonthConditionData() throws Exception {
		List<Long> result = groupCensusService.getGroupMonthConditionData();
		return result;
	}
	
	@RequestMapping(value = "/getStationYearConditionData", method = RequestMethod.GET)
    @ResponseBody
	public List<Long> getStationYearConditionData() throws Exception {
		List<Long> result = groupContrastService.getStationYearConditionData();
		return result;
	}
	
	@RequestMapping(value = "/getStationMonthConditionData", method = RequestMethod.GET)
    @ResponseBody
	public List<Long> getStationMonthConditionData() throws Exception {
		List<Long> result = groupContrastService.getStationMonthConditionData();
		return result;
	}
	
}
