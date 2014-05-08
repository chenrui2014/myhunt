package com.huntech.huntoms.oms.service.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huntech.huntoms.oms.dto.ChartBean;
import com.huntech.huntoms.oms.dto.DataBean;
import com.huntech.huntoms.oms.dto.GroupEBean;
import com.huntech.huntoms.oms.dto.OraganDataBean;
import com.huntech.huntoms.oms.dto.OverViewBean;
import com.huntech.huntoms.oms.dto.RankingBean;
import com.huntech.huntoms.oms.dto.RankingDataBean;
import com.huntech.huntoms.oms.repository.GroupCensusJdbc;
import com.huntech.huntoms.oms.repository.GroupContrastJdbc;
import com.huntech.huntoms.oms.service.GroupCensusService;

@Service
@Transactional(readOnly = true)
public class GroupCensusServiceImpl implements GroupCensusService {

	@Autowired
	private GroupCensusJdbc groupCensusJdbc;
	@Autowired
	private GroupContrastJdbc groupContrastJdbc;
	
	@Override
	public List<Long> getGroupYearConditionData() {
		ArrayList<Long> result = new ArrayList<Long>();
		List<Map<String, Object>> queryList = groupCensusJdbc.getGroupYearConditionData();  // 获取查询结果
		Iterator<Map<String, Object>> ite = queryList.iterator(); 
		while (ite.hasNext()) { 
			Map<String, Object> item = ite.next();
			for (String key : item.keySet()) {
				if (key.equals("GYEAR") && item.get(key)!=null) {
					if (!result.contains(Long.valueOf((item.get(key).toString())))) {
						result.add(Long.valueOf(item.get(key).toString()));
					}
				} else if (key.equals("PYEAR") && item.get(key)!=null) {
					if (!result.contains(Long.valueOf((item.get(key).toString())))) {
						result.add(Long.valueOf(item.get(key).toString()));
					}
				}
			}
		}
		Collections.sort(result);
		return result;
	}
	
	public List<Long> getGroupMonthConditionData() {
		ArrayList<Long> result = new ArrayList<Long>();
		List<Map<String, Object>> queryList = groupCensusJdbc.getGroupMonthConditionData();  // 获取查询结果
		Iterator<Map<String, Object>> ite = queryList.iterator(); 
		while (ite.hasNext()) { 
			Map<String, Object> item = ite.next();
			for (String key : item.keySet()) {
				if (key.equals("YEAR") && item.get(key)!=null) {
					if (!result.contains(Long.valueOf((item.get(key).toString())))) {
						result.add(Long.valueOf(item.get(key).toString()));
					}
				} 
			}
		}
		Collections.sort(result);
		return result;
	}
	
	@Override
	public GroupEBean getEOverYearData() {
		List<Map<String, Object>> queryList = groupCensusJdbc.getEOverYearData();  // 获取查询结果
		GroupEBean result = new GroupEBean();  // 图表Bean
		List<String> xAxisCategories = new ArrayList<String>();  // x刻度列表
		List<DataBean> dataBeanList = new ArrayList<DataBean>();  // 数据Bean列表
		DataBean planEBean = new DataBean();  // 计划发电量Bean
		planEBean.setDataType("column");
		planEBean.setyAxis(0);
		planEBean.setDataName("计划发电量");
		DataBean theoryEBean = new DataBean();  // 理论发电量Bean
		theoryEBean.setDataType("column");
		theoryEBean.setyAxis(0);
		theoryEBean.setDataName("理论发电量");
		DataBean eBean = new DataBean();  // 实际发电量Bean
		eBean.setDataType("column");
		eBean.setyAxis(0);
		eBean.setDataName("实际发电量");
		DataBean onNetEBean = new DataBean();  // 上网电量Bean
		onNetEBean.setDataType("column");
		onNetEBean.setyAxis(0);
		onNetEBean.setDataName("上网电量");
		result.setDataBeanList(dataBeanList);
		DecimalFormat df = new DecimalFormat("0.00");
		Iterator<Map<String, Object>> ite = queryList.iterator(); 
		while (ite.hasNext()) { 
			Map<String, Object> item = ite.next();
			for (String key : item.keySet()) {
				if (key.equals("ORGAN_NAME") && result.getTitle() == null) {
					result.setTitle(item.get(key).toString() + "历年电量统计分析");
				} else if (key.equals("YEAR")) {
					String year = item.get(key).toString(); 
					xAxisCategories.add(year);
				}  else if (key.equals("PLANE")) {
					if (item.get(key) != null) {
						BigDecimal planE = new BigDecimal(df.format(item.get(key))); 
						planEBean.addData(planE);	
					} else {
						planEBean.addData(new BigDecimal(0));	
					}
				} else if (key.equals("THEORYE")) {
					if (item.get(key) != null) {
						BigDecimal theoryE = new BigDecimal(df.format(item.get(key))); 
						theoryEBean.addData(theoryE);	
					} else {
						theoryEBean.addData(new BigDecimal(0));	
					}
				} else if (key.equals("E")) {
					if (item.get(key) != null) {
						BigDecimal e = new BigDecimal(df.format(item.get(key))); 
						eBean.addData(e);	
					} else {
						eBean.addData(new BigDecimal(0));	
					}
				} else if (key.equals("ONNETE")) {
					if (item.get(key) != null) {
						BigDecimal onNetE = new BigDecimal(df.format(item.get(key))); 
						onNetEBean.addData(onNetE);	
					} else {
						onNetEBean.addData(new BigDecimal(0));	
					}
				}
			}
		}
		dataBeanList.add(planEBean);
		dataBeanList.add(theoryEBean);
		dataBeanList.add(eBean);
		dataBeanList.add(onNetEBean);
		result.setDataBeanList(dataBeanList);
		result.setxAxisCateGories(xAxisCategories);
		return result;
	}

	@Override
	public GroupEBean getEYearData(Long year) {
		List<Map<String, Object>> queryList = groupCensusJdbc.getEYearData(year); // 获取查询结果
		GroupEBean result = new GroupEBean();  // 图表Bean
		List<String> xAxisCategories = new ArrayList<String>();  // x刻度列表
		List<DataBean> dataBeanList = new ArrayList<DataBean>();  // 数据Bean列表
		DataBean planEBean = new DataBean();  // 计划发电量Bean
		planEBean.setDataType("column");
		planEBean.setyAxis(0);
		planEBean.setDataName("计划发电量");
		DataBean theoryEBean = new DataBean();  // 理论发电量Bean
		theoryEBean.setDataType("column");
		theoryEBean.setyAxis(0);
		theoryEBean.setDataName("理论发电量");
		theoryEBean.setVisible(false);
		DataBean eBean = new DataBean();  // 实际发电量Bean
		eBean.setDataType("column");
		eBean.setyAxis(0);
		eBean.setDataName("实际发电量");
		DataBean onNetEBean = new DataBean();  // 上网电量Bean
		onNetEBean.setDataType("column");
		onNetEBean.setyAxis(0);
		onNetEBean.setDataName("上网电量");
		onNetEBean.setVisible(false);
		result.setDataBeanList(dataBeanList);
		DecimalFormat df = new DecimalFormat("0.00");
		Iterator<Map<String, Object>> ite = queryList.iterator(); 
		while (ite.hasNext()) { 
			Map<String, Object> item = ite.next();
			for (String key : item.keySet()) {
				if (key.equals("ORGAN_NAME") && result.getTitle() == null) {
					result.setTitle(item.get(key).toString() + year + "年电量统计分析");
				} else if (key.equals("MONTH")) {
					String month = item.get(key).toString(); 
					xAxisCategories.add(month);
				} else if (key.equals("PLANE")) {
					if (item.get(key) != null) {
						BigDecimal planE = new BigDecimal(df.format(item.get(key))); 
						planEBean.addData(planE);	
					} else {
						planEBean.addData(new BigDecimal(0));	
					}
				} else if (key.equals("THEORYE")) {
					if (item.get(key) != null) {
						BigDecimal theoryE = new BigDecimal(df.format(item.get(key))); 
						theoryEBean.addData(theoryE);	
					}
				} else if (key.equals("E")) {
					if (item.get(key) != null) {
						BigDecimal e = new BigDecimal(df.format(item.get(key))); 
						eBean.addData(e);	
					} else {
						eBean.addData(new BigDecimal(0));	
					}
				} else if (key.equals("ONNETE")) {
					if (item.get(key) != null) {
						BigDecimal onNetE = new BigDecimal(df.format(item.get(key))); 
						onNetEBean.addData(onNetE);	
					} else {
						onNetEBean.addData(new BigDecimal(0));	
					}
				}
			}
		}
		dataBeanList.add(planEBean);
		dataBeanList.add(theoryEBean);
		dataBeanList.add(eBean);
		dataBeanList.add(onNetEBean);
		result.setDataBeanList(dataBeanList);
		result.setxAxisCateGories(xAxisCategories);
		result.setYear(year.toString());
		return result;
	}

	@Override
	public GroupEBean getEMonthData(Long year, Long month) {
		List<Map<String, Object>> queryList = groupCensusJdbc.getEMonthData(year, month); // 获取查询结果
		GroupEBean result = new GroupEBean();  // 图表Bean
		List<String> xAxisCategories = new ArrayList<String>();  // x刻度列表
		List<DataBean> dataBeanList = new ArrayList<DataBean>();  // 数据Bean列表
		DataBean eBean = new DataBean();  // 实际发电量Bean
		eBean.setDataType("line");
		eBean.setyAxis(0);
		eBean.setDataName("实际发电量");
		DataBean onNetEBean = new DataBean();  // 上网电量Bean
		onNetEBean.setDataType("line");
		onNetEBean.setyAxis(0);
		onNetEBean.setDataName("上网电量");
		result.setDataBeanList(dataBeanList);
		DecimalFormat df = new DecimalFormat("0.00");
		Iterator<Map<String, Object>> ite = queryList.iterator(); 
		while (ite.hasNext()) { 
			Map<String, Object> item = ite.next();
			System.out.println(item.values());
			for (String key : item.keySet()) {
				if (key.equals("ORGAN_NAME") && result.getTitle() == null) {
					result.setTitle(item.get(key).toString() + year + "年" + month + "月电量统计分析");
				} else if (key.equals("DAY")) {
					String day = item.get(key).toString(); 
					xAxisCategories.add(day);
				} else if (key.equals("E")) {
					if (item.get(key) != null) {
						BigDecimal e = new BigDecimal(df.format(item.get(key))); 
						eBean.addData(e);	
					} else {
						eBean.addData(new BigDecimal(0));	
					}
				} else if (key.equals("ONNETE")) {
					if (item.get(key) != null) {
						BigDecimal onNetE = new BigDecimal(df.format(item.get(key))); 
						onNetEBean.addData(onNetE);	
					} else {
						onNetEBean.addData(new BigDecimal(0));	
					}
				}
			}
		}
		dataBeanList.add(eBean);
		dataBeanList.add(onNetEBean);
		result.setxAxisCateGories(xAxisCategories);
		result.setDataBeanList(dataBeanList);
		result.setYear(year.toString());
		result.setMonth(month.toString());
		return result;
	}

	@Override
	public GroupEBean getEfficEOverYearData() {
		List<Map<String, Object>> queryList = groupCensusJdbc.getEfficEOverYearData();  // 获取查询结果
		GroupEBean result = new GroupEBean();  // 图表Bean
		List<String> xAxisCategories = new ArrayList<String>();  // x刻度列表
		List<DataBean> dataBeanList = new ArrayList<DataBean>();  // 数据Bean列表
		DataBean singleMWEBean = new DataBean();  // 单兆瓦发电量Bean
		singleMWEBean.setDataType("column");
		singleMWEBean.setyAxis(0);
		singleMWEBean.setDataName("单兆瓦发电量");
		result.setDataBeanList(dataBeanList);
		DataBean efficEBean = new DataBean();  // 发电效率Bean
		efficEBean.setDataType("line");
		efficEBean.setyAxis(1);
		efficEBean.setDataName("发电效率");
		DecimalFormat df = new DecimalFormat("0.00");
		Iterator<Map<String, Object>> ite = queryList.iterator(); 
		while (ite.hasNext()) { 
			Map<String, Object> item = ite.next();
			for (String key : item.keySet()) {
				if (key.equals("ORGAN_NAME") && result.getTitle() == null) {
					result.setTitle(item.get(key).toString() + "历年发电效率分析");
				} else if (key.equals("YEAR")) {
					String year = item.get(key).toString(); 
					xAxisCategories.add(year);
				} else if (key.equals("SINGLEMWE")) {
					if (item.get(key) != null) {
						BigDecimal singleMWE = new BigDecimal(df.format(item.get(key))); 
						singleMWEBean.addData(singleMWE);	
					} else {
						singleMWEBean.addData(new BigDecimal(0));	
					}
				} else if (key.equals("EFFICE")) {
					if (item.get(key) != null) {
						BigDecimal efficE = new BigDecimal(df.format(item.get(key))); 
						efficEBean.addData(efficE);	
					} else {
						efficEBean.addData(new BigDecimal(0));	
					}
				} 
			}
		}
		dataBeanList.add(singleMWEBean);
		dataBeanList.add(efficEBean);
		result.setxAxisCateGories(xAxisCategories);
		result.setDataBeanList(dataBeanList);
		return result;
	}

	@Override
	public GroupEBean getEfficEYearData(Long year) {
		List<Map<String, Object>> queryList = groupCensusJdbc.getEfficEYearData(year);  // 获取查询结果
		GroupEBean result = new GroupEBean();  // 图表Bean
		List<String> xAxisCategories = new ArrayList<String>();  // x刻度列表
		List<DataBean> dataBeanList = new ArrayList<DataBean>();  // 数据Bean列表
		DataBean singleMWEBean = new DataBean();  // 单兆瓦发电量Bean
		singleMWEBean.setDataType("column");
		singleMWEBean.setyAxis(0);
		singleMWEBean.setDataName("单兆瓦发电量");
		DataBean efficEBean = new DataBean();  // 发电效率Bean
		efficEBean.setDataType("line");
		efficEBean.setyAxis(1);
		efficEBean.setDataName("发电效率");
		result.setDataBeanList(dataBeanList);
		DecimalFormat df = new DecimalFormat("0.00");
		Iterator<Map<String, Object>> ite = queryList.iterator(); 
		while (ite.hasNext()) { 
			Map<String, Object> item = ite.next();
			for (String key : item.keySet()) {
				if (key.equals("ORGAN_NAME") && result.getTitle() == null) {
					result.setTitle(item.get(key).toString() + year + "年发电效率分析");
				} else if (key.equals("MONTH")) {
					String month = item.get(key).toString(); 
					xAxisCategories.add(month);
				} else if (key.equals("SINGLEMWE")) {
					if (item.get(key) != null) {
						BigDecimal singleMWE = new BigDecimal(df.format(item.get(key))); 
						singleMWEBean.addData(singleMWE);	
					} else {
						singleMWEBean.addData(new BigDecimal(0));	
					}
				} else if (key.equals("EFFICE")) {
					if (item.get(key) != null) {
						BigDecimal efficE = new BigDecimal(df.format(item.get(key))); 
						efficEBean.addData(efficE);	
					} else {
						efficEBean.addData(new BigDecimal(0));	
					}
				}
			}
		}
		dataBeanList.add(singleMWEBean);
		dataBeanList.add(efficEBean);
		result.setxAxisCateGories(xAxisCategories);
		result.setDataBeanList(dataBeanList);
		result.setYear(year.toString());
		return result;
	}

	@Override
	public GroupEBean getGroupPlanEOverYearData() {
		List<Map<String, Object>> queryList = groupCensusJdbc.getPlanEOverYearData();  // 获取查询结果
		GroupEBean result = new GroupEBean();  // 图表Bean
		List<String> xAxisCategories = new ArrayList<String>();  // x刻度列表
		List<DataBean> dataBeanList = new ArrayList<DataBean>();  // 数据Bean列表		
		DataBean planEBean = new DataBean();  // 计划发电量Bean
		planEBean.setDataType("column");
		planEBean.setyAxis(0);
		planEBean.setDataName("计划发电量");
		DataBean eBean = new DataBean();  // 发电量Bean
		eBean.setDataType("column");
		eBean.setyAxis(0);
		eBean.setDataName("发电量");
		DataBean planCompleteBean = new DataBean();  // 计划完成率Bean
		planCompleteBean.setDataType("line");
		planCompleteBean.setyAxis(1);
		planCompleteBean.setDataName("计划完成率");
		result.setDataBeanList(dataBeanList);
		DecimalFormat df = new DecimalFormat("0.00");
		Iterator<Map<String, Object>> ite = queryList.iterator(); 
		while (ite.hasNext()) { 
			Map<String, Object> item = ite.next();
			for (String key : item.keySet()) {
				if (key.equals("ORGAN_NAME") && result.getTitle() == null) {
					result.setTitle(item.get(key).toString() + "历年发电计划执行分析");
				} else if (key.equals("YEAR")) {
					String year = item.get(key).toString(); 
					xAxisCategories.add(year);
				} else if (key.equals("PLANE")) {
					if (item.get(key) != null) {
						BigDecimal planE = new BigDecimal(df.format(item.get(key))); 
						planEBean.addData(planE);	
					} else {
						planEBean.addData(new BigDecimal(0));	
					}
				} else if (key.equals("E")) {
					if (item.get(key) != null) {
						BigDecimal e = new BigDecimal(df.format(item.get(key))); 
						eBean.addData(e);	
					} else {
						eBean.addData(new BigDecimal(0));	
					}
				} else if (key.equals("PLANCOMPLETE")) {
					if (item.get(key) != null) {
						BigDecimal planeComplete = new BigDecimal(df.format(item.get(key))); 
						planCompleteBean.addData(planeComplete);	
					} else {
						planCompleteBean.addData(new BigDecimal(0));	
					}
				}
			}
		}
		dataBeanList.add(planEBean);
		dataBeanList.add(eBean);
		dataBeanList.add(planCompleteBean);
		result.setxAxisCateGories(xAxisCategories);
		result.setDataBeanList(dataBeanList);
		return result;
	}

	@Override
	public GroupEBean getGroupPlanEYearData(Long year) {
		List<Map<String, Object>> queryList = groupCensusJdbc.getPlanEYearData(year);  // 获取查询结果
		GroupEBean result = new GroupEBean();  // 图表Bean
		List<String> xAxisCategories = new ArrayList<String>();  // x刻度列表
		List<DataBean> dataBeanList = new ArrayList<DataBean>();  // 数据Bean列表
		DataBean planEBean = new DataBean();  // 计划发电量Bean
		planEBean.setDataType("column");
		planEBean.setyAxis(0);
		planEBean.setDataName("计划发电量");
		DataBean eBean = new DataBean();  // 发电量Bean
		eBean.setDataType("column");
		eBean.setyAxis(0);
		eBean.setDataName("发电量");
		DataBean planCompleteBean = new DataBean();  // 计划完成率Bean
		planCompleteBean.setDataType("line");
		planCompleteBean.setyAxis(1);
		planCompleteBean.setDataName("计划完成率");
		result.setDataBeanList(dataBeanList);
		DecimalFormat df = new DecimalFormat("0.00");
		Iterator<Map<String, Object>> ite = queryList.iterator(); 
		while (ite.hasNext()) { 
			Map<String, Object> item = ite.next();
			for (String key : item.keySet()) {
				if (key.equals("ORGAN_NAME") && result.getTitle() == null) {
					result.setTitle(item.get(key).toString() + year + "年发电计划执行分析");
				} else if (key.equals("MONTH")) {
					String month = item.get(key).toString(); 
					xAxisCategories.add(month);
				} else if (key.equals("PLANE")) {
					if (item.get(key) != null) {
						BigDecimal planE = new BigDecimal(df.format(item.get(key))); 
						planEBean.addData(planE);	
					} else {
						planEBean.addData(new BigDecimal(0));	
					}
				} else if (key.equals("E")) {
					if (item.get(key) != null) {
						BigDecimal e = new BigDecimal(df.format(item.get(key))); 
						eBean.addData(e);	
					} else {
						eBean.addData(new BigDecimal(0));	
					}
				} else if (key.equals("PLANCOMPLETE")) {
					if (item.get(key) != null) {
						BigDecimal planeComplete = new BigDecimal(df.format(item.get(key))); 
						planCompleteBean.addData(planeComplete);	
					} else {
						planCompleteBean.addData(new BigDecimal(0));	
					}
				}
			}
		}
		dataBeanList.add(planEBean);
		dataBeanList.add(eBean);
		dataBeanList.add(planCompleteBean);
		result.setxAxisCateGories(xAxisCategories);
		result.setDataBeanList(dataBeanList);
		result.setYear(year.toString());
		return result;
	}

	@Override
	public GroupEBean getGroupPlanOnNetEOverYearData() {
		List<Map<String, Object>> queryList = groupCensusJdbc.getPlanOnNetEOverYearData();  // 获取查询结果
		GroupEBean result = new GroupEBean();  // 图表Bean
		List<String> xAxisCategories = new ArrayList<String>();  // x刻度列表
		List<DataBean> dataBeanList = new ArrayList<DataBean>();  // 数据Bean列表
		DataBean planOnNetEBean = new DataBean();  // 计划发电量Bean
		planOnNetEBean.setDataType("column");
		planOnNetEBean.setyAxis(0);
		planOnNetEBean.setDataName("计划上网电量");
		DataBean onNetEBean = new DataBean();  // 发电量Bean
		onNetEBean.setDataType("column");
		onNetEBean.setyAxis(0);
		onNetEBean.setDataName("上网电量");
		DataBean planCompleteBean = new DataBean();  // 计划完成率Bean
		planCompleteBean.setDataType("line");
		planCompleteBean.setyAxis(1);
		planCompleteBean.setDataName("计划完成率");
		result.setDataBeanList(dataBeanList);
		DecimalFormat df = new DecimalFormat("0.00");
		Iterator<Map<String, Object>> ite = queryList.iterator(); 
		while (ite.hasNext()) { 
			Map<String, Object> item = ite.next();
			for (String key : item.keySet()) {
				if (key.equals("ORGAN_NAME") && result.getTitle() == null) {
					result.setTitle(item.get(key).toString() + "历年上网计划执行分析");
				} else if (key.equals("YEAR")) {
					String year = item.get(key).toString(); 
					xAxisCategories.add(year);
				} else if (key.equals("PLANONNETE")) {
					if (item.get(key) != null) {
						BigDecimal planE = new BigDecimal(df.format(item.get(key))); 
						planOnNetEBean.addData(planE);	
					} else {
						planOnNetEBean.addData(new BigDecimal(0));	
					}
				} else if (key.equals("ONNETE")) {
					if (item.get(key) != null) {
						BigDecimal e = new BigDecimal(df.format(item.get(key))); 
						onNetEBean.addData(e);	
					} else {
						onNetEBean.addData(new BigDecimal(0));	
					}
				} else if (key.equals("PLANCOMPLETE")) {
					if (item.get(key) != null) {
						BigDecimal planeComplete = new BigDecimal(df.format(item.get(key))); 
						planCompleteBean.addData(planeComplete);	
					} else {
						planCompleteBean.addData(new BigDecimal(0));	
					}
				}
			}
		}
		dataBeanList.add(planOnNetEBean);
		dataBeanList.add(onNetEBean);
		dataBeanList.add(planCompleteBean);
		result.setxAxisCateGories(xAxisCategories);
		result.setDataBeanList(dataBeanList);
		return result;
	}

	@Override
	public GroupEBean getGroupPlanOnNetEYearData(Long year) {
		List<Map<String, Object>> queryList = groupCensusJdbc.getPlanOnNetEYearData(year);  // 获取查询结果
		GroupEBean result = new GroupEBean();  // 图表Bean
		List<String> xAxisCategories = new ArrayList<String>();  // x刻度列表
		List<DataBean> dataBeanList = new ArrayList<DataBean>();  // 数据Bean列表
		DataBean planOnNetEBean = new DataBean();  // 计划发电量Bean
		planOnNetEBean.setDataType("column");
		planOnNetEBean.setyAxis(0);
		planOnNetEBean.setDataName("计划上网电量");
		DataBean onNetEBean = new DataBean();  // 发电量Bean
		onNetEBean.setDataType("column");
		onNetEBean.setyAxis(0);
		onNetEBean.setDataName("上网电量");
		DataBean planCompleteBean = new DataBean();  // 计划完成率Bean
		planCompleteBean.setDataType("line");
		planCompleteBean.setyAxis(1);
		planCompleteBean.setDataName("计划完成率");
		result.setDataBeanList(dataBeanList);
		DecimalFormat df = new DecimalFormat("0.00");
		Iterator<Map<String, Object>> ite = queryList.iterator(); 
		while (ite.hasNext()) { 
			Map<String, Object> item = ite.next();
			for (String key : item.keySet()) {
				if (key.equals("ORGAN_NAME") && result.getTitle() == null) {
					result.setTitle(item.get(key).toString() + year + "年上网计划执行分析");
				} else if (key.equals("MONTH")) {
					String month = item.get(key).toString(); 
					xAxisCategories.add(month);
				} else if (key.equals("PLANONNETE")) {
					if (item.get(key) != null) {
						BigDecimal planE = new BigDecimal(df.format(item.get(key))); 
						planOnNetEBean.addData(planE);	
					} else {
						planOnNetEBean.addData(new BigDecimal(0));	
					}
				} else if (key.equals("ONNETE")) {
					if (item.get(key) != null) {
						BigDecimal e = new BigDecimal(df.format(item.get(key))); 
						onNetEBean.addData(e);	
					} else {
						onNetEBean.addData(new BigDecimal(0));	
					}
				} else if (key.equals("PLANCOMPLETE")) {
					if (item.get(key) != null) {
						BigDecimal planeComplete = new BigDecimal(df.format(item.get(key))); 
						planCompleteBean.addData(planeComplete);	
					} else {
						planCompleteBean.addData(new BigDecimal(0));	
					}
				}
			}
		}
		dataBeanList.add(planOnNetEBean);
		dataBeanList.add(onNetEBean);
		dataBeanList.add(planCompleteBean);
		result.setxAxisCateGories(xAxisCategories);
		result.setDataBeanList(dataBeanList);
		result.setYear(year.toString());
		return result;
	}

	@Override
	public GroupEBean getGroupTotalEOverYearData() {
		List<Map<String, Object>> queryList = groupCensusJdbc.getTotalEOverYearData();  // 获取查询结果
		GroupEBean result = new GroupEBean();  // 图表Bean
		List<String> xAxisCategories = new ArrayList<String>();  // x刻度列表
		List<DataBean> dataBeanList = new ArrayList<DataBean>();  // 数据Bean列表
		DataBean factoryEBean = new DataBean();  // 厂用电量
		factoryEBean.setDataType("column");
		factoryEBean.setyAxis(0);
		factoryEBean.setDataName("厂用电量");
		DataBean compreFactoryEBean = new DataBean();  // 综合厂用电量
		compreFactoryEBean.setDataType("column");
		compreFactoryEBean.setyAxis(0);
		compreFactoryEBean.setDataName("综合厂用电量");
		DataBean factoryEScaleBean = new DataBean();  // 厂用电率
		factoryEScaleBean.setDataType("line");
		factoryEScaleBean.setyAxis(1);
		factoryEScaleBean.setDataName("厂用电率");
		DataBean compreFactoryEScaleBean = new DataBean();  // 综合厂用电率
		compreFactoryEScaleBean.setDataType("line");
		compreFactoryEScaleBean.setyAxis(1);
		compreFactoryEScaleBean.setDataName("综合厂用电率");
		result.setDataBeanList(dataBeanList);
		DecimalFormat df = new DecimalFormat("0.00");
		Iterator<Map<String, Object>> ite = queryList.iterator(); 
		while (ite.hasNext()) { 
			Map<String, Object> item = ite.next();
			for (String key : item.keySet()) {
				if (key.equals("ORGAN_NAME") && result.getTitle() == null) {
					result.setTitle(item.get(key).toString() + "历年能耗指标分析");
				} else if (key.equals("YEAR")) {
					String year = item.get(key).toString(); 
					xAxisCategories.add(year);
				} else if (key.equals("FACTORYE")) {
					if (item.get(key) != null) {
						BigDecimal factoryE = new BigDecimal(df.format(item.get(key))); 
						factoryEBean.addData(factoryE);	
					} else {
						factoryEBean.addData(new BigDecimal(0));	
					}
				} else if (key.equals("COMPREFACTORYE")) {
					if (item.get(key) != null) {
						BigDecimal compreFactoryE = new BigDecimal(df.format(item.get(key))); 
						compreFactoryEBean.addData(compreFactoryE);	
					} else {
						compreFactoryEBean.addData(new BigDecimal(0));	
					}
				} else if (key.equals("FACTORYESCALE")) {
					if (item.get(key) != null) {
						BigDecimal factoryEScale = new BigDecimal(df.format(item.get(key))); 
						factoryEScaleBean.addData(factoryEScale);	
					} else {
						factoryEScaleBean.addData(new BigDecimal(0));	
					}
				} else if (key.equals("COMPREFACTORYESCALE")) {
					if (item.get(key) != null) {
						BigDecimal compreFactoryEScale = new BigDecimal(df.format(item.get(key))); 
						compreFactoryEScaleBean.addData(compreFactoryEScale);	
					} else {
						compreFactoryEScaleBean.addData(new BigDecimal(0));	
					}
				}
			}
		}
		dataBeanList.add(factoryEBean);
		dataBeanList.add(compreFactoryEBean);
		dataBeanList.add(factoryEScaleBean);
		dataBeanList.add(compreFactoryEScaleBean);
		result.setxAxisCateGories(xAxisCategories);
		result.setDataBeanList(dataBeanList);
		return result;
	}

	@Override
	public GroupEBean getGroupTotalEYearData(Long year) {
		List<Map<String, Object>> queryList = groupCensusJdbc.getTotalEYearData(year);  // 获取查询结果
		GroupEBean result = new GroupEBean();  // 图表Bean
		List<String> xAxisCategories = new ArrayList<String>();  // x刻度列表
		List<DataBean> dataBeanList = new ArrayList<DataBean>();  // 数据Bean列表
		DataBean factoryEBean = new DataBean();  // 厂用电量
		factoryEBean.setDataType("column");
		factoryEBean.setyAxis(0);
		factoryEBean.setDataName("厂用电量");
		DataBean compreFactoryEBean = new DataBean();  // 综合厂用电量
		compreFactoryEBean.setDataType("column");
		compreFactoryEBean.setyAxis(0);
		compreFactoryEBean.setDataName("综合厂用电量");
		DataBean factoryEScaleBean = new DataBean();  // 厂用电率
		factoryEScaleBean.setDataType("line");
		factoryEScaleBean.setyAxis(1);
		factoryEScaleBean.setDataName("厂用电率");
		DataBean compreFactoryEScaleBean = new DataBean();  // 综合厂用电率
		compreFactoryEScaleBean.setDataType("line");
		compreFactoryEScaleBean.setyAxis(1);
		compreFactoryEScaleBean.setDataName("综合厂用电率");
		result.setDataBeanList(dataBeanList);
		DecimalFormat df = new DecimalFormat("0.00");
		Iterator<Map<String, Object>> ite = queryList.iterator(); 
		while (ite.hasNext()) { 
			Map<String, Object> item = ite.next();
			for (String key : item.keySet()) {
				if (key.equals("ORGAN_NAME") && result.getTitle() == null) {
					result.setTitle(item.get(key).toString() + year + "年能耗指标分析");
				} else if (key.equals("MONTH")) {
					String month = item.get(key).toString(); 
					xAxisCategories.add(month);
				} else if (key.equals("FACTORYE")) {
					if (item.get(key) != null) {
						BigDecimal factoryE = new BigDecimal(df.format(item.get(key))); 
						factoryEBean.addData(factoryE);	
					} else {
						factoryEBean.addData(new BigDecimal(0));	
					}
				} else if (key.equals("COMPREFACTORYE")) {
					if (item.get(key) != null) {
						BigDecimal compreFactoryE = new BigDecimal(df.format(item.get(key))); 
						compreFactoryEBean.addData(compreFactoryE);	
					} else {
						compreFactoryEBean.addData(new BigDecimal(0));	
					}
				} else if (key.equals("FACTORYESCALE")) {
					if (item.get(key) != null) {
						BigDecimal factoryEScale = new BigDecimal(df.format(item.get(key))); 
						factoryEScaleBean.addData(factoryEScale);	
					} else {
						factoryEScaleBean.addData(new BigDecimal(0));	
					}
				} else if (key.equals("COMPREFACTORYESCALE")) {
					if (item.get(key) != null) {
						BigDecimal compreFactoryEScale = new BigDecimal(df.format(item.get(key))); 
						compreFactoryEScaleBean.addData(compreFactoryEScale);	
					} else {
						compreFactoryEScaleBean.addData(new BigDecimal(0));	
					}
				}
			}
		}
		dataBeanList.add(factoryEBean);
		dataBeanList.add(compreFactoryEBean);
		dataBeanList.add(factoryEScaleBean);
		dataBeanList.add(compreFactoryEScaleBean);
		result.setxAxisCateGories(xAxisCategories);
		result.setDataBeanList(dataBeanList);
		result.setYear(year.toString());
		return result;
	}

	@Override
	public OverViewBean getGroupOverviewYearData(Long year) {
		OverViewBean result = new OverViewBean();
		List<Map<String, Object>> organQueryList = groupCensusJdbc.getGroupOrganData(year);  // 获取查询结果
		List<Map<String, Object>> chartQueryList = groupCensusJdbc.getEYearData(year);  // 获取查询结果
		List<Map<String, Object>> chartQueryList1 = groupContrastJdbc.getBranchOnNetEYearData(year);  // 获取查询结果
		List<Map<String, Object>> rankingQueryList = groupContrastJdbc.getStationRankingYearData(year);  // 获取查询结果
		OraganDataBean organData = new OraganDataBean();
		List<ChartBean> chartData = new ArrayList<ChartBean>();
		List<RankingBean> rankingData = new ArrayList<RankingBean>();
		DecimalFormat df = new DecimalFormat("0.00");
		Iterator<Map<String, Object>> ite = organQueryList.iterator(); 
		while (ite.hasNext()) { 
			Map<String, Object> item = ite.next();
			for (String key : item.keySet()) {
				if (key.equals("CAPACITY")) {
					if (item.get(key) != null) {
						BigDecimal capacity = new BigDecimal(df.format(item.get(key))); 
						organData.setCapacity(capacity);
					} else {
						organData.setCapacity(new BigDecimal(0));	
					}
				} else if (key.equals("PLANCAPACITY")) {
					if (item.get(key) != null) {
						BigDecimal planCapacity = new BigDecimal(df.format(item.get(key))); 
						organData.setPlanCapacity(planCapacity);
					} else {
						organData.setPlanCapacity(new BigDecimal(0));	
					}
				} else if (key.equals("EFFICE")) {
					if (item.get(key) != null) {
						BigDecimal effice = new BigDecimal(df.format(item.get(key))); 
						organData.setEfficE(effice);
					} else {
						organData.setPlanCapacity(new BigDecimal(0));	
					}
				} else if (key.equals("E")) {
					if (item.get(key) != null) {
						BigDecimal e = new BigDecimal(df.format(item.get(key))); 
						organData.setE(e);
					} else {
						organData.setPlanCapacity(new BigDecimal(0));	
					}
				} else if (key.equals("PLANE")) {
					if (item.get(key) != null) {
						BigDecimal plane = new BigDecimal(df.format(item.get(key))); 
						organData.setPlanE(plane);
					} else {
						organData.setPlanCapacity(new BigDecimal(0));	
					}
				} else if (key.equals("ETOYESTER")) {
					if (item.get(key) != null) {
						BigDecimal etoyester= new BigDecimal(df.format(item.get(key))); 
						organData.seteToYester(etoyester);
					} else {
						organData.setPlanCapacity(new BigDecimal(0));	
					}
				} else if (key.equals("PLANETOYESTER")) {
					if (item.get(key) != null) {
						BigDecimal planeetoyester = new BigDecimal(df.format(item.get(key))); 
						organData.setPlanEToYester(planeetoyester);
					} else {
						organData.setPlanCapacity(new BigDecimal(0));	
					}
				}
			}
		}
		
		ChartBean chart1 = new ChartBean();  // 图表Bean
		List<String> xAxisCategories = new ArrayList<String>();  // x刻度列表
		List<DataBean> dataBeanList = new ArrayList<DataBean>();  // 数据Bean列表
		List<String> nameList = new ArrayList<String>();
		List<BigDecimal> eList = new ArrayList<BigDecimal>();
		List<BigDecimal> onnetEList = new ArrayList<BigDecimal>();
		DataBean eBean = new DataBean();  // 实际发电量Bean
		eBean.setDataType("column");
		eBean.setyAxis(0);
		eBean.setDataName("实际发电量");
		DataBean onNetEBean = new DataBean();  // 上网电量Bean
		onNetEBean.setDataType("column");
		onNetEBean.setyAxis(0);
		onNetEBean.setDataName("上网电量");
		onNetEBean.setVisible(false);
		chart1.setDataBeanList(dataBeanList);
		Iterator<Map<String, Object>> ite0 = chartQueryList1.iterator(); 
		while (ite0.hasNext()) { 
			Map<String, Object> item2 = ite0.next();
			for (String key : item2.keySet()) {
				if (key.equals("NAME")) {
					if(chart1.getTitle() == null){
						chart1.setTitle("");	
					}
					nameList.add(item2.get(key).toString() );
					xAxisCategories.add(item2.get(key).toString());
				} else if (key.equals("E")) {
					if (item2.get(key) != null) {
						BigDecimal e = new BigDecimal(df.format(item2.get(key))); 
						eBean.addData(e);	
						eList.add(e);
					} else {
						eBean.addData(new BigDecimal(0));	
						eList.add(new BigDecimal(0));
					}
				} else if (key.equals("ONNETE")) {
					if (item2.get(key) != null) {
						BigDecimal onNetE = new BigDecimal(df.format(item2.get(key))); 
						onNetEBean.addData(onNetE);	
						onnetEList.add(onNetE);
					} else {
						onNetEBean.addData(new BigDecimal(0));	
						onnetEList.add(new BigDecimal(0));
					}
				}
			}
		}
		dataBeanList.add(eBean);
		dataBeanList.add(onNetEBean);
		chart1.setDataBeanList(dataBeanList);
		chart1.setxAxisCateGories(xAxisCategories);
		chartData.add(chart1);
		organData.setOrganList(nameList);
		organData.seteList(eList);
		organData.setOnnetEList(onnetEList);
		
		ChartBean chart2 = new ChartBean();  // 图表Bean
		List<String> xAxisCategories1 = new ArrayList<String>();  // x刻度列表
		List<DataBean> dataBeanList2 = new ArrayList<DataBean>();  // 数据Bean列表
		DataBean eBean1 = new DataBean();  // 实际发电量Bean
		eBean1.setDataType("line");
		eBean1.setyAxis(0);
		eBean1.setDataName("实际发电量");
		DataBean onNetEBean1 = new DataBean();  // 上网电量Bean
		onNetEBean1.setDataType("line");
		onNetEBean1.setyAxis(0);
		onNetEBean1.setDataName("上网电量");
		onNetEBean1.setVisible(false);
		chart2.setDataBeanList(dataBeanList);
		Iterator<Map<String, Object>> ite2 = chartQueryList.iterator(); 
		while (ite2.hasNext()) { 
			Map<String, Object> item2 = ite2.next();
			for (String key : item2.keySet()) {
				if (key.equals("ORGAN_NAME") && chart2.getTitle() == null) {
					chart2.setTitle("");
				} else if (key.equals("MONTH")) {
					String month = item2.get(key).toString(); 
					xAxisCategories1.add(month);
				} else if (key.equals("E")) {
					if (item2.get(key) != null) {
						BigDecimal e = new BigDecimal(df.format(item2.get(key))); 
						eBean1.addData(e);	
					} else {
						eBean1.addData(new BigDecimal(0));	
					}
				} else if (key.equals("ONNETE")) {
					if (item2.get(key) != null) {
						BigDecimal onNetE = new BigDecimal(df.format(item2.get(key))); 
						onNetEBean1.addData(onNetE);	
					} else {
						onNetEBean1.addData(new BigDecimal(0));	
					}
				}
			}
		}
		dataBeanList2.add(eBean1);
		dataBeanList2.add(onNetEBean1);
		chart2.setDataBeanList(dataBeanList2);
		chart2.setxAxisCateGories(xAxisCategories1);
		chartData.add(chart2);
		
		 RankingBean efficEBean = new RankingBean();
		    efficEBean.setCode("efficE");
		    efficEBean.setTitle("电站发电效率排行");
		    List<RankingDataBean> efficEList = new ArrayList<RankingDataBean>();
		    efficEBean.setDataList(efficEList);
		    RankingBean singleMWEBean = new RankingBean();
		    singleMWEBean.setCode("singleMWE");
		    singleMWEBean.setTitle("电站单兆瓦发电量排行");
		    List<RankingDataBean> singleMWEList = new ArrayList<RankingDataBean>();
		    singleMWEBean.setDataList(singleMWEList);
		    RankingBean sameUseHourBean = new RankingBean();
		    sameUseHourBean.setCode("sameUseHour");
		    sameUseHourBean.setTitle("电站等效利用小时排行");
		    List<RankingDataBean> sameUseHourList = new ArrayList<RankingDataBean>();
		    sameUseHourBean.setDataList(sameUseHourList);
		    RankingBean eScaleBean = new RankingBean();
		    eScaleBean.setCode("eScale");
		    eScaleBean.setTitle("电站发电量计划完成率排行");
		    List<RankingDataBean> eScaleList = new ArrayList<RankingDataBean>();
		    eScaleBean.setDataList(eScaleList);
		    RankingBean onNetEScaleBean = new RankingBean();
		    onNetEScaleBean.setCode("onNetEScale");
		    onNetEScaleBean.setTitle("电站上网电量计划完成率排行");
		    List<RankingDataBean> onNetEScaleList = new ArrayList<RankingDataBean>();
		    onNetEScaleBean.setDataList(onNetEScaleList);
		Iterator<Map<String, Object>> ite1 = rankingQueryList.iterator();
		while (ite1.hasNext()) {
			Map<String, Object> item1 = ite1.next();
			RankingDataBean efficData = new RankingDataBean();
			RankingDataBean singleMWEData = new RankingDataBean();
			RankingDataBean sameUseHourData = new RankingDataBean();
			RankingDataBean eData = new RankingDataBean();
			RankingDataBean eScaleData = new RankingDataBean();
			RankingDataBean factoryEScaleData = new RankingDataBean();
			RankingDataBean onNetEData = new RankingDataBean();
			RankingDataBean onNetEScaleData = new RankingDataBean();
			RankingDataBean compreFactoryEScaleData = new RankingDataBean();
			for (String key : item1.keySet()) {
				if (key.equals("NAME")) {
					efficData.setName(item1.get(key).toString());
					singleMWEData.setName(item1.get(key).toString());
					sameUseHourData.setName(item1.get(key).toString());
					eData.setName(item1.get(key).toString());
					eScaleData.setName(item1.get(key).toString());
					factoryEScaleData.setName(item1.get(key).toString());
					onNetEData.setName(item1.get(key).toString());
					onNetEScaleData.setName(item1.get(key).toString());
					compreFactoryEScaleData.setName(item1.get(key).toString());
				} else if (key.equals("EFFICE")) {
					if (item1.get(key) != null) {
						efficData.setValue(new BigDecimal(df.format(item1.get(key))));	
					} else {
						efficData.setValue(new BigDecimal(0));	
					}
				}  else if (key.equals("SINGLEMWE")) {
					if (item1.get(key) != null) {
						singleMWEData.setValue(new BigDecimal(df.format(item1.get(key))));	
					} else {
						singleMWEData.setValue(new BigDecimal(0));	
					}
				} else if (key.equals("SAMEUSEHOUR")) {
					if (item1.get(key) != null) {
						sameUseHourData.setValue(new BigDecimal(df.format(item1.get(key))));	
					} else {
						sameUseHourData.setValue(new BigDecimal(0));	
					}
				} else if (key.equals("ESCALE")) {
					if (item1.get(key) != null) {
						eScaleData.setValue(new BigDecimal(df.format(item1.get(key))));
					} else {
						eScaleData.setValue(new BigDecimal(0));	
					}
				} else if (key.equals("ONNETESCALE")) {
					if (item1.get(key) != null) {
						onNetEScaleData.setValue(new BigDecimal(df.format(item1.get(key))));
					} else {
						onNetEScaleData.setValue(new BigDecimal(0));	
					}
				}
			}
			efficEBean.getDataList().add(efficData);
			singleMWEBean.getDataList().add(singleMWEData);
			sameUseHourBean.getDataList().add(sameUseHourData);
			eScaleBean.getDataList().add(eScaleData);
			onNetEScaleBean.getDataList().add(onNetEScaleData);
		}
		if (!efficEBean.getDataList().isEmpty()) {
			Collections.sort(efficEBean.getDataList());	
		}
		if (!singleMWEBean.getDataList().isEmpty()) {
			Collections.sort(singleMWEBean.getDataList());	
		}
		if (!sameUseHourBean.getDataList().isEmpty()) {
			Collections.sort(sameUseHourBean.getDataList());	
		}
		if (!eScaleBean.getDataList().isEmpty()) {
			Collections.sort(eScaleBean.getDataList());	
		}
		if (!onNetEScaleBean.getDataList().isEmpty()) {
			Collections.sort(onNetEScaleBean.getDataList());	
		}
		rankingData.add(efficEBean);
		rankingData.add(singleMWEBean);
		rankingData.add(sameUseHourBean);
		rankingData.add(eScaleBean);
		rankingData.add(onNetEScaleBean);
		
		
		
		result.setOrganData(organData);
		result.setChartData(chartData);
		result.setRankingData(rankingData);
		return result;
	}

	@Override
	public OverViewBean getGroupOverviewMonthData(Long year, Long month) {
		OverViewBean result = new OverViewBean();
		List<Map<String, Object>> organQueryList = groupCensusJdbc.getBranchOrganData(year,month);  // 获取查询结果
		List<Map<String, Object>> chartQueryList = groupCensusJdbc.getEMonthData(year,month);  // 获取查询结果
		List<Map<String, Object>> chartQueryList1 = groupContrastJdbc.getBranchOnNetEMonthData(year,month);  // 获取查询结果
		List<Map<String, Object>> rankingQueryList = groupContrastJdbc.getStationRankingMonthData(year,month);  // 获取查询结果
		OraganDataBean organData = new OraganDataBean();
		List<ChartBean> chartData = new ArrayList<ChartBean>();
		List<RankingBean> rankingData = new ArrayList<RankingBean>();
		DecimalFormat df = new DecimalFormat("0.00");
		Iterator<Map<String, Object>> ite = organQueryList.iterator(); 
		while (ite.hasNext()) { 
			Map<String, Object> item = ite.next();
			for (String key : item.keySet()) {
				if (key.equals("CAPACITY")) {
					if (item.get(key) != null) {
						BigDecimal capacity = new BigDecimal(df.format(item.get(key))); 
						organData.setCapacity(capacity);
					} else {
						organData.setCapacity(new BigDecimal(0));	
					}
				} else if (key.equals("PLANCAPACITY")) {
					if (item.get(key) != null) {
						BigDecimal planCapacity = new BigDecimal(df.format(item.get(key))); 
						organData.setPlanCapacity(planCapacity);
					} else {
						organData.setPlanCapacity(new BigDecimal(0));	
					}
				} else if (key.equals("EFFICE")) {
					if (item.get(key) != null) {
						BigDecimal effice = new BigDecimal(df.format(item.get(key))); 
						organData.setEfficE(effice);
					} else {
						organData.setPlanCapacity(new BigDecimal(0));	
					}
				} else if (key.equals("E")) {
					if (item.get(key) != null) {
						BigDecimal e = new BigDecimal(df.format(item.get(key))); 
						organData.setE(e);
					} else {
						organData.setPlanCapacity(new BigDecimal(0));	
					}
				} else if (key.equals("PLANE")) {
					if (item.get(key) != null) {
						BigDecimal plane = new BigDecimal(df.format(item.get(key))); 
						organData.setPlanE(plane);
					} else {
						organData.setPlanCapacity(new BigDecimal(0));	
					}
				} else if (key.equals("ETOYESTER")) {
					if (item.get(key) != null) {
						BigDecimal etoyester= new BigDecimal(df.format(item.get(key))); 
						organData.seteToYester(etoyester);
					} else {
						organData.setPlanCapacity(new BigDecimal(0));	
					}
				} else if (key.equals("PLANETOYESTER")) {
					if (item.get(key) != null) {
						BigDecimal planeetoyester = new BigDecimal(df.format(item.get(key))); 
						organData.setPlanEToYester(planeetoyester);
					} else {
						organData.setPlanCapacity(new BigDecimal(0));	
					}
				}
			}
		}
		
		ChartBean chart1 = new ChartBean();  // 图表Bean
		List<String> xAxisCategories = new ArrayList<String>();  // x刻度列表
		List<DataBean> dataBeanList = new ArrayList<DataBean>();  // 数据Bean列表
		List<String> nameList = new ArrayList<String>();
		List<BigDecimal> eList = new ArrayList<BigDecimal>();
		List<BigDecimal> onnetEList = new ArrayList<BigDecimal>();
		DataBean eBean = new DataBean();  // 实际发电量Bean
		eBean.setDataType("column");
		eBean.setyAxis(0);
		eBean.setDataName("实际发电量");
		DataBean onNetEBean = new DataBean();  // 上网电量Bean
		onNetEBean.setDataType("column");
		onNetEBean.setyAxis(0);
		onNetEBean.setDataName("上网电量");
		onNetEBean.setVisible(false);
		chart1.setDataBeanList(dataBeanList);
		Iterator<Map<String, Object>> ite0 = chartQueryList1.iterator(); 
		while (ite0.hasNext()) { 
			Map<String, Object> item2 = ite0.next();
			for (String key : item2.keySet()) {
				if (key.equals("NAME")) {
					if(chart1.getTitle() == null){
						chart1.setTitle("");	
					}
					nameList.add(item2.get(key).toString() );
					xAxisCategories.add(item2.get(key).toString());
				} else if (key.equals("E")) {
					if (item2.get(key) != null) {
						BigDecimal e = new BigDecimal(df.format(item2.get(key))); 
						eBean.addData(e);	
						eList.add(e);
					} else {
						eBean.addData(new BigDecimal(0));	
						eList.add(new BigDecimal(0));
					}
				} else if (key.equals("ONNETE")) {
					if (item2.get(key) != null) {
						BigDecimal onNetE = new BigDecimal(df.format(item2.get(key))); 
						onNetEBean.addData(onNetE);	
						onnetEList.add(onNetE);
					} else {
						onNetEBean.addData(new BigDecimal(0));	
						onnetEList.add(new BigDecimal(0));
					}
				}
			}
		}
		dataBeanList.add(eBean);
		dataBeanList.add(onNetEBean);
		chart1.setDataBeanList(dataBeanList);
		chart1.setxAxisCateGories(xAxisCategories);
		chartData.add(chart1);
		organData.setOrganList(nameList);
		organData.seteList(eList);
		organData.setOnnetEList(onnetEList);
		
		ChartBean chart2 = new ChartBean();  // 图表Bean
		List<String> xAxisCategories1 = new ArrayList<String>();  // x刻度列表
		List<DataBean> dataBeanList2 = new ArrayList<DataBean>();  // 数据Bean列表
		DataBean eBean1 = new DataBean();  // 实际发电量Bean
		eBean1.setDataType("line");
		eBean1.setyAxis(0);
		eBean1.setDataName("实际发电量");
		DataBean onNetEBean1 = new DataBean();  // 上网电量Bean
		onNetEBean1.setDataType("line");
		onNetEBean1.setyAxis(0);
		onNetEBean1.setDataName("上网电量");
		onNetEBean1.setVisible(false);
		chart2.setDataBeanList(dataBeanList);
		Iterator<Map<String, Object>> ite2 = chartQueryList.iterator(); 
		while (ite2.hasNext()) { 
			Map<String, Object> item2 = ite2.next();
			for (String key : item2.keySet()) {
				if (key.equals("ORGAN_NAME") && chart2.getTitle() == null) {
					chart2.setTitle("");
				} else if (key.equals("MONTH")) {
					String month1 = item2.get(key).toString(); 
					xAxisCategories1.add(month1);
				} else if (key.equals("E")) {
					if (item2.get(key) != null) {
						BigDecimal e = new BigDecimal(df.format(item2.get(key))); 
						eBean1.addData(e);	
					} else {
						eBean1.addData(new BigDecimal(0));	
					}
				} else if (key.equals("ONNETE")) {
					if (item2.get(key) != null) {
						BigDecimal onNetE = new BigDecimal(df.format(item2.get(key))); 
						onNetEBean1.addData(onNetE);	
					} else {
						onNetEBean1.addData(new BigDecimal(0));	
					}
				}
			}
		}
		dataBeanList2.add(eBean1);
		dataBeanList2.add(onNetEBean1);
		chart2.setDataBeanList(dataBeanList2);
		chart2.setxAxisCateGories(xAxisCategories1);
		chartData.add(chart2);
		
		 RankingBean efficEBean = new RankingBean();
		    efficEBean.setCode("efficE");
		    efficEBean.setTitle("电站发电效率排行");
		    List<RankingDataBean> efficEList = new ArrayList<RankingDataBean>();
		    efficEBean.setDataList(efficEList);
		    RankingBean singleMWEBean = new RankingBean();
		    singleMWEBean.setCode("singleMWE");
		    singleMWEBean.setTitle("电站单兆瓦发电量排行");
		    List<RankingDataBean> singleMWEList = new ArrayList<RankingDataBean>();
		    singleMWEBean.setDataList(singleMWEList);
		    RankingBean sameUseHourBean = new RankingBean();
		    sameUseHourBean.setCode("sameUseHour");
		    sameUseHourBean.setTitle("电站等效利用小时排行");
		    List<RankingDataBean> sameUseHourList = new ArrayList<RankingDataBean>();
		    sameUseHourBean.setDataList(sameUseHourList);
		    RankingBean eScaleBean = new RankingBean();
		    eScaleBean.setCode("eScale");
		    eScaleBean.setTitle("电站发电量计划完成率排行");
		    List<RankingDataBean> eScaleList = new ArrayList<RankingDataBean>();
		    eScaleBean.setDataList(eScaleList);
		    RankingBean onNetEScaleBean = new RankingBean();
		    onNetEScaleBean.setCode("onNetEScale");
		    onNetEScaleBean.setTitle("电站上网电量计划完成率排行");
		    List<RankingDataBean> onNetEScaleList = new ArrayList<RankingDataBean>();
		    onNetEScaleBean.setDataList(onNetEScaleList);
		Iterator<Map<String, Object>> ite1 = rankingQueryList.iterator();
		while (ite1.hasNext()) {
			Map<String, Object> item1 = ite1.next();
			RankingDataBean efficData = new RankingDataBean();
			RankingDataBean singleMWEData = new RankingDataBean();
			RankingDataBean sameUseHourData = new RankingDataBean();
			RankingDataBean eData = new RankingDataBean();
			RankingDataBean eScaleData = new RankingDataBean();
			RankingDataBean factoryEScaleData = new RankingDataBean();
			RankingDataBean onNetEData = new RankingDataBean();
			RankingDataBean onNetEScaleData = new RankingDataBean();
			RankingDataBean compreFactoryEScaleData = new RankingDataBean();
			for (String key : item1.keySet()) {
				if (key.equals("NAME")) {
					efficData.setName(item1.get(key).toString());
					singleMWEData.setName(item1.get(key).toString());
					sameUseHourData.setName(item1.get(key).toString());
					eData.setName(item1.get(key).toString());
					eScaleData.setName(item1.get(key).toString());
					factoryEScaleData.setName(item1.get(key).toString());
					onNetEData.setName(item1.get(key).toString());
					onNetEScaleData.setName(item1.get(key).toString());
					compreFactoryEScaleData.setName(item1.get(key).toString());
				} else if (key.equals("EFFICE")) {
					if (item1.get(key) != null) {
						efficData.setValue(new BigDecimal(df.format(item1.get(key))));	
					} else {
						efficData.setValue(new BigDecimal(0));	
					}
				}  else if (key.equals("SINGLEMWE")) {
					if (item1.get(key) != null) {
						singleMWEData.setValue(new BigDecimal(df.format(item1.get(key))));	
					} else {
						singleMWEData.setValue(new BigDecimal(0));	
					}
				} else if (key.equals("SAMEUSEHOUR")) {
					if (item1.get(key) != null) {
						sameUseHourData.setValue(new BigDecimal(df.format(item1.get(key))));	
					} else {
						sameUseHourData.setValue(new BigDecimal(0));	
					}
				} else if (key.equals("ESCALE")) {
					if (item1.get(key) != null) {
						eScaleData.setValue(new BigDecimal(df.format(item1.get(key))));
					} else {
						eScaleData.setValue(new BigDecimal(0));	
					}
				} else if (key.equals("ONNETESCALE")) {
					if (item1.get(key) != null) {
						onNetEScaleData.setValue(new BigDecimal(df.format(item1.get(key))));
					} else {
						onNetEScaleData.setValue(new BigDecimal(0));	
					}
				}
			}
			efficEBean.getDataList().add(efficData);
			singleMWEBean.getDataList().add(singleMWEData);
			sameUseHourBean.getDataList().add(sameUseHourData);
			eScaleBean.getDataList().add(eScaleData);
			onNetEScaleBean.getDataList().add(onNetEScaleData);
		}
		if (!efficEBean.getDataList().isEmpty()) {
			Collections.sort(efficEBean.getDataList());	
		}
		if (!singleMWEBean.getDataList().isEmpty()) {
			Collections.sort(singleMWEBean.getDataList());	
		}
		if (!sameUseHourBean.getDataList().isEmpty()) {
			Collections.sort(sameUseHourBean.getDataList());	
		}
		if (!eScaleBean.getDataList().isEmpty()) {
			Collections.sort(eScaleBean.getDataList());	
		}
		if (!onNetEScaleBean.getDataList().isEmpty()) {
			Collections.sort(onNetEScaleBean.getDataList());	
		}
		rankingData.add(efficEBean);
		rankingData.add(singleMWEBean);
		rankingData.add(sameUseHourBean);
		rankingData.add(eScaleBean);
		rankingData.add(onNetEScaleBean);
		
		
		
		result.setOrganData(organData);
		result.setChartData(chartData);
		result.setRankingData(rankingData);
		return result;
	}

	@Override
	public OverViewBean getBranchOverviewYearData(Long id, Long year) {
		OverViewBean result = new OverViewBean();
		List<Map<String, Object>> organQueryList = groupCensusJdbc.getGroupOrganData(year);  // 获取查询结果
		List<Map<String, Object>> chartQueryList = groupCensusJdbc.getBranchEYearData(id,year);  // 获取查询结果
		List<Map<String, Object>> chartQueryList1 = groupContrastJdbc.getBranchStationEOnetYearData(id,year);  // 获取查询结果
		List<Map<String, Object>> rankingQueryList = groupContrastJdbc.getBranchStationRankingYearData(id,year);  // 获取查询结果
		OraganDataBean organData = new OraganDataBean();
		List<ChartBean> chartData = new ArrayList<ChartBean>();
		List<RankingBean> rankingData = new ArrayList<RankingBean>();
		DecimalFormat df = new DecimalFormat("0.00");
		Iterator<Map<String, Object>> ite = organQueryList.iterator(); 
		while (ite.hasNext()) { 
			Map<String, Object> item = ite.next();
			for (String key : item.keySet()) {
				if (key.equals("CAPACITY")) {
					if (item.get(key) != null) {
						BigDecimal capacity = new BigDecimal(df.format(item.get(key))); 
						organData.setCapacity(capacity);
					} else {
						organData.setCapacity(new BigDecimal(0));	
					}
				} else if (key.equals("PLANCAPACITY")) {
					if (item.get(key) != null) {
						BigDecimal planCapacity = new BigDecimal(df.format(item.get(key))); 
						organData.setPlanCapacity(planCapacity);
					} else {
						organData.setPlanCapacity(new BigDecimal(0));	
					}
				} else if (key.equals("EFFICE")) {
					if (item.get(key) != null) {
						BigDecimal effice = new BigDecimal(df.format(item.get(key))); 
						organData.setEfficE(effice);
					} else {
						organData.setPlanCapacity(new BigDecimal(0));	
					}
				} else if (key.equals("E")) {
					if (item.get(key) != null) {
						BigDecimal e = new BigDecimal(df.format(item.get(key))); 
						organData.setE(e);
					} else {
						organData.setPlanCapacity(new BigDecimal(0));	
					}
				} else if (key.equals("PLANE")) {
					if (item.get(key) != null) {
						BigDecimal plane = new BigDecimal(df.format(item.get(key))); 
						organData.setPlanE(plane);
					} else {
						organData.setPlanCapacity(new BigDecimal(0));	
					}
				} else if (key.equals("ETOYESTER")) {
					if (item.get(key) != null) {
						BigDecimal etoyester= new BigDecimal(df.format(item.get(key))); 
						organData.seteToYester(etoyester);
					} else {
						organData.setPlanCapacity(new BigDecimal(0));	
					}
				} else if (key.equals("PLANETOYESTER")) {
					if (item.get(key) != null) {
						BigDecimal planeetoyester = new BigDecimal(df.format(item.get(key))); 
						organData.setPlanEToYester(planeetoyester);
					} else {
						organData.setPlanCapacity(new BigDecimal(0));	
					}
				}
			}
		}
		
		ChartBean chart1 = new ChartBean();  // 图表Bean
		List<String> xAxisCategories = new ArrayList<String>();  // x刻度列表
		List<DataBean> dataBeanList = new ArrayList<DataBean>();  // 数据Bean列表
		List<String> nameList = new ArrayList<String>();
		List<BigDecimal> eList = new ArrayList<BigDecimal>();
		List<BigDecimal> onnetEList = new ArrayList<BigDecimal>();
		DataBean eBean = new DataBean();  // 实际发电量Bean
		eBean.setDataType("column");
		eBean.setyAxis(0);
		eBean.setDataName("实际发电量");
		DataBean onNetEBean = new DataBean();  // 上网电量Bean
		onNetEBean.setDataType("column");
		onNetEBean.setyAxis(0);
		onNetEBean.setDataName("上网电量");
		onNetEBean.setVisible(false);
		chart1.setDataBeanList(dataBeanList);
		Iterator<Map<String, Object>> ite0 = chartQueryList1.iterator(); 
		while (ite0.hasNext()) { 
			Map<String, Object> item2 = ite0.next();
			for (String key : item2.keySet()) {
				if (key.equals("NAME")) {
					if(chart1.getTitle() == null){
						chart1.setTitle("");	
					}
					nameList.add(item2.get(key).toString() );
					xAxisCategories.add(item2.get(key).toString());
				} else if (key.equals("E")) {
					if (item2.get(key) != null) {
						BigDecimal e = new BigDecimal(df.format(item2.get(key))); 
						eBean.addData(e);	
						eList.add(e);
					} else {
						eBean.addData(new BigDecimal(0));	
						eList.add(new BigDecimal(0));
					}
				} else if (key.equals("ONNETE")) {
					if (item2.get(key) != null) {
						BigDecimal onNetE = new BigDecimal(df.format(item2.get(key))); 
						onNetEBean.addData(onNetE);	
						onnetEList.add(onNetE);
					} else {
						onNetEBean.addData(new BigDecimal(0));	
						onnetEList.add(new BigDecimal(0));
					}
				}
			}
		}
		dataBeanList.add(eBean);
		dataBeanList.add(onNetEBean);
		chart1.setDataBeanList(dataBeanList);
		chart1.setxAxisCateGories(xAxisCategories);
		chartData.add(chart1);
		organData.setOrganList(nameList);
		organData.seteList(eList);
		organData.setOnnetEList(onnetEList);
		
		ChartBean chart2 = new ChartBean();  // 图表Bean
		List<String> xAxisCategories1 = new ArrayList<String>();  // x刻度列表
		List<DataBean> dataBeanList2 = new ArrayList<DataBean>();  // 数据Bean列表
		DataBean eBean1 = new DataBean();  // 实际发电量Bean
		eBean1.setDataType("line");
		eBean1.setyAxis(0);
		eBean1.setDataName("实际发电量");
		DataBean onNetEBean1 = new DataBean();  // 上网电量Bean
		onNetEBean1.setDataType("line");
		onNetEBean1.setyAxis(0);
		onNetEBean1.setDataName("上网电量");
		onNetEBean1.setVisible(false);
		chart2.setDataBeanList(dataBeanList);
		Iterator<Map<String, Object>> ite2 = chartQueryList.iterator(); 
		while (ite2.hasNext()) { 
			Map<String, Object> item2 = ite2.next();
			for (String key : item2.keySet()) {
				if (key.equals("ORGAN_NAME") && chart2.getTitle() == null) {
					chart2.setTitle("");
				} else if (key.equals("MONTH")) {
					String month = item2.get(key).toString(); 
					xAxisCategories1.add(month);
				} else if (key.equals("E")) {
					if (item2.get(key) != null) {
						BigDecimal e = new BigDecimal(df.format(item2.get(key))); 
						eBean1.addData(e);	
					} else {
						eBean1.addData(new BigDecimal(0));	
					}
				} else if (key.equals("ONNETE")) {
					if (item2.get(key) != null) {
						BigDecimal onNetE = new BigDecimal(df.format(item2.get(key))); 
						onNetEBean1.addData(onNetE);	
					} else {
						onNetEBean1.addData(new BigDecimal(0));	
					}
				}
			}
		}
		dataBeanList2.add(eBean1);
		dataBeanList2.add(onNetEBean1);
		chart2.setDataBeanList(dataBeanList2);
		chart2.setxAxisCateGories(xAxisCategories1);
		chartData.add(chart2);
		
		 RankingBean efficEBean = new RankingBean();
		    efficEBean.setCode("efficE");
		    efficEBean.setTitle("电站发电效率排行");
		    List<RankingDataBean> efficEList = new ArrayList<RankingDataBean>();
		    efficEBean.setDataList(efficEList);
		    RankingBean singleMWEBean = new RankingBean();
		    singleMWEBean.setCode("singleMWE");
		    singleMWEBean.setTitle("电站单兆瓦发电量排行");
		    List<RankingDataBean> singleMWEList = new ArrayList<RankingDataBean>();
		    singleMWEBean.setDataList(singleMWEList);
		    RankingBean sameUseHourBean = new RankingBean();
		    sameUseHourBean.setCode("sameUseHour");
		    sameUseHourBean.setTitle("电站等效利用小时排行");
		    List<RankingDataBean> sameUseHourList = new ArrayList<RankingDataBean>();
		    sameUseHourBean.setDataList(sameUseHourList);
		    RankingBean eScaleBean = new RankingBean();
		    eScaleBean.setCode("eScale");
		    eScaleBean.setTitle("电站发电量计划完成率排行");
		    List<RankingDataBean> eScaleList = new ArrayList<RankingDataBean>();
		    eScaleBean.setDataList(eScaleList);
		    RankingBean onNetEScaleBean = new RankingBean();
		    onNetEScaleBean.setCode("onNetEScale");
		    onNetEScaleBean.setTitle("电站上网电量计划完成率排行");
		    List<RankingDataBean> onNetEScaleList = new ArrayList<RankingDataBean>();
		    onNetEScaleBean.setDataList(onNetEScaleList);
		Iterator<Map<String, Object>> ite1 = rankingQueryList.iterator();
		while (ite1.hasNext()) {
			Map<String, Object> item1 = ite1.next();
			RankingDataBean efficData = new RankingDataBean();
			RankingDataBean singleMWEData = new RankingDataBean();
			RankingDataBean sameUseHourData = new RankingDataBean();
			RankingDataBean eData = new RankingDataBean();
			RankingDataBean eScaleData = new RankingDataBean();
			RankingDataBean factoryEScaleData = new RankingDataBean();
			RankingDataBean onNetEData = new RankingDataBean();
			RankingDataBean onNetEScaleData = new RankingDataBean();
			RankingDataBean compreFactoryEScaleData = new RankingDataBean();
			for (String key : item1.keySet()) {
				if (key.equals("NAME")) {
					efficData.setName(item1.get(key).toString());
					singleMWEData.setName(item1.get(key).toString());
					sameUseHourData.setName(item1.get(key).toString());
					eData.setName(item1.get(key).toString());
					eScaleData.setName(item1.get(key).toString());
					factoryEScaleData.setName(item1.get(key).toString());
					onNetEData.setName(item1.get(key).toString());
					onNetEScaleData.setName(item1.get(key).toString());
					compreFactoryEScaleData.setName(item1.get(key).toString());
				} else if (key.equals("EFFICE")) {
					if (item1.get(key) != null) {
						efficData.setValue(new BigDecimal(df.format(item1.get(key))));	
					} else {
						efficData.setValue(new BigDecimal(0));	
					}
				}  else if (key.equals("SINGLEMWE")) {
					if (item1.get(key) != null) {
						singleMWEData.setValue(new BigDecimal(df.format(item1.get(key))));	
					} else {
						singleMWEData.setValue(new BigDecimal(0));	
					}
				} else if (key.equals("SAMEUSEHOUR")) {
					if (item1.get(key) != null) {
						sameUseHourData.setValue(new BigDecimal(df.format(item1.get(key))));	
					} else {
						sameUseHourData.setValue(new BigDecimal(0));	
					}
				} else if (key.equals("ESCALE")) {
					if (item1.get(key) != null) {
						eScaleData.setValue(new BigDecimal(df.format(item1.get(key))));
					} else {
						eScaleData.setValue(new BigDecimal(0));	
					}
				} else if (key.equals("ONNETESCALE")) {
					if (item1.get(key) != null) {
						onNetEScaleData.setValue(new BigDecimal(df.format(item1.get(key))));
					} else {
						onNetEScaleData.setValue(new BigDecimal(0));	
					}
				}
			}
			efficEBean.getDataList().add(efficData);
			singleMWEBean.getDataList().add(singleMWEData);
			sameUseHourBean.getDataList().add(sameUseHourData);
			eScaleBean.getDataList().add(eScaleData);
			onNetEScaleBean.getDataList().add(onNetEScaleData);
		}
		if (!efficEBean.getDataList().isEmpty()) {
			Collections.sort(efficEBean.getDataList());	
		}
		if (!singleMWEBean.getDataList().isEmpty()) {
			Collections.sort(singleMWEBean.getDataList());	
		}
		if (!sameUseHourBean.getDataList().isEmpty()) {
			Collections.sort(sameUseHourBean.getDataList());	
		}
		if (!eScaleBean.getDataList().isEmpty()) {
			Collections.sort(eScaleBean.getDataList());	
		}
		if (!onNetEScaleBean.getDataList().isEmpty()) {
			Collections.sort(onNetEScaleBean.getDataList());	
		}
		rankingData.add(efficEBean);
		rankingData.add(singleMWEBean);
		rankingData.add(sameUseHourBean);
		rankingData.add(eScaleBean);
		rankingData.add(onNetEScaleBean);
		
		
		
		result.setOrganData(organData);
		result.setChartData(chartData);
		result.setRankingData(rankingData);
		return result;
	}

	@Override
	public OverViewBean getBranchOverviewMonthData(Long id, Long year,
			Long month) {
		OverViewBean result = new OverViewBean();
		List<Map<String, Object>> organQueryList = groupCensusJdbc.getBranchOrganData(id,year,month);  // 获取查询结果
		List<Map<String, Object>> chartQueryList = groupCensusJdbc.getBranchEMonthData(id,year,month);  // 获取查询结果
		List<Map<String, Object>> chartQueryList1 = groupContrastJdbc.getBranchStationEOnetMonthData(id,year,month);  // 获取查询结果
		List<Map<String, Object>> rankingQueryList = groupContrastJdbc.getBranchStationRankingMonthData(id,year,month);  // 获取查询结果
		OraganDataBean organData = new OraganDataBean();
		List<ChartBean> chartData = new ArrayList<ChartBean>();
		List<RankingBean> rankingData = new ArrayList<RankingBean>();
		DecimalFormat df = new DecimalFormat("0.00");
		Iterator<Map<String, Object>> ite = organQueryList.iterator(); 
		while (ite.hasNext()) { 
			Map<String, Object> item = ite.next();
			for (String key : item.keySet()) {
				if (key.equals("CAPACITY")) {
					if (item.get(key) != null) {
						BigDecimal capacity = new BigDecimal(df.format(item.get(key))); 
						organData.setCapacity(capacity);
					} else {
						organData.setCapacity(new BigDecimal(0));	
					}
				} else if (key.equals("PLANCAPACITY")) {
					if (item.get(key) != null) {
						BigDecimal planCapacity = new BigDecimal(df.format(item.get(key))); 
						organData.setPlanCapacity(planCapacity);
					} else {
						organData.setPlanCapacity(new BigDecimal(0));	
					}
				} else if (key.equals("EFFICE")) {
					if (item.get(key) != null) {
						BigDecimal effice = new BigDecimal(df.format(item.get(key))); 
						organData.setEfficE(effice);
					} else {
						organData.setPlanCapacity(new BigDecimal(0));	
					}
				} else if (key.equals("E")) {
					if (item.get(key) != null) {
						BigDecimal e = new BigDecimal(df.format(item.get(key))); 
						organData.setE(e);
					} else {
						organData.setPlanCapacity(new BigDecimal(0));	
					}
				} else if (key.equals("PLANE")) {
					if (item.get(key) != null) {
						BigDecimal plane = new BigDecimal(df.format(item.get(key))); 
						organData.setPlanE(plane);
					} else {
						organData.setPlanCapacity(new BigDecimal(0));	
					}
				} else if (key.equals("ETOYESTER")) {
					if (item.get(key) != null) {
						BigDecimal etoyester= new BigDecimal(df.format(item.get(key))); 
						organData.seteToYester(etoyester);
					} else {
						organData.setPlanCapacity(new BigDecimal(0));	
					}
				} else if (key.equals("PLANETOYESTER")) {
					if (item.get(key) != null) {
						BigDecimal planeetoyester = new BigDecimal(df.format(item.get(key))); 
						organData.setPlanEToYester(planeetoyester);
					} else {
						organData.setPlanCapacity(new BigDecimal(0));	
					}
				}
			}
		}
		
		ChartBean chart1 = new ChartBean();  // 图表Bean
		List<String> xAxisCategories = new ArrayList<String>();  // x刻度列表
		List<DataBean> dataBeanList = new ArrayList<DataBean>();  // 数据Bean列表
		List<String> nameList = new ArrayList<String>();
		List<BigDecimal> eList = new ArrayList<BigDecimal>();
		List<BigDecimal> onnetEList = new ArrayList<BigDecimal>();
		DataBean eBean = new DataBean();  // 实际发电量Bean
		eBean.setDataType("column");
		eBean.setyAxis(0);
		eBean.setDataName("实际发电量");
		DataBean onNetEBean = new DataBean();  // 上网电量Bean
		onNetEBean.setDataType("column");
		onNetEBean.setyAxis(0);
		onNetEBean.setDataName("上网电量");
		onNetEBean.setVisible(false);
		chart1.setDataBeanList(dataBeanList);
		Iterator<Map<String, Object>> ite0 = chartQueryList1.iterator(); 
		while (ite0.hasNext()) { 
			Map<String, Object> item2 = ite0.next();
			for (String key : item2.keySet()) {
				if (key.equals("NAME")) {
					if(chart1.getTitle() == null){
						chart1.setTitle("");	
					}
					nameList.add(item2.get(key).toString() );
					xAxisCategories.add(item2.get(key).toString());
				} else if (key.equals("E")) {
					if (item2.get(key) != null) {
						BigDecimal e = new BigDecimal(df.format(item2.get(key))); 
						eBean.addData(e);	
						eList.add(e);
					} else {
						eBean.addData(new BigDecimal(0));	
						eList.add(new BigDecimal(0));
					}
				} else if (key.equals("ONNETE")) {
					if (item2.get(key) != null) {
						BigDecimal onNetE = new BigDecimal(df.format(item2.get(key))); 
						onNetEBean.addData(onNetE);	
						onnetEList.add(onNetE);
					} else {
						onNetEBean.addData(new BigDecimal(0));	
						onnetEList.add(new BigDecimal(0));
					}
				}
			}
		}
		dataBeanList.add(eBean);
		dataBeanList.add(onNetEBean);
		chart1.setDataBeanList(dataBeanList);
		chart1.setxAxisCateGories(xAxisCategories);
		chartData.add(chart1);
		organData.setOrganList(nameList);
		organData.seteList(eList);
		organData.setOnnetEList(onnetEList);
		
		ChartBean chart2 = new ChartBean();  // 图表Bean
		List<String> xAxisCategories1 = new ArrayList<String>();  // x刻度列表
		List<DataBean> dataBeanList2 = new ArrayList<DataBean>();  // 数据Bean列表
		DataBean eBean1 = new DataBean();  // 实际发电量Bean
		eBean1.setDataType("line");
		eBean1.setyAxis(0);
		eBean1.setDataName("实际发电量");
		DataBean onNetEBean1 = new DataBean();  // 上网电量Bean
		onNetEBean1.setDataType("line");
		onNetEBean1.setyAxis(0);
		onNetEBean1.setDataName("上网电量");
		onNetEBean1.setVisible(false);
		chart2.setDataBeanList(dataBeanList);
		Iterator<Map<String, Object>> ite2 = chartQueryList.iterator(); 
		while (ite2.hasNext()) { 
			Map<String, Object> item2 = ite2.next();
			for (String key : item2.keySet()) {
				if (key.equals("ORGAN_NAME") && chart2.getTitle() == null) {
					chart2.setTitle("");
				} else if (key.equals("MONTH")) {
					String month1 = item2.get(key).toString(); 
					xAxisCategories1.add(month1);
				} else if (key.equals("E")) {
					if (item2.get(key) != null) {
						BigDecimal e = new BigDecimal(df.format(item2.get(key))); 
						eBean1.addData(e);	
					} else {
						eBean1.addData(new BigDecimal(0));	
					}
				} else if (key.equals("ONNETE")) {
					if (item2.get(key) != null) {
						BigDecimal onNetE = new BigDecimal(df.format(item2.get(key))); 
						onNetEBean1.addData(onNetE);	
					} else {
						onNetEBean1.addData(new BigDecimal(0));	
					}
				}
			}
		}
		dataBeanList2.add(eBean1);
		dataBeanList2.add(onNetEBean1);
		chart2.setDataBeanList(dataBeanList2);
		chart2.setxAxisCateGories(xAxisCategories1);
		chartData.add(chart2);
		
		 RankingBean efficEBean = new RankingBean();
		    efficEBean.setCode("efficE");
		    efficEBean.setTitle("电站发电效率排行");
		    List<RankingDataBean> efficEList = new ArrayList<RankingDataBean>();
		    efficEBean.setDataList(efficEList);
		    RankingBean singleMWEBean = new RankingBean();
		    singleMWEBean.setCode("singleMWE");
		    singleMWEBean.setTitle("电站单兆瓦发电量排行");
		    List<RankingDataBean> singleMWEList = new ArrayList<RankingDataBean>();
		    singleMWEBean.setDataList(singleMWEList);
		    RankingBean sameUseHourBean = new RankingBean();
		    sameUseHourBean.setCode("sameUseHour");
		    sameUseHourBean.setTitle("电站等效利用小时排行");
		    List<RankingDataBean> sameUseHourList = new ArrayList<RankingDataBean>();
		    sameUseHourBean.setDataList(sameUseHourList);
		    RankingBean eScaleBean = new RankingBean();
		    eScaleBean.setCode("eScale");
		    eScaleBean.setTitle("电站发电量计划完成率排行");
		    List<RankingDataBean> eScaleList = new ArrayList<RankingDataBean>();
		    eScaleBean.setDataList(eScaleList);
		    RankingBean onNetEScaleBean = new RankingBean();
		    onNetEScaleBean.setCode("onNetEScale");
		    onNetEScaleBean.setTitle("电站上网电量计划完成率排行");
		    List<RankingDataBean> onNetEScaleList = new ArrayList<RankingDataBean>();
		    onNetEScaleBean.setDataList(onNetEScaleList);
		Iterator<Map<String, Object>> ite1 = rankingQueryList.iterator();
		while (ite1.hasNext()) {
			Map<String, Object> item1 = ite1.next();
			RankingDataBean efficData = new RankingDataBean();
			RankingDataBean singleMWEData = new RankingDataBean();
			RankingDataBean sameUseHourData = new RankingDataBean();
			RankingDataBean eData = new RankingDataBean();
			RankingDataBean eScaleData = new RankingDataBean();
			RankingDataBean factoryEScaleData = new RankingDataBean();
			RankingDataBean onNetEData = new RankingDataBean();
			RankingDataBean onNetEScaleData = new RankingDataBean();
			RankingDataBean compreFactoryEScaleData = new RankingDataBean();
			for (String key : item1.keySet()) {
				if (key.equals("NAME")) {
					efficData.setName(item1.get(key).toString());
					singleMWEData.setName(item1.get(key).toString());
					sameUseHourData.setName(item1.get(key).toString());
					eData.setName(item1.get(key).toString());
					eScaleData.setName(item1.get(key).toString());
					factoryEScaleData.setName(item1.get(key).toString());
					onNetEData.setName(item1.get(key).toString());
					onNetEScaleData.setName(item1.get(key).toString());
					compreFactoryEScaleData.setName(item1.get(key).toString());
				} else if (key.equals("EFFICE")) {
					if (item1.get(key) != null) {
						efficData.setValue(new BigDecimal(df.format(item1.get(key))));	
					} else {
						efficData.setValue(new BigDecimal(0));	
					}
				}  else if (key.equals("SINGLEMWE")) {
					if (item1.get(key) != null) {
						singleMWEData.setValue(new BigDecimal(df.format(item1.get(key))));	
					} else {
						singleMWEData.setValue(new BigDecimal(0));	
					}
				} else if (key.equals("SAMEUSEHOUR")) {
					if (item1.get(key) != null) {
						sameUseHourData.setValue(new BigDecimal(df.format(item1.get(key))));	
					} else {
						sameUseHourData.setValue(new BigDecimal(0));	
					}
				} else if (key.equals("ESCALE")) {
					if (item1.get(key) != null) {
						eScaleData.setValue(new BigDecimal(df.format(item1.get(key))));
					} else {
						eScaleData.setValue(new BigDecimal(0));	
					}
				} else if (key.equals("ONNETESCALE")) {
					if (item1.get(key) != null) {
						onNetEScaleData.setValue(new BigDecimal(df.format(item1.get(key))));
					} else {
						onNetEScaleData.setValue(new BigDecimal(0));	
					}
				}
			}
			efficEBean.getDataList().add(efficData);
			singleMWEBean.getDataList().add(singleMWEData);
			sameUseHourBean.getDataList().add(sameUseHourData);
			eScaleBean.getDataList().add(eScaleData);
			onNetEScaleBean.getDataList().add(onNetEScaleData);
		}
		if (!efficEBean.getDataList().isEmpty()) {
			Collections.sort(efficEBean.getDataList());	
		}
		if (!singleMWEBean.getDataList().isEmpty()) {
			Collections.sort(singleMWEBean.getDataList());	
		}
		if (!sameUseHourBean.getDataList().isEmpty()) {
			Collections.sort(sameUseHourBean.getDataList());	
		}
		if (!eScaleBean.getDataList().isEmpty()) {
			Collections.sort(eScaleBean.getDataList());	
		}
		if (!onNetEScaleBean.getDataList().isEmpty()) {
			Collections.sort(onNetEScaleBean.getDataList());	
		}
		rankingData.add(efficEBean);
		rankingData.add(singleMWEBean);
		rankingData.add(sameUseHourBean);
		rankingData.add(eScaleBean);
		rankingData.add(onNetEScaleBean);
		
		
		
		result.setOrganData(organData);
		result.setChartData(chartData);
		result.setRankingData(rankingData);
		return result;
	}

}
